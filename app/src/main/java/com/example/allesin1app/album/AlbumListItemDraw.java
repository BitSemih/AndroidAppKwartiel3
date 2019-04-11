package com.example.allesin1app.album;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

//Ondraw class for a album list item
public class AlbumListItemDraw extends View {
    private Paint paint;
    private int count, screenX, nextRect, maxRectanglesOnRow, nextRectHeight, rowNumber;
    private Context context;
    private Point size;

    //Constructors for different usages
    public AlbumListItemDraw(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public AlbumListItemDraw(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public AlbumListItemDraw(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        init();
    }

    public void init() {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor("#00D554"));

        //Accessing window manager to get info on screen width
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        size = new Point();
        display.getSize(size);
        this.screenX = size.x;
    }

    //Method for setting how much rectangles have to be drawn
    public void setRectangleCount(int count) {
        this.count = count;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        nextRect = 0;
        nextRectHeight = 0;
        rowNumber = 1;
        maxRectanglesOnRow = screenX / 80;
        for (int i = 0; i < this.count; i++) {
            //Draw a rectangle
            canvas.drawRect(30 + nextRect, 10 + nextRectHeight, 80 + nextRect, 20 + nextRectHeight, paint);
            //Add 80px for every pixel drawn
            nextRect += 80;
            //Check if rectangle iteration is gonna be drawn outside of screen
            if ((i + 2) > (maxRectanglesOnRow * rowNumber)) {
                //Add height to the base rectangle height
                nextRectHeight += 20;
                rowNumber++;
                //Resetting rectangle position so it is drawn from the beginning on a new line
                nextRect = 0;
            }
        }
    }
}
