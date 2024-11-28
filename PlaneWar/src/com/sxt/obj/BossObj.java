package com.sxt.obj;

import com.sxt.GameWin;
import com.sxt.utils.GameUtils;

import java.awt.*;

public class BossObj extends GameObj{
    int health=30;
    @Override
    public Image getImg() {
        return super.getImg();
    }

    public BossObj() {
        super();
    }

    public BossObj(Image img, int width, int height, int x, int y, double speed, GameWin frame) {
        super(img, width, height, x, y, speed, frame);
    }

    public BossObj(int x, int y) {
        super(x, y);
    }

    public BossObj(Image img, int x, int y, double speed) {
        super(img, x, y, speed);
    }

    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
        if(y<40){
            y+=speed;
        }else{
            x+=speed;
            if(x<0||x>360){
                speed=-speed;
            }
        }
        //1.boss与我方子弹碰撞，我方子弹消失，boss扣血,
        for (ShellObj shellobj: GameUtils.shellObjList) {
            if(this.getRec().intersects(shellobj.getRec())==true&&health>0){
                shellobj.setX(-100);
                shellobj.setY(-100);
                GameUtils.removeList.add(shellobj);
                health--;
            } else if (this.getRec().intersects(shellobj.getRec())==true&&health<=0) {
                //产生爆炸
                //创建爆炸对象
                ExplodeObj explodeObj = new ExplodeObj(x, y);
                //放入到爆炸集合
                GameUtils.explodeObjList.add(explodeObj);
                //由于爆炸发生一次，所以还要放入移出集合
                GameUtils.removeList.add(explodeObj);
                //我方子弹
                shellobj.setX(-100);
                shellobj.setY(-100);
                GameUtils.removeList.add(shellobj);
                //小boss1
                this.x=-200;
                this.y=-200;
                GameUtils.removeList.add(this);
                GameWin.state=4;
            }

        }

        //2.boss与我方2级子弹碰撞，我方子弹消失，boss扣血,
        for (DoubleShellObj doubleshellobj: GameUtils.doubleshellObjList) {
            if(this.getRec().intersects(doubleshellobj.getRec())==true&&health>0){
                doubleshellobj.setX(-100);
                doubleshellobj.setY(-100);
                GameUtils.removeList.add(doubleshellobj);
                health-=3;
            } else if (this.getRec().intersects(doubleshellobj.getRec())==true&&health<=0) {
                ExplodeObj explodeObj = new ExplodeObj(x, y);
                GameUtils.explodeObjList.add(explodeObj);
                GameUtils.removeList.add(explodeObj);
                doubleshellobj.setX(-100);
                doubleshellobj.setY(-100);
                GameUtils.removeList.add(doubleshellobj);
                this.x=-200;
                this.y=-200;
                GameUtils.removeList.add(this);
                GameWin.state=4;
            }

        }

        //3.boss与我方3级子弹碰撞，我方子弹消失，boss扣血,
        for (TripleShellObj tripleshellobj: GameUtils.tripleshellObjList) {
            if(this.getRec().intersects(tripleshellobj.getRec())==true&&health>0){
                tripleshellobj.setX(-100);
                tripleshellobj.setY(-100);
                GameUtils.removeList.add(tripleshellobj);
                health-=6;
            } else if (this.getRec().intersects(tripleshellobj.getRec())==true&&health<=0) {
                ExplodeObj explodeObj = new ExplodeObj(x, y);
                GameUtils.explodeObjList.add(explodeObj);
                GameUtils.removeList.add(explodeObj);
                tripleshellobj.setX(-100);
                tripleshellobj.setY(-100);
                GameUtils.removeList.add(tripleshellobj);
                this.x=-200;
                this.y=-200;
                GameUtils.removeList.add(this);
                GameWin.state=4;
            }

        }

        //4.绘制boss的血条
        g.setColor(Color.white);
        g.fillRect(200,40,200,10);
        g.setColor(Color.red);
        g.fillRect(200,40,health*200/30,10);


    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
