package tv.llel.lollipop.widgets;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import tv.llel.floatingactionbutton.FloatingActionButton;
import tv.llel.floatingactionbutton.FloatingActionsMenu;
import tv.llel.lollipop.widgets.R;
import tv.llel.supportclasses.Dialog;
import tv.llel.supportclasses.DividerItemDecoration;
import tv.llel.supportclasses.RecyclerItemClickListener;
import tv.llel.supportclasses.RecyclerViewAdapter;


public class LollipopWidgets extends Activity implements SwipeRefreshLayout.OnRefreshListener{

	RecyclerView recyclerView = null;
	FloatingActionsMenu floatMenu = null;
	FloatingActionButton floatButton1 = null, floatButton2 = null, floatButton3 = null;
	View fullView = null;
	
    private class Sample 
    {
        private CharSequence title;
        private Class<? extends Activity> activityClass;

        public Sample(int titleResId, Class<? extends Activity> activityClass) {
            this.activityClass = activityClass;
            this.title = getResources().getString(titleResId);
        }

        @Override
        public String toString() {
            return title.toString();
        }
    }

    private static Sample[] mSamples;

    Animation slide_down = null, slide_up = null; 
    		
    SwipeRefreshLayout mListViewContainer;
    
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
      //pull to swipe
        mListViewContainer = (SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayout_listView);
        onCreateSwipeToRefresh(mListViewContainer);
        
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(LollipopWidgets.this));
        recyclerView.addItemDecoration(new DividerItemDecoration(LollipopWidgets.this, DividerItemDecoration.VERTICAL_LIST));

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(LollipopWidgets.this, getResources()
            .getStringArray(R.array.countries));
        recyclerView.setAdapter(adapter);
        
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(LollipopWidgets.this, new RecyclerItemClickListener.OnItemClickListener() {
        	      @Override public void onItemClick(View view, int position) {
        	    	  if(fullView.getVisibility()==View.VISIBLE)
        	    		  toggle();
        	      }
        	    }));
        
        floatMenu = (FloatingActionsMenu)findViewById(R.id.multiple_actions);
        floatButton1 = (FloatingActionButton)findViewById(R.id.fab_action1);
        floatButton2 = (FloatingActionButton)findViewById(R.id.fab_ation2);
        floatButton3 = (FloatingActionButton)findViewById(R.id.fab_ation3);
        fullView = (View)findViewById(R.id.full_view);
        
        slide_down = AnimationUtils.loadAnimation(getApplicationContext(), R.animator.slide_down);
        slide_up = AnimationUtils.loadAnimation(getApplicationContext(), R.animator.slide_up);
        slide_down.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {}
			@Override
			public void onAnimationRepeat(Animation animation) {}
			@Override
			public void onAnimationEnd(Animation animation) {
				fullView.setVisibility(View.GONE);
			}
		});
        slide_up.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {}
			@Override
			public void onAnimationRepeat(Animation animation) {}
			@Override
			public void onAnimationEnd(Animation animation) {
				fullView.setVisibility(View.VISIBLE);
			}
		});
        
        floatMenu.mAddButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	Log.e("TAG","clicked----");
              toggle();
            }
          });
        
        // Instantiate the list of samples.
        mSamples = new Sample[]{
                new Sample(R.string.title_zoom, ZoomActivity.class),
                new Sample(R.string.tick_plus, TickPlusActivity.class),
                new Sample(R.string.title_layout_changes, LayoutChangesActivity.class),
        };
        
        floatButton1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				toggle();
				startActivity(new Intent(LollipopWidgets.this, mSamples[0].activityClass));
			}
		});
        floatButton2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				toggle();
				startActivity(new Intent(LollipopWidgets.this, mSamples[1].activityClass));
			}
		});
        floatButton3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				toggle();
				startActivity(new Intent(LollipopWidgets.this, mSamples[2].activityClass));
			}
		});
        
        fullView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.e("TAG"," clicked.. " + fullView.getVisibility());
				if(fullView.getVisibility()==View.VISIBLE) {
					toggle();
				}
			}
		});
        
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.about) {
            TextView content = (TextView) getLayoutInflater().inflate(R.layout.about_view, null);
            content.setMovementMethod(LinkMovementMethod.getInstance());
            content.setText(Html.fromHtml(getString(R.string.about_body)));
            new Dialog(LollipopWidgets.this, "Llel Anim", getString(R.string.about_body)).show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void collapse() {
        if (floatMenu.mExpanded) {
        	floatMenu.mExpanded = false;
        	floatMenu.mCollapseAnimation.start();
        	floatMenu.mExpandAnimation.cancel();
        }
      }

      public void toggle() {
        if (floatMenu.mExpanded) {
        	collapse();
        	fullView.startAnimation(slide_down);
        } else {
        	expand();
        	fullView.startAnimation(slide_up);
        }
      }

      public void expand() {
        if (!floatMenu.mExpanded) {
        	floatMenu.mExpanded = true;
        	floatMenu.mCollapseAnimation.cancel();
        	floatMenu.mExpandAnimation.start();
        }
      }

	@Override
	public void onRefresh() {
		Handler h = new Handler();
		h.postDelayed(r, 4000);
	}
	
	Runnable r = new Runnable() {
	    @Override
	    public void run(){
	        mListViewContainer.setRefreshing(false);
	    }
	};

	private void onCreateSwipeToRefresh(SwipeRefreshLayout refreshLayout) {

        refreshLayout.setOnRefreshListener(this);

        refreshLayout.setColorSchemeResources(
                R.color.refresh_progress_1,
                R.color.refresh_progress_2,
                R.color.refresh_progress_3);
    }
      
      
}
