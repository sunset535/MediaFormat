package com.zxdp.main;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.zxdp.adapter.HumanProgressBar;

public class TestProcessbar extends JFrame{
	public TestProcessbar() {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(350, 75);
        HumanProgressBar p = new HumanProgressBar();
        p.setValue(20);
        p.setBounds(15, 15, 300, 15);
        this.add(p);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TestProcessbar();
            }
        });
    }
}
