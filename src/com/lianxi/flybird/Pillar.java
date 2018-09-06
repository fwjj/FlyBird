package com.lianxi.flybird;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Pillar {
	int x;
	int y;
	BufferedImage imageup;
	BufferedImage imagedown;
	int width;
	int height;
	int gap;
	int distance;
	int speed;

	public Pillar(int a) {
		speed=2;
		distance = 288;
		gap = 150;
		x = 288 + distance * (a - 1);
		try {
			imageup = ImageIO.read(new File("图片/zzshang.png"));
			imagedown = ImageIO.read(new File("图片/zzxia.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		height = imageup.getHeight();
		width = imageup.getWidth();
		// -90 .......-300
		y = -(int) (Math.random() * 210 + 90);

	}

	public void move() {
		x-=speed;
		if (x <= -width / 2) {
			x = distance * 2 - width / 2;
			y = -(int) (Math.random() * 210 + 90);
		}

	}

	public boolean hit(FlyBird bird) {
		if (bird.x>x-bird.width+10&&bird.x<x+width-10) {
			if (bird.y>y+height-10&&bird.y<y+height+gap-bird.height+10) {
				return false;
			}
			return true;
		}
		return false;

	}

}
