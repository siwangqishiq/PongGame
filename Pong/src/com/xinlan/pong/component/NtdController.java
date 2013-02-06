package com.xinlan.pong.component;

import com.xinlan.pong.abstracts.Controller;
import com.xinlan.pong.role.Player;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;

public class NtdController implements Controller {
	public static final String TAG = "Controller";
	public static final int DIR_BTN_WIDTH = 50;
	public static final int DIR_BTN_HEIGHT = 50;
	public static final int DIR_RADIUS = 50;
	public static final int BTN_WIDTH = 50;
	public static final int BTN_RADIUS = 40;
	public static int TOP = 100;

	private int SCREEN_WIDTH, SCREEN_HEIGHT;
	private int DELTA = 5;
	
	private float startPointX, startPointY;
	private float aBtnX, aBtnY, bBtnX, bBtnY;
	private Point[] dirs;
	private Paint paint;

	private boolean dirUpIsPressed = false;
	private boolean dirDownIsPressed=false;
	private boolean dirLeftIsPressed=false;
	private boolean dirRightIsPressed=false;
	private boolean aBtnPressed = false;
	private boolean bBtnPressed = false;
	
	private Player mPlayer;
	

	public NtdController(int screenWidth, int screenHeight,Player player) {
		this.SCREEN_WIDTH = screenWidth;
		this.SCREEN_HEIGHT = screenHeight;
		paint = new Paint();
		paint.setColor(Color.GRAY);
		paint.setAntiAlias(true);
		TOP = screenHeight >> 1;
		startPointX = (float) DELTA;
		startPointY = (float) TOP + DIR_BTN_HEIGHT;
		mPlayer=player;

		dirs = new Point[13];

		aBtnX = SCREEN_WIDTH - (DELTA + BTN_RADIUS) * 4;
		aBtnY = TOP + BTN_RADIUS;
		bBtnX = SCREEN_WIDTH - (DELTA + BTN_RADIUS);
		bBtnY = TOP + 2 * BTN_RADIUS + 2 * DELTA;

		dirs[0] = new Point(startPointX, startPointY);
		dirs[1] = new Point(startPointX + DIR_BTN_HEIGHT, startPointY);
		dirs[2] = new Point(startPointX + DIR_BTN_HEIGHT, TOP);
		dirs[3] = new Point(startPointX + DIR_BTN_HEIGHT + DIR_BTN_WIDTH, TOP);
		dirs[4] = new Point(startPointX + DIR_BTN_HEIGHT + DIR_BTN_WIDTH, TOP
				+ DIR_BTN_HEIGHT);
		dirs[5] = new Point(startPointX + DIR_BTN_HEIGHT + DIR_BTN_WIDTH
				+ DIR_BTN_HEIGHT, TOP + DIR_BTN_HEIGHT);
		dirs[6] = new Point(startPointX + DIR_BTN_HEIGHT + DIR_BTN_WIDTH
				+ DIR_BTN_HEIGHT, TOP + DIR_BTN_HEIGHT + DIR_BTN_WIDTH);
		dirs[7] = new Point(startPointX + DIR_BTN_HEIGHT + DIR_BTN_WIDTH, TOP
				+ DIR_BTN_HEIGHT + DIR_BTN_WIDTH);
		dirs[8] = new Point(startPointX + DIR_BTN_HEIGHT + DIR_BTN_WIDTH, TOP
				+ DIR_BTN_HEIGHT + DIR_BTN_WIDTH + DIR_BTN_HEIGHT);
		dirs[9] = new Point(startPointX + DIR_BTN_HEIGHT, TOP + DIR_BTN_HEIGHT
				+ DIR_BTN_WIDTH + DIR_BTN_HEIGHT);
		dirs[10] = new Point(startPointX + DIR_BTN_HEIGHT, TOP + DIR_BTN_HEIGHT
				+ DIR_BTN_WIDTH);
		dirs[11] = new Point(startPointX, TOP + DIR_BTN_HEIGHT + DIR_BTN_WIDTH);
		dirs[12] = new Point(startPointX, startPointY);

	}

	public void draw(Canvas canvas) {
		drawCross(canvas);
		drawBtn(canvas);
	}

	private void drawBtn(Canvas canvas) {
		canvas.drawCircle(aBtnX, aBtnY, BTN_RADIUS, paint);
		canvas.drawCircle(bBtnX, bBtnY, BTN_RADIUS, paint);
	}

	@Override
	public boolean onTouch(MotionEvent event) {
		float x = event.getX(), y = event.getY();
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			dispatchTouch(x, y,true);
			break;
		case MotionEvent.ACTION_MOVE:
			dispatchTouch(x, y,true);
			break;
		case MotionEvent.ACTION_UP:
			dirUpIsPressed=false;
			dirDownIsPressed=false;
			dirRightIsPressed=false;
			dirLeftIsPressed=false;
			break;
		}//end class
		return true;
	}
	
	public void logic(){
		if(dirUpIsPressed){
			clickUpBtn();
		}
		if(dirDownIsPressed){
			clickDownBtn();
		}
		if(dirRightIsPressed){
			clickRightBtn();
		}
		if(dirLeftIsPressed){
			clickLeftBtn();
		}
	}

	private void dispatchTouch(float x, float y,boolean isPress) {
		if (x >= dirs[0].x && x <= dirs[0].x + DIR_BTN_HEIGHT && y >= dirs[0].y
				&& y <= dirs[0].y + DIR_BTN_WIDTH) {
			dirLeftIsPressed=isPress;
			//clickLeftBtn();
		}
		if (x >= dirs[2].x && x <= dirs[2].x + DIR_BTN_WIDTH && y >= dirs[2].y
				&& y <= dirs[2].y + DIR_BTN_HEIGHT) {
			dirUpIsPressed=isPress;
			//clickUpBtn();
		}
		if (x >= dirs[4].x && x <= dirs[4].x + DIR_BTN_HEIGHT && y >= dirs[4].y
				&& y <= dirs[4].y + DIR_BTN_WIDTH) {
			dirRightIsPressed=isPress;
			//clickRightBtn();
		}
		if (x >= dirs[10].x && x <= dirs[10].x + DIR_BTN_WIDTH
				&& y >= dirs[10].y && y <= dirs[10].y + DIR_BTN_HEIGHT) {
			dirDownIsPressed=isPress;
			//clickDownBtn();
		}
		if ((x - aBtnX) * (x - aBtnX) + (y - aBtnY) * (y - aBtnY) <= BTN_RADIUS
				* BTN_RADIUS) {
		}
		if ((x - bBtnX) * (x - bBtnX) + (y - bBtnY) * (y - bBtnY) <= BTN_RADIUS
				* BTN_RADIUS) {
		}
	}

	private void drawCross(Canvas canvas) {
		Path path = new Path();
		path.moveTo(startPointX, startPointY);
		for (int i = 1; i < dirs.length; i++) {
			Point p = dirs[i];
			path.lineTo(p.x, p.y);
		}
		canvas.drawPath(path, paint);
	}

	class Point {
		float x;
		float y;

		public Point(float x, float y) {
			this.x = x;
			this.y = y;
		}
	}// end inner class

	@Override
	public void clickUpBtn() {
	}

	@Override
	public void clickDownBtn() {
	}

	@Override
	public void clickLeftBtn() {
		mPlayer.toLeft();
	}

	@Override
	public void clickRightBtn() {
		mPlayer.toRight();
	}

	@Override
	public void clickAButton() {
		
	}

	@Override
	public void clickBButton() {
	}
}// end class
