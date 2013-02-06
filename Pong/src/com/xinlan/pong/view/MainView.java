package com.xinlan.pong.view;

import com.xinlan.pong.abstracts.Controller;
import com.xinlan.pong.component.NtdController;
import com.xinlan.pong.role.Ball;
import com.xinlan.pong.role.GroupBox;
import com.xinlan.pong.role.Player;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class MainView extends SurfaceView implements Callback, Runnable {
	private SurfaceHolder sfh;
	private Paint paint;
	private Thread th;
	private boolean flag;
	private Canvas canvas;
	private Context context;
	public static int screenW, screenH;
	private Resources res = this.getResources();
	
	public static int GAME_STATE=1;
	private Controller mController;
	private Player mPlayer;
	private Ball mBall;
	private GroupBox mGroupBox;

	public MainView(Context context) {
		super(context);
		this.context = context;
		sfh = this.getHolder();
		sfh.addCallback(this);
		paint = new Paint();
		paint.setColor(Color.WHITE);
		paint.setAntiAlias(true);
		setFocusable(true);
		setFocusableInTouchMode(true);
		// 设置背景常亮
		this.setKeepScreenOn(true);
	}

	public void surfaceCreated(SurfaceHolder holder) {
		screenW = this.getWidth();
		screenH = this.getHeight();
		init();
		flag = true;
		// 实例线程
		th = new Thread(this);
		// 启动线程
		th.start();
	}

	/**
	 * 初始化
	 */
	public void init() {
		GAME_STATE=1;
		mPlayer=new Player();
		mController = new NtdController(screenW,screenH,mPlayer);
		mBall = new Ball(screenW/2,screenH/2,mPlayer);
		mGroupBox = new GroupBox(mBall);
	}

	public void draw() {
		try {
			canvas = sfh.lockCanvas();
			if (canvas != null) {
				switch(GAME_STATE){
				case 1:
					canvas.drawColor(Color.BLACK);
					mController.draw(canvas);
					mPlayer.draw(canvas);
					mGroupBox.draw(canvas);
					mBall.draw(canvas);
					break;
				case 2:
					canvas.drawColor(Color.BLACK);
					Paint paint2=new Paint();
					paint2.setColor(Color.BLUE);
					paint2.setTextSize(25f);
					paint2.setTextSkewX(-1f);
					canvas.drawText("GAME OVER", 150, 200, paint2);
					mController.draw(canvas);
					mGroupBox.draw(canvas);
					break;
				case 3:
					canvas.drawColor(Color.BLACK);
					Paint paint3=new Paint();
					paint3.setColor(Color.RED);
					paint3.setTextSize(26f);
					paint3.setTextSkewX(-1f);
					canvas.drawText("你赢啦！", 150, 200, paint3);
					mController.draw(canvas);
					mGroupBox.draw(canvas);
					break;
				}
			}// end if
		} catch (Exception e) {
		} finally {
			if (canvas != null) {
				sfh.unlockCanvasAndPost(canvas);
			}
		}
	}

	public void logic() {
		mController.logic();
		mPlayer.logic();
		mGroupBox.logic();
		mBall.logic();
	}

	public void run() {
		while (flag) {
			long start = System.currentTimeMillis();
			draw();
			logic();
			long end = System.currentTimeMillis();
			//System.out.println(end - start);
			try {
				if (end - start < 30) {
					Thread.sleep(30- (end - start));
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}// end while
	}

	/**
	 * 监听触屏事件
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		mController.onTouch(event);
		return true;
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		flag = false;
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}
}// end class
