package net.metabaron.marketsoverlay;

import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Markers_OverlayActivity extends MapActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        MapView mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
        
        List<Overlay> mapOverlays = mapView.getOverlays();
        Drawable drawable = this.getResources().getDrawable(R.drawable.red);
        Log.d("TextView", "****");
        Log.d("TextView", "Drawable width: " + drawable.getMinimumWidth() + " pixels and height: " + drawable.getMinimumHeight() + " pixels.");
        Log.d("TextView", "Drawable intrisinc width: " + drawable.getIntrinsicWidth() + " pixels and intrisinc height: " + drawable.getIntrinsicHeight() + " pixels.");
        MarkersItemizedOverlay itemizedoverlay = new MarkersItemizedOverlay(drawable, this);
        
        //Getting all information about the text being displayed    
        TextView textView = new TextView(this);
        textView.setText("123456");
        textView.setTextColor(0xFF000000);
        textView.layout(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        
        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.layout(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        relativeLayout.setBackgroundResource(R.drawable.red);
        relativeLayout.addView(textView);
        Bitmap viewBitmap = Bitmap.createBitmap(drawable.getMinimumWidth(), drawable.getMinimumHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(viewBitmap);
        relativeLayout.draw(canvas);
        BitmapDrawable bmd = new BitmapDrawable(viewBitmap);

        ImageView imageView = (ImageView) findViewById(R.id.imageView1);
        imageView.layout(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        imageView.setImageBitmap(bmd.getBitmap());
        
        GeoPoint point = new GeoPoint(19240000,-99120000);
        OverlayItem overlayitem = new OverlayItem(point, "Hola, Mundo!", "I'm in Mexico City!");
        overlayitem.setMarker(drawable);
        itemizedoverlay.addOverlay(overlayitem);
        mapOverlays.add(itemizedoverlay);
        
        //Map invisible
        mapView.setVisibility(mapView.INVISIBLE);
        
        float textViewSize = textView.getTextSize();
        float textViewScaleX = textView.getTextScaleX();
        Log.d("TextView", "***** You are writing: " + textView.getText());
        Log.d("TextView", "font size (pixel): " + textViewSize + " and text scale horizontally: " + textViewScaleX);
        //Padding?
        //Calculating total text size
        float textViewSizeX = textViewSize * textView.length() * textViewScaleX;
        Log.d("TextView", "Text size: " + textViewSizeX);
        float scale = getResources().getDisplayMetrics().density;
        Log.d("TextView", "Screen density scale: " + String.valueOf(scale));
    }
    
    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
}