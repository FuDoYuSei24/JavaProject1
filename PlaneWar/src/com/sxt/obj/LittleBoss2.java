package com.sxt.obj;

import com.sxt.GameWin;
import com.sxt.utils.GameUtils;

import java.awt.*;

//小BOSS2的类
public class LittleBoss2 extends GameObj{
    int health=10;
    @Override
    public Image getImg() {
        return super.getImg();
    }

    public LittleBoss2() {
        super();
    }

    public LittleBoss2(Image img, int width, int height, int x, int y, double speed, GameWin frame) {
        super(img, width, height, x, y, speed, frame);
    }

    public LittleBoss2(int x, int y) {
        super(x, y);
    }

    public LittleBoss2(Image img, int x, int y, double speed) {
        super(img, x, y, speed);
    }

    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
        if(y<150){
            y+=2;
        }else{
            x+=speed;
            if(x>400||x<10){
                speed=-speed;
            }
        }
        //1.小boss2与我方子弹碰撞，我方子弹消失，小boss2扣血,
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
                //当敌方小boss被击毁的时候才会出现补给品
                GiftObj giftObj=new GiftObj(this.x,this.y);
                GameUtils.giftObjList.add(giftObj);
                GameUtils.gameObjList.addAll(GameUtils.giftObjList);
                //我方子弹
                shellobj.setX(-100);
                shellobj.setY(-100);
                GameUtils.removeList.add(shellobj);
                //小boss2
                this.x=-200;
                this.y=-200;
                GameUtils.removeList.add(this);
                GameWin.score+=8;
            }
        }
        //2.小boss2与我方2级子弹碰撞，我方子弹消失，小boss1扣血,
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
                GiftObj giftObj=new GiftObj(this.x,this.y);
                GameUtils.giftObjList.add(giftObj);
                GameUtils.gameObjList.addAll(GameUtils.giftObjList);
                doubleshellobj.setX(-100);
                doubleshellobj.setY(-100);
                GameUtils.removeList.add(doubleshellobj);
                this.x=-200;
                this.y=-200;
                GameUtils.removeList.add(this);
                GameWin.score+=8;
            }

        }

        //3.小boss2与我方3级子弹碰撞，我方子弹消失，小boss1扣血,
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
                GiftObj giftObj=new GiftObj(this.x,this.y);
                GameUtils.giftObjList.add(giftObj);
                GameUtils.gameObjList.addAll(GameUtils.giftObjList);
                tripleshellobj.setX(-100);
                tripleshellobj.setY(-100);
                GameUtils.removeList.add(tripleshellobj);
                this.x=-200;
                this.y=-200;
                GameUtils.removeList.add(this);
                GameWin.score+=8;
            }

        }
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
