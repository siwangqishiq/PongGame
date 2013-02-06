package com.xinlan.pong.role;

import java.util.LinkedList;

import com.xinlan.pong.view.MainView;


import android.graphics.Canvas;

public class GroupBox {
	public static final int X_NUM=12;
	public static final int Y_NUM=4;
	public static final int X_DELTA=10;
	public static final int Y_DELTA=10;
	
	private LinkedList<Box> boxes;
	private Ball mBall;
	
	public GroupBox(Ball mBall){
		this.mBall=mBall;
		boxes=new LinkedList<Box>();
		initBoxes();
	}
	
	private void initBoxes(){
		int startx=X_DELTA,starty=Y_DELTA;
		for(int i=0;i<Y_NUM;i++){
			for(int j=0;j<X_NUM;j++){
				Box box=new Box(startx,starty);
				boxes.add(box);
				startx+=(X_DELTA+Box.WIDTH);
			}//end for j
			startx=X_DELTA;
			starty+=(Y_DELTA+Box.HEIGHT);
		}//end for i
	}
	
	public void draw(Canvas canvas){
		for(Box box:boxes){
			box.draw(canvas);
		}//end for
	}
	
	public void logic(){
		for(int i=0;i<boxes.size();i++){
			Box box=boxes.get(i);
			if(box.isHit(mBall.x, mBall.y, mBall.radius)){
				if(box.isDead()){
					boxes.remove(box);
				}
				mBall.isHitted();
			}
		}//end for
		if(boxes.size()==0){
			MainView.GAME_STATE=3;
		}
	}
}
