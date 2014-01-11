package com.brysisoundboard;


import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.GridView;

import com.brysisoundboard.R;

public class myMenu extends Activity {
	
	MediaPlayer mpButtonCLick;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main); //Sets layout
		
		GridView gv = (GridView) findViewById(R.id.gridView); //Sets GridView layout to gridView id.
		gv.setAdapter(new ButtonAdapter(getApplicationContext())); //Sets button adapter to ButtonAdapter.java

	}
		
}
