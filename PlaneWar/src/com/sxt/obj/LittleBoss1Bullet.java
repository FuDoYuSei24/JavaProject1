package com.sxt.obj;

import com.sxt.GameWin;
import com.sxt.utils.GameUtils;

import java.awt.*;

//小boss1的子弹类
public class LittleBoss1Bullet extends GameObj {
    @Override
    public Image getImg() {
        return super.getImg();
    }

    public LittleBoss1Bullet() {
        super();
    }

    public LittleBoss1Bullet(Image img, int width, int height, int x, int y, double speed, GameWin frame) {
        super(img, width, height, x, y, speed, frame);
    }

    public LittleBoss1Bullet(int x, int y) {
        super(x, y);
    }

    public LittleBoss1Bullet(Image img, int x, int y, double speed) {
        super(img, x, y, speed);
    }

    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
        y+=speed;
        //越界判断
        if(y>=800){
            GameUtils.removeList.add(this);
        }
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
        //敌方小boss
    }
}
