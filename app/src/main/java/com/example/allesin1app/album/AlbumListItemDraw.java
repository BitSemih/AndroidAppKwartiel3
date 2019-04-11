package com.example.allesin1app.album;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import java.util.jar.Attributes;

public class AlbumListItemDraw extends View {
    private Paint paint;
    private Rect rectangle;
    private int count, screenX, screenY, nextRect, maxRectanglesOnRow, nextRectHeight, rowNumber;
    private Context context;
    private Point size;

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

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        size = new Point();
        display.getSize(size);
        this.screenX = size.x;
        this.screenY = size.y;
    }

    public void rectangleCount(int count) {
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
            canvas.drawRect(30 + nextRect, 10 + nextRectHeight, 80 + nextRect, 20 + nextRectHeight, paint);
            nextRect += 80;
            if ((i + 2) > (maxRectanglesOnRow * rowNumber)) {
                nextRectHeight += 20;
                rowNumber++;
                nextRect = 0;
            }
        }
    }
}
