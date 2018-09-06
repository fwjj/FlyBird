package com.lianxi.flybird;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Floor {
	int x;
	int y;
	BufferedImage image;
	int speed;
	int width;

	public Floor() {
		x = 0;
		y = 400;
		try {
			image = ImageIO.read(new File("图片/ground1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		width = image.getWidth();
		speed = 1;
	}

	public void move() {
		x-=speed;
		if (x==288-336) {
			x=0;
		}
	}

	public boolean hit(FlyBird flyBird) {
		if (flyBird.y+flyBird.height>=y+5) {
			return true;
		}
		return false;

	}
}
