package com.sxt.obj;
//击败小boss后掉落的补给类

import com.sxt.GameWin;
import com.sxt.utils.GameUtils;

import java.awt.*;

public class GiftObj extends GameObj{
    public GiftObj() {
        super();
    }

    public GiftObj(Image img, int width, int height, int x, int y, double speed, GameWin frame) {
        super(img, width, height, x, y, speed, frame);
    }

    public GiftObj(int x, int y) {
        super(x, y);
    }

    public GiftObj(Image img, int x, int y, double speed) {
        super(img, x, y, speed);
    }

    @Override
    public void paintSelf(Graphics g) {
        super.img= GameUtils.giftImg;
        super.width=64;
        super.height=62;
        super.speed=1;
        super.paintSelf(g);
        y+=speed;

        if(y>=800){
            GameUtils.removeList.add(this);
        }
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
