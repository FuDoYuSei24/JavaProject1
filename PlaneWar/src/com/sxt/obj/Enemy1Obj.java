package com.sxt.obj;

import com.sxt.GameWin;
import com.sxt.utils.GameUtils;

import java.awt.*;

//敌人1的类
public class Enemy1Obj extends GameObj{
    @Override
    public Image getImg() {
        return super.getImg();
    }

    public Enemy1Obj() {
        super();
    }

    public Enemy1Obj(Image img, int width, int height, int x, int y, double speed, GameWin frame) {
        super(img, width, height, x, y, speed, frame);
    }

    public Enemy1Obj(Image img, int x, int y, double speed) {
        super(img, x, y, speed);
    }

    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
        y+=speed;
        //1.检测敌方小飞机与我方1级子弹的碰撞
        //碰撞后二者消失
        for (ShellObj shellObj: GameUtils.shellObjList) {
            if (this.getRec().intersects(shellObj.getRec())==true){
                //发生碰撞，产生爆炸
                //创建爆炸对象
                ExplodeObj explodeObj=new ExplodeObj(x,y);
                //放入到爆炸集合
                GameUtils.explodeObjList.add(explodeObj);
                //由于爆炸发生一次，所以还要放入移出集合
                GameUtils.removeList.add(explodeObj);
                //移除
                shellObj.setX(-100);
                shellObj.setY(-100);
                this.x=-200;
                this.y=-200;
                GameUtils.removeList.add(shellObj);
                GameUtils.removeList.add(this);
                GameWin.score+=1;
            }
            
        }

        //2.检测敌方小飞机与我方2级子弹的碰撞
        //碰撞后二者消失
        for (DoubleShellObj doubleshellObj: GameUtils.doubleshellObjList) {
            if (this.getRec().intersects(doubleshellObj.getRec())==true){
                ExplodeObj explodeObj=new ExplodeObj(x,y);
                GameUtils.explodeObjList.add(explodeObj);
                GameUtils.removeList.add(explodeObj);
                doubleshellObj.setX(-100);
                doubleshellObj.setY(-100);
                this.x=-200;
                this.y=-200;
                GameUtils.removeList.add(doubleshellObj);
                GameUtils.removeList.add(this);
                GameWin.score+=1;
            }

        }

        //3.检测敌方小飞机与我方3级子弹的碰撞
        //碰撞后二者消失
        for (TripleShellObj tripleshellObj: GameUtils.tripleshellObjList) {
            if (this.getRec().intersects(tripleshellObj.getRec())==true){
                ExplodeObj explodeObj=new ExplodeObj(x,y);
                GameUtils.explodeObjList.add(explodeObj);
                GameUtils.removeList.add(explodeObj);
                tripleshellObj.setX(-100);
                tripleshellObj.setY(-100);
                this.x=-200;
                this.y=-200;
                GameUtils.removeList.add(tripleshellObj);
                GameUtils.removeList.add(this);
                GameWin.score+=1;
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
