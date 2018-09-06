package com.lianxi.flybird;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FlyBird {
	BufferedImage[] images;
	int x, y;
	int width, height;
	int size;// 鸟的大小用于碰撞检测
	int index;
	double g;// 重力加速度
	double t;// 两次位置的间隔时间
	double v0;// 初始上抛速度
	double speed;// 当前上抛速度
	double s;// 经过时间t后的位移
	double alpha;// 鸟的倾斜度

	/** 为该类添加构造方法，初始化属性变量 */
	public FlyBird()  {
		index=0;
		images=new BufferedImage[8];
		try {
			for (int j = 0; j < images.length; j++) {
				images[j] = ImageIO.read(new File("图片/"+j+".png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		width = images[index].getWidth();
		height = images[index].getHeight();
		x = (288-width)/2;
		y = (512-height)/2;
		size = 40;
		// 为计算鸟的位置的属性初始化
		g = 9.8; // 重力加速度
		v0 = 20; // 初始上抛速度
		t = 0.5; // 两次位置的间隔时间
		s = 0; // 经过时间t后的位移
		alpha = 0; // 鸟的倾斜度
	}

	/** 小鸟类的移动方法 */
	public void move() {
		index++;
		if (index==8) {
			index=0;
		}		
		s = v0 * t + g * t * t / 2;// 计算上抛运动位移
		y = y - (int) s;// 计算鸟的坐标位置
		v0 = v0 - g * t;// 计算下次的速
		if (y>=400) {
			y=400;
		}
		alpha=Math.atan(s/8);
	}

	public void flappy() {// 重新设置初始速度，重新向上飞。
		v0 = 20;
	}

}
