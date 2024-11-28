package com.sxt.obj;

import com.sxt.GameWin;

import java.awt.*;

public class WarningObj extends GameObj{
    @Override
    public Image getImg() {
        return super.getImg();
    }

    public WarningObj() {
        super();
    }

    public WarningObj(Image img, int width, int height, int x, int y, double speed, GameWin frame) {
        super(img, width, height, x, y, speed, frame);
    }

    public WarningObj(int x, int y) {
        super(x, y);
    }

    public WarningObj(Image img, int x, int y, double speed) {
        super(img, x, y, speed);
    }

    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
    }
}
