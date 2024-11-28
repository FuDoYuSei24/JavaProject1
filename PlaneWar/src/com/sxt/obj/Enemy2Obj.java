package com.sxt.obj;

import com.sxt.GameWin;
import com.sxt.utils.GameUtils;

import java.awt.*;

//敌人2的类
public class Enemy2Obj extends GameObj{
    //敌方大飞机有血量，2点
    int health=2;
    public Enemy2Obj() {
        super();
    }

    public Enemy2Obj(Image img, int width, int height, int x, int y, double speed, GameWin frame) {
        super(img, width, height, x, y, speed, frame);
    }

    public Enemy2Obj(Image img, int x, int y, double speed) {
        super(img, x, y, speed);
    }

    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
        y+=speed;
        //1.检测我方飞机子弹与敌方大飞机的碰撞
        //敌方大飞机需要我们的子弹打中三次才会消失
        for (ShellObj shellObj: GameUtils.shellObjList) {
            if(this.getRec().intersects(shellObj.getRec())==true&&health>0){//二者发生碰撞且大飞机的血量大于零
                //我方子弹消失
                shellObj.setX(-100);
                shellObj.setY(-100);
                GameUtils.removeList.add(shellObj);
                //敌方大飞机血量减少
                health--;
            } else if (this.getRec().intersects(shellObj.getRec())==true&&health<=0) {
                //发生碰撞，产生爆炸
                //创建爆炸对象
                ExplodeObj explodeObj=new ExplodeObj(x,y);
                //放入到爆炸集合
                GameUtils.explodeObjList.add(explodeObj);
                //由于爆炸发生一次，所以还要放入移出集合
                GameUtils.removeList.add(explodeObj);
                //我方子弹消失
                shellObj.setX(-100);
                shellObj.setY(-100);
                GameUtils.removeList.add(shellObj);
                //大飞机消失
                this.x=-200;
                this.y=-200;
                GameUtils.removeList.add(this);
                GameWin.score+=3;
                
            }

        }

        //2.检测我方2级飞机子弹与敌方大飞机的碰撞
        //敌方大飞机需要我们的子弹打中三次才会消失
        for (DoubleShellObj doubleshellObj: GameUtils.doubleshellObjList) {
            if(this.getRec().intersects(doubleshellObj.getRec())==true&&health>0){//二者发生碰撞且大飞机的血量大于零
                doubleshellObj.setX(-100);
                doubleshellObj.setY(-100);
                GameUtils.removeList.add(doubleshellObj);
                health-=3;
            } else if (this.getRec().intersects(doubleshellObj.getRec())==true&&health<=0) {
                ExplodeObj explodeObj=new ExplodeObj(x,y);
                GameUtils.explodeObjList.add(explodeObj);
                doubleshellObj.setX(-100);
                doubleshellObj.setY(-100);
                GameUtils.removeList.add(doubleshellObj);
                this.x=-200;
                this.y=-200;
                GameUtils.removeList.add(this);
                GameWin.score+=3;

            }

        }

        //3.检测我方3级飞机子弹与敌方大飞机的碰撞
        //敌方大飞机需要我们的子弹打中三次才会消失
        for (TripleShellObj tripleshellObj: GameUtils.tripleshellObjList) {
            if(this.getRec().intersects(tripleshellObj.getRec())==true&&health>0){//二者发生碰撞且大飞机的血量大于零
                tripleshellObj.setX(-100);
                tripleshellObj.setY(-100);
                GameUtils.removeList.add(tripleshellObj);
                health-=6;
            } else if (this.getRec().intersects(tripleshellObj.getRec())==true&&health<=0) {
                ExplodeObj explodeObj=new ExplodeObj(x,y);
                GameUtils.explodeObjList.add(explodeObj);
                GameUtils.removeList.add(explodeObj);
                tripleshellObj.setX(-100);
                tripleshellObj.setY(-100);
                GameUtils.removeList.add(tripleshellObj);
                this.x=-200;
                this.y=-200;
                GameUtils.removeList.add(this);
                GameWin.score+=3;

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
