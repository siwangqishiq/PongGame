package com.xinlan.pong.abstracts;

import android.graphics.Canvas;
import android.view.MotionEvent;

public interface Controller {
	public void clickUpBtn();
	public void clickDownBtn();
	public void clickLeftBtn();
	public void clickRightBtn();
	public void clickAButton();
	public void clickBButton();
	public void draw(Canvas canvas);
	public boolean onTouch(MotionEvent event);
	public void logic();
}//end class
