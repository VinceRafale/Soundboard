package com.brysisoundboard;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Build;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import com.brysisoundboard.R;



public class ButtonAdapter extends BaseAdapter {
	
	//Keep track of whether sound is playing and name of song being played
	//Used in toggle feature
	private boolean soundPlaying = false;
	String namePlaying = "";
	
	
	//List of sounds. Position in array corresponds to buttons. (Going left to right)
	public  int[] sounds = {
			R.raw.king_of_cod,
			R.raw.mw3_rap,
			R.raw.black_ops_rap,
			R.raw.call_of_duty_ghosts,
			R.raw.bf4_rap,
			R.raw.minecraft_rap,
			R.raw.finding_diamonds,
			R.raw.trick_shots,
			R.raw.nuketown_rap,
			R.raw.battlefield_rap,
			R.raw.play_ghosts,
			R.raw.i_got_a_bow,
			R.raw.killionaire_rap_brysi,
			R.raw.im_a_call_of_duty_pro,
			R.raw.camper,
			R.raw.gta_5,
			R.raw.sick_sticks,
			R.raw.i_wish_i_had_a_jetpack,
			R.raw.broke_my_controller,
			R.raw.all_i_see_is_red,
			R.raw.need_for_speed_rap,
			R.raw.mortal_kombat_rap,
			R.raw.zombie_rap,
			R.raw.halo_4_rap,
			R.raw.cod_vs_halo_rap_battle,
			R.raw.black_ops_2_rap
	};
	
	//List of button texts. Position in array corresponds to buttons.
	public static String[] filesnames = {
			"King of COD",
			"Modern Warfare 3",
			"Black Ops",
			"COD Ghosts Rap",
			"Battlefield 4 Rap",
			"Minecraft",
			"Finding Diamonds",
			"Trick Shots",
			"Nuketown",
			"Battlefield 3 Rap",
			"Play Ghosts",
			"I Got A Bow",
			"Killionaire",
			"Call of Duty Pro",
			"Camper",
			"GTA 5 Rap",
			"Sick with the Sticks",
			"I Wish I Had a Jetpack",
			"Broke My Controller",
			"All I See is Red",
			"Need For Speed",
			"Mortal Kombat",
			"Zombie",
			"Halo 4",
			"Cod vs Halo",
			"Black Ops 2"
			
	};

	private Context context;
	
	//Holds sound file. Sound is assigned on click using the button's position to find the appropriate sound in the array.
	private MediaPlayer sound;

	public ButtonAdapter(Context applicationContext) {
		// TODO Auto-generated constructor stub
		context = applicationContext;
	}

	@Override
	public int getCount() {
		// number of data elements to be displayed. (Buttons with sounds)
		return sounds.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	//Return width of device's display
	public static int getWidth(Context mContext){
	    int width=0;
	    WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
	    Display display = wm.getDefaultDisplay();
	    if(Build.VERSION.SDK_INT>12){                   
	        Point size = new Point();
	        display.getSize(size);
	        width = size.x;
	    }
	    else{
	        width = display.getWidth();  // deprecated
	    }
	    return width;
	}
	
	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
	
		Button btn;
	
		//Calculate appropriate button size for device
		int totalWidth = getWidth(context);
		int sideLength = (totalWidth/3);
		
		//Create custom font object
		Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/Nobile-Regular.ttf");
		
		btn = new Button(context); 
		btn.setLayoutParams(new GridView.LayoutParams(sideLength, sideLength)); //Sets size of button. x, y coordinates.
		btn.setPadding(8, 8, 8, 8);
		btn.setText(filesnames[position]); //Sets text in button.
		btn.setTypeface(font); //Set custom font
		btn.setTextColor(Color.WHITE); 
		btn.setTextSize(16f);
		btn.setBackgroundResource(R.layout.custombutton); //Custom .9 files
		//btn.setTypeface(null, Typeface.BOLD); //Set text to bold.
		
		
		//This method is called when button is pressed by user.
		btn.setOnClickListener(new OnClickListener() {
			

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Toast.makeText(context, "" + position, Toast.LENGTH_SHORT).show(); //Shows position of button in array.
				
				if (soundPlaying)
				{
					if(namePlaying == filesnames[position]) //Allow for stop on double-click
					{
						soundPlaying = false;
						sound.stop();
						sound.release();
					}
					else
					{
						//Stop other sound
						soundPlaying = false;
						sound.stop();
						sound.release();

						//Initialize new sound
						sound = MediaPlayer.create(context, sounds[position]); 
						sound.start();
						soundPlaying = true;
						namePlaying = filesnames[position];
					}
				}
				else
				{
					//Initialize new sound
					sound = MediaPlayer.create(context, sounds[position]); //Assign correct sound to the MediaPlayer object.
					soundPlaying = true;
					namePlaying = filesnames[position];
					sound.start();
				}


				//Runs when sound finishes playing
				sound.setOnCompletionListener(new OnCompletionListener() {            
		            public void onCompletion(MediaPlayer mp) {
		            	
		                soundPlaying = false;
		                namePlaying = "";
		                sound.release();
		            }
		        });

			}
			
			
		});
		
	
	
	
	
	return btn;
	}

}
