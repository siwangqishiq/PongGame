package com.xinlan.pong.role;

import com.xinlan.pong.view.MainView;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Player extends BaseShape {
	public static int WIDTH = 380;
	public static final int HEIGHT = 10;
	private Paint paint;
	private int top;
	private int speed;

	public Player() {
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.BLUE);
		left = (MainView.screenW - WIDTH) / 2;
		top=MainView.screenH/2-HEIGHT;
		width=WIDTH;
		height=HEIGHT;
		speed=10;
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawRect(new Rect(left,top,left+width,top+height), paint);
		
	}

	@Override
	public void logic() {
		
	}

	public void toLeft() {
		left-=speed;
		if(left<=0){
			left=0;
		}
	}

	public void toRight() {
		left+=speed;
		int bound=MainView.screenW-width;
		if(left>=bound){
			left=bound;
		}
	}

}// end class
