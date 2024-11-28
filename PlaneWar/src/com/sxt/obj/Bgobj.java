package com.sxt.obj;

import com.sxt.GameWin;

import java.awt.*;

//这个类是背景类
//背景类要继承Gameobj类
public class Bgobj extends GameObj{
    //重写构造方法和paint方法
    public Bgobj() {
        super();
    }

    public Bgobj(Image img, int width, int height, int x, int y, double speed, GameWin frame) {
        super(img, width, height, x, y, speed, frame);
    }

    public Bgobj(Image img, int x, int y, double speed) {
        super(img, x, y, speed);
    }

    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
        y+=speed;
        //背景图不是无限长的，需要让背景图绘制到头后重新绘制
        if(y>=0){
            y=-1800;
        }
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
