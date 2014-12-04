#lollipopfx-kitkatjelly#

Android Lollipop Widgets/Effects for Kitkat, Jelly Bean Apps: Implementation of Android 5.0 Widgets to the Pre-Lollipop versions (Jelly Bean/Kitkat):
* RecyclerView
* FloatingActionButton (FAB)
* FAB with Animations
* Pull to Refresh Animation 
* Lollipop Design Alert-Dialogs
* Ripple Animations on Button Click
* Other L Smooth Animations

### RecyclerView ###

It's a kind of ListView/GridView (or) a replacement for ListView & Gridview in the latest version of Android release - a flexible view for providing a limited window into a large data-set.

[Requires 'android-support-v7-recyclerview.jar'] 

Add this view in your XML:

```
    <android.support.v7.widget.RecyclerView
        android:id="@+id/recycler_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:cacheColorHint="@android:color/transparent"
        android:scrollbars="vertical" />
```

### FloatingActionButton and FAB with Animations ###

* Add the "tv.llel.floatingactionbutton.FloatingActionsMenu" to your layout XML file. 
The button should be placed in the bottom right corner of the screen. 
* Add your "tv.llel.floatingactionbutton.FloatingActionButton"s to the FloatingActionsMenu layout. Can add as many FloatingActionButtons as required.

```
    <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:fab="http://schemas.android.com/apk/res-auto"
        android:background="@color/background"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
            
            <View android:id="@+id/full_view"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:visibility="gone"
            android:background="#80000000"/>
        
            <android.support.v4.widget.SwipeRefreshLayout
            android:id="@+id/swipeRefreshLayout_listView"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:layout_above="@+id/grid_load_more_progressBar"
            >
        
            <android.support.v7.widget.RecyclerView
            android:id="@+id/recycler_view"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:cacheColorHint="@android:color/transparent"
            android:scrollbars="vertical" />
         </android.support.v4.widget.SwipeRefreshLayout>
        
        <tv.llel.floatingactionbutton.FloatingActionsMenu
            android:id="@+id/multiple_actions"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_alignParentBottom="true"
            android:layout_alignParentEnd="true"
            android:layout_alignParentRight="true"
            android:layout_marginBottom="16dp"
            android:layout_marginEnd="16dp"
            android:layout_marginRight="14dp"
            fab:fab_addButtonColorNormal="@color/blue"
            fab:fab_addButtonColorPressed="@color/blue_pressed"
            fab:fab_addButtonPlusIconColor="@color/white" >

            <tv.llel.floatingactionbutton.FloatingActionButton
                android:id="@+id/fab_action1"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                fab:fab_colorNormal="@color/meganta_semi_transparent"
                fab:fab_colorPressed="@color/meganta_semi_transparent_pressed" />

            <tv.llel.floatingactionbutton.FloatingActionButton
                android:id="@+id/fab_ation2"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                fab:fab_colorNormal="@color/new_pink_semi_transparent"
                fab:fab_colorPressed="@color/new_pink_pressed_semi_transparent" />
            
            <tv.llel.floatingactionbutton.FloatingActionButton
                android:id="@+id/fab_ation3"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                fab:fab_colorNormal="@color/yellow_semi_transparent"
                fab:fab_colorPressed="@color/yellow_semi_transparent_pressed" />
        </tv.llel.floatingactionbutton.FloatingActionsMenu>
    </RelativeLayout>
```

You can add the Click listener for this FloatingMenu:

<pre>
    floatMenu.mAddButton.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
             toggle();
        }
    });
</pre>

The method toggle() is defined this way:

<pre>
    public void toggle() {
        if (floatMenu.mExpanded) {
        	collapse();
        	fullView.startAnimation(slide_down);
        } else {
        	expand();
        	fullView.startAnimation(slide_up);
        }
    }
</pre>

slide_down and slide_up animations are declared as:

<pre>
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
</pre>
