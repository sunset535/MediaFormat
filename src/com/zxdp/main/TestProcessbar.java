package com.zxdp.main;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.zxdp.test.HumanProgressBar;

public class TestProcessbar extends JFrame{
	private static final long serialVersionUID = 1L;

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
