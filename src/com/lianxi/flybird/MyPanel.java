package com.lianxi.flybird;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MyPanel extends JPanel implements MouseListener {
	// 小鸟，柱子，地板
	Floor floor;
	Pillar pillar1;
	Pillar pillar2;
	FlyBird bird;
	BufferedImage bg;
	BufferedImage start;
	BufferedImage pause;
	int top;
	int score;
	int status;

	public MyPanel() {
		top = score = 0;
		floor = new Floor();
		pillar1 = new Pillar(1);
		pillar2 = new Pillar(2);
		bird = new FlyBird();
		status = 1;
		try {
			bg = ImageIO.read(new File("图片/bg.png"));
			start = ImageIO.read(new File("图片/start.png"));
			pause = ImageIO.read(new File("图片/gameover.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		addMouseListener(this);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(bg, 0, 0, null);
		g.drawImage(pillar1.imageup, pillar1.x, pillar1.y, null);
		g.drawImage(pillar1.imagedown, pillar1.x, pillar1.y + pillar1.gap + pillar1.height, null);
		g.drawImage(pillar2.imageup, pillar2.x, pillar2.y, null);
		g.drawImage(pillar2.imagedown, pillar2.x, pillar2.y + pillar2.gap + pillar2.height, null);
		g.drawImage(floor.image, floor.x, floor.y, null);
		Graphics2D g2 = (Graphics2D) g;
		g2.rotate(-bird.alpha, bird.x + bird.width / 2, bird.y + bird.height / 2);
		g2.drawImage(bird.images[bird.index], bird.x, bird.y, null);
		g2.rotate(bird.alpha, bird.x + bird.width / 2, bird.y + bird.height / 2);
		g.setFont(new Font("宋体", 0, 20));
		g.drawString("score :" + score, 0, 50);
		g.drawString("top :" + top, 190, 50);
		if (status == 1) {
			g.drawImage(start, (288 - 116) / 2, (512 - 70) / 2, null);
		}
		if (status == 3) {
			g.drawImage(pause, (288 - 74) / 2, (512 - 48) / 2, null);
		}

	}

	public void action() {
		while (true) {

			if (status == 2) {
				floor.move();
				pillar1.move();
				pillar2.move();
				bird.move();
				if (floor.hit(bird) || pillar1.hit(bird)) {
					if (top<score) {
						top = score;
					}
					status = 3;
				}
				if (bird.x > pillar1.x + pillar1.width && bird.x <= pillar1.x + pillar1.width + pillar1.speed) {
					score++;
				} else if (bird.x > pillar2.x + pillar2.width && bird.x <= pillar2.x + pillar2.width + pillar2.speed) {
					score++;
				}
			}
			repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (status == 1) {
			status = 2;
		} else if (status == 3) {
			score = 0;
			floor = new Floor();
			pillar1 = new Pillar(1);
			pillar2 = new Pillar(2);
			bird = new FlyBird();
			status = 1;
		} else {
			bird.flappy();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

}
