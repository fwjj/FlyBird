package com.lianxi.flybird;

import javax.swing.JFrame;

public class FlyBirdFrame {
	public static void main(String[] args) {
		JFrame jf = new JFrame();
		jf.setTitle("FlyBird");
		jf.setSize(288, 512);
		jf.setLocation(500, 100);
		jf.setDefaultCloseOperation(3);
		MyPanel mp = new MyPanel();
		jf.add(mp);
		jf.setResizable(false);
		jf.setVisible(true);
		mp.action();
	}
}
