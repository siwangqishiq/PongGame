package com.xinlan.pong.role;

import android.graphics.Canvas;

public abstract class BaseShape {
	protected int left;
	protected int top;
	protected int width;
	protected int height;

	public abstract void draw(Canvas canvas);

	public abstract void logic();
}
