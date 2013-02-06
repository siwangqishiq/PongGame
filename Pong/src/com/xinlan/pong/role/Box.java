package com.xinlan.pong.role;


import com.xinlan.pong.util.Common;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Box extends BaseShape {
	public static final int WIDTH = 32;
	public static final int HEIGHT = 10;

	private Paint paint;
	private Paint paint2;
	
	private int life=2;
	public Box(int init_x, int init_y) {
		this.left = init_x;
		this.top = init_y;
		this.width = WIDTH;
		this.height = HEIGHT;
		paint = new Paint();
		paint2 = new Paint();
		paint.setColor(Color.YELLOW);
		paint2.setColor(Color.CYAN);
	}

	@Override
	public void draw(Canvas canvas) {
		switch(life){
		case 1:
			canvas.drawRect(left, top, left + width, top + height, paint2);
			break;
		case 2:
			canvas.drawRect(left, top, left + width, top + height, paint);
			break;
		}//end switch
	}

	@Override
	public void logic() {
	}

	public boolean isHit(int ballLeft, int ballTop, int ballRadius) {
		if(Common.isCircleHit(ballLeft, ballTop, ballRadius, left, top,
				width, height)){
			life--;
			return true;
		}
		return false;
	}
	
	public boolean isDead(){
		return life<=0?true:false;
	}
}// end class
