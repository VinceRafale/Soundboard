package com.brysisoundboard;


import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;

import com.brysisoundboard.R;
public class MainActivity extends Activity {
	
	MediaPlayer mpSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //Splash Screen
        
        //Set layout to splash.xml
        setContentView(R.layout.splash);
        
        //Create MediaPlayer object to play Brysi intro soundbite
        mpSplash = MediaPlayer.create(this, R.raw.brysi_intro);
        mpSplash.start();
        
        //Sets timer for how long splash screen should be displayed.
        Thread logoTimer = new Thread() {
        	public void run() {
        		try {
        			int logoTimer = 0; 
        			while (logoTimer < 3500){ //How long splash screen should be displayed.
        				sleep(100);
        				logoTimer += 100;
        			}
        			startActivity(new Intent("com.example.brysisoundboard.CLEARSCREEN")); //Sends program to MyMenu.java
        		} catch (InterruptedException e) {
        			e.printStackTrace();
        		}
        		
        		finally {
        			finish();
        		}
        	}
        	
        };
        
        logoTimer.start();
       
    }

    
    @Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mpSplash.release();
	}


	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mpSplash.pause();
	}


	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mpSplash.start();
	}


	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
}
