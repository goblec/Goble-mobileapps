package com.example.goblepedometer;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;


public class MainActivity extends Activity {
	private TextView textViewX;
	private TextView textViewY;
	private TextView textViewZ;
	private TextView textCalories;
	
	private TextView textSensitive;
	
	private TextView textViewSteps;
	
	private Button buttonReset;
	
	private SensorManager sensorManager;
	private float acceleration;
	
	private float previousY;
	private float currentY;
	private int numSteps1;
	
	private SeekBar seekBar;
	private int numSteps;
	private int threshold;
	
	private TextView textLat;
	private TextView textLong;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        textViewX = (TextView) findViewById(R.id.textViewX);
        textViewY = (TextView) findViewById(R.id.textViewY);
        //textViewZ = (TextView) findViewById(R.id.textViewZ);
        textViewSteps = (TextView) findViewById(R.id.textSteps);
        textSensitive = (TextView) findViewById(R.id.textSensitive);
        buttonReset = (Button)findViewById(R.id.buttonReset);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        textCalories = (TextView) findViewById(R.id.textCalories);
        
        seekBar.setProgress(10);
        seekBar.setOnSeekBarChangeListener(seekBarListener);
        threshold = 10;
        textSensitive.setText(String.valueOf(threshold));
        
        previousY= 0;
        currentY = 0;
        numSteps = 0;
        
        acceleration = 0.00f;
        
        enableAccelerometerListening();
        
        textLat = (TextView)findViewById(R.id.textLat);
        textLong = (TextView)findViewById(R.id.textLong);
        		
        LocationManager locManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        LocationListener locListener = new MyLocationListener();
        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locListener);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
        
    }
    
    

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void enableAccelerometerListening(){
    	sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    	sensorManager.registerListener(sensorEventListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }
    private SensorEventListener sensorEventListener = new SensorEventListener(){
    	public void onSensorChanged(SensorEvent event){
    		float x = event.values[0];
    		float y = event.values[1];
    		//float z = event.values[2];
    		
    		currentY = y;
    		
    		if (Math.abs(currentY- previousY)> threshold){
    			numSteps++;
    			textViewSteps.setText(String.valueOf(numSteps));
    		}
    		textViewX.setText(String.valueOf(x));
    		textViewY.setText(String.valueOf(y));
    		//textViewZ.setText(String.valueOf(z));
    		previousY =y;
    	
    	}

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			
		}
    
    };
    	public void resetSteps(View v){
    		numSteps = 0;
    		textViewSteps.setText(String.valueOf(numSteps));
    		
    	}
    	private OnSeekBarChangeListener seekBarListener = new OnSeekBarChangeListener()
    	{ 
    		public void onProgressChanged (SeekBar seekbar, int progress, boolean fromUser){
    		threshold = seekBar.getProgress();
    		textSensitive.setText(String.valueOf(threshold));
    	}
    	public void onStartTrackingTouch (SeekBar seekBar){
    		
    	}
    	public void onStopTrackingTouch(SeekBar seekbar){
    		
    	}
		
};

	public class MyLocationListener implements LocationListener
{
	public void onLocationChanged (Location loc){
		double lat = loc.getLatitude();
		double lon = loc.getLongitude();
		
		textLat.setText(String.valueOf(lat));
		textLong.setText(String.valueOf(lon));
	}
	public void onProviderDisabled(String provider){
	
}
	public void onProviderEnabled(String provider){
	
}
	public void onStatusChanged(String provider, int status, Bundle extras){
	
}
}

}
