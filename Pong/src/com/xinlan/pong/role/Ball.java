package com.xinlan.pong.role;

import com.xinlan.pong.util.Common;
import com.xinlan.pong.view.MainView;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Ball extends BaseShape {
	public static final int SPEED_RANGE = 10;
	public static final int RADIUS = 8;
	public int x, y;
	public int radius;
	private int speed_x, speed_y;
	private int dir_x, dir_y;
	private Paint paint;

	private int x_bound, y_bound;
	private Player mPlayer;

	public Ball(int init_x, int init_y, Player player) {
		x = init_x;
		y = init_y;
		
		mPlayer = player;
		radius = RADIUS;
		speed_x = Common.genRand(5, 10);
		speed_y = -Common.genRand(5, 10);
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.RED);
		x_bound = MainView.screenW - radius;
		y_bound = MainView.screenH / 2 - radius - player.height;
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawCircle(x, y, radius, paint);
	}

	@Override
	public void logic() {
		x += speed_x;
		if (x <= radius) {
			speed_x = -speed_x;
			x = radius;
		} else if (x >= x_bound) {
			speed_x = -speed_x;
			x = x_bound;
		}

		y += speed_y;
		if (y <= radius) {
			speed_y = -speed_y;
			y = radius;
		} else if (y >= y_bound) {
			if (isInRange()) {
				speed_y = -speed_y;
				y = y_bound;
			} else {
				MainView.GAME_STATE = 2;
			}
		}
	}

	private boolean isInRange() {
		if (x > mPlayer.left && x < mPlayer.left + mPlayer.width) {
			return true;
		}
		return false;
	}

	/**
	 */
	public void isHitted() {
		speed_y = -speed_y;
	}
}// end class
