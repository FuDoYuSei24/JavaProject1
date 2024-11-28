package com.sxt.obj;

import com.sxt.GameWin;
import com.sxt.utils.GameUtils;

import java.awt.*;

//这个类是我方子弹的类
public class ShellObj extends GameObj {
    //重写构造方法，绘制方法，获取自身矩形方法
    @Override
    public Image getImg() {
        return super.getImg();
    }

    public ShellObj() {
        super();
    }

    public ShellObj(Image img, int width, int height, int x, int y, double speed, GameWin frame) {
        super(img, width, height, x, y, speed, frame);
    }

    public ShellObj(Image img, int x, int y, double speed) {
        super(img, x, y, speed);
    }

    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
        //实现子弹的移动，改变子弹的纵坐标
        y-=speed;
        if(y<=0){
            GameUtils.removeList.add(this);
        }

    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
