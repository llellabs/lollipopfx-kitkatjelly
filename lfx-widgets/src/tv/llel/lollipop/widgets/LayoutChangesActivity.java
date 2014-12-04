
package tv.llel.lollipop.widgets;

import java.util.ArrayList;

import tv.llel.lollipop.widgets.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class LayoutChangesActivity extends Activity {

    private ViewGroup mContainerView;

    private EditText edittext = null;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_changes);

        mContainerView = (ViewGroup) findViewById(R.id.container);
        edittext = (EditText) findViewById(R.id.editbox);
        
        edittext.setOnEditorActionListener(new OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if(!edittext.getEditableText().toString().trim().equals(""))
				{
					mylist.add(edittext.getEditableText().toString());
					addItem(mylist.size()-1);
					edittext.setText("");
				}
				return true;
			}
		});
    }

    private void addItem(int pos) {
        final ViewGroup newView = (ViewGroup) LayoutInflater.from(this).inflate(
                R.layout.list_item_example, mContainerView, false);

        ((TextView) newView.findViewById(android.R.id.text1)).setText(mylist.get(pos));

        newView.findViewById(R.id.delete_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContainerView.removeView(newView);
            }
        });

        mContainerView.addView(newView, 0);
    }

    ArrayList<String> mylist = new ArrayList<String>();
}
