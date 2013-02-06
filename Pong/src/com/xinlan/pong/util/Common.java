package com.xinlan.pong.util;

import java.util.Random;

public class Common {
	
	public static int genRand(int min, int max) {
		return (new Random()).nextInt(max) % (max - min + 1) + min;
	}

	public static boolean isHit(int left1, int top1, int w1, int h1, int left2,
			int top2, int w2, int h2) {
		int x1 = left1 + w1 /2;
		int y1 = top1 + h1 /2;
		int x2 = left2 + w2 /2;
		int y2 = top2 + h2 /2;
		if (Math.abs(x1 - x2) < (w1/2+w2/2)-10
				&& Math.abs(y1 - y2) < (h1/2+h2/2)-10) {
			return true;
		}
		return false;
	}
	
	public static boolean isCircleHit(int x,int y,int radius,int left,int top,int width,int height){
		int x2 = left + width /2;
		int y2 = top + height /2;
		if (Math.abs(x - x2) < (radius+width/2)
				&& Math.abs(y - y2) < (radius+height/2)) {
			return true;
		}
		return false;
	}
}
