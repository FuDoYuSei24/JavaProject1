package com.sxt.obj;


import com.sxt.GameWin;
import com.sxt.utils.GameUtils;

import java.awt.*;

//小boos2的子弹类
public class LittleBoss2Bullet extends GameObj{
    int health=2;
    @Override
    public Image getImg() {
        return super.getImg();
    }

    public LittleBoss2Bullet() {
        super();
    }

    public LittleBoss2Bullet(Image img, int width, int height, int x, int y, double speed, GameWin frame) {
        super(img, width, height, x, y, speed, frame);
    }

    public LittleBoss2Bullet(int x, int y) {
        super(x, y);
    }

    public LittleBoss2Bullet(Image img, int x, int y, double speed) {
        super(img, x, y, speed);
    }

    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
        //实现追踪功能
        //y都是往前，故y只是简单地加speed
        y+=speed;
        //当子弹的坐标在我方飞机的坐标的左边，则x增加
        //当子弹的坐标在我方飞机的坐标的右边，则x减小
        //通过在GameWin中定义的索引值planeindex得到我方飞机的索引
        //getX得到我方飞机的横坐标
        this.x-=(this.x-GameUtils.gameObjList.get(GameWin.planeindex).getX())/30;

        //1.小boss2子弹与我方子弹碰撞，我方子弹消失，小boss2子弹扣血,
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
                //小boss2子弹
                this.x=-200;
                this.y=-200;
                GameUtils.removeList.add(this);
                GameWin.score+=2;
            }
        }
        //2.小boss2子弹与我方2级子弹碰撞，我方子弹消失，小boss2子弹扣血,
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
                GameWin.score+=2;
            }

        }

        //3.小boss2与我方3级子弹碰撞，我方子弹消失，小boss2子弹扣血,
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
                GameWin.score+=2;
            }

        }
        //4.越界判断
        if(y>=800){
            GameUtils.removeList.add(this);
        }
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
