package com.netcracker;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class Button extends JPanel implements MouseMotionListener{
    protected JButton cmd;
    protected JButton cm;
    Button()
    {
        JFrame frm =new JFrame();
        frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frm.setSize(1000, 1000);
        frm.add(this);
        /*  frm.dispose();
        frm.setUndecorated(true);
        frm.setVisible(true);*/
        //   this.setLayout(new FlowLayout());
        this.add(cmd=new JButton(""));
        this.add( cm= new JButton(""));
        cm.setVisible(false);
        // cmd.setFocusPainted(false);
        cmd.setIcon(getImageIcon("a.png"));
        cmd.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent arg0) {
                cmd.setVisible(false);
                cm.setVisible(true);
                cm.setBackground(new Color(200,255,200));
                cm.setIcon(getImageIcon("b.png"));
                // frm.dispose();


            }

        });
        cm.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent arg0) {
                frm.dispose();
            }

        });
        frm.getContentPane().addMouseMotionListener(this);
        frm.setVisible(true);
    }

    ImageIcon getImageIcon(String file)
    {
        return new ImageIcon(file);
    }



    public void mouseDragged (MouseEvent e){
        if (e.getModifiers() == 16) this.MoveGetdirection(e.getX(), e.getY());

    }
    public void mouseMoved (MouseEvent e)
    {
        this.MoveGetdirection(e.getX(), e.getY());
    }


    int Rad;
    protected void MoveGetdirection ( int x_position,
                                      int y_position)
    {
        Rad = (int) (cmd.getWidth() * 1.5);
        int x_centerbutton = cmd.getLocation().x + (cmd.getWidth() / 2);
        int y_centerbutton = cmd.getLocation().y + (cmd.getHeight() / 2);
        int rast = (int) (2 * Math.sqrt(Math.pow(x_position - x_centerbutton, 2) + Math.pow(y_position - y_centerbutton, 2)));
        if (rast < Rad) {
            int dx = Rad - rast;
            int dy = Rad - rast;

            if (x_position > x_centerbutton) {
                dx = -dx;
            }
            if (y_position > y_centerbutton) {
                dy = -dy;
            }
            this.MoveDCmd(dx, dy, x_centerbutton, y_centerbutton);
        }
    }

    int bonus = 10;
    protected void MoveDCmd ( int dx, int dy, int x_centerbutton, int y_centerbutton)
    {

        if (dx >= 0) {
            if (x_centerbutton + cmd.getWidth() / 2 + dx < this.getWidth()) {
                cmd.setLocation(cmd.getLocation().x += dx, cmd.getLocation().y);
            } else {
                cmd.setLocation(bonus, cmd.getLocation().y);
            }
        }

        if (dy >= 0) {
            if (y_centerbutton + cmd.getHeight() / 2 + dy < this.getHeight()) {
                cmd.setLocation(cmd.getLocation().x, cmd.getLocation().y += dy);
            } else {
                cmd.setLocation(cmd.getLocation().x, bonus);

            }
        }

        if (dx <= 0) {
            if (x_centerbutton - cmd.getWidth() / 2 > -dx) {
                cmd.setLocation(cmd.getLocation().x += dx, cmd.getLocation().y);
            } else {
                cmd.setLocation(this.getWidth() - (cmd.getWidth() + bonus), cmd.getLocation().y);
            }
        }

        if (dy <= 0) {
            if (y_centerbutton - (cmd.getHeight() / 2) > -dy) {
                cmd.setLocation(cmd.getLocation().x, cmd.getLocation().y += dy);
            } else {
                cmd.setLocation(cmd.getLocation().x, this.getHeight() - (cmd.getHeight() + bonus));
            }
        }
    }

}
