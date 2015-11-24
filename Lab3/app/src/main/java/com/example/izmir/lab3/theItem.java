package com.example.izmir.lab3;

/**
 * Created by Izmir on 2015-11-24.
 */
import android.graphics.Color;
import android.graphics.Paint;
import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

public class theItem extends View {
    String name;

    public theItem(Context context, String theName) {
        super(context);
        name = theName;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint items = new Paint();
        items.setColor(Color.BLACK);
        items.setTextSize(50);

        Paint customColor = new Paint();
        customColor.setColor(Color.parseColor("#fafafa"));
        customColor.setStyle(Paint.Style.FILL);
        canvas.drawPaint(customColor);

        canvas.drawText(name, 20, 70, items);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){

        //Sets the dimensions for the item.
        setMeasuredDimension(500, 100);
    }
}