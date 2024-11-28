package com.sxt.obj;

import com.sxt.GameWin;
import com.sxt.utils.GameUtils;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//我方飞机的类
//继承GameObj
public class PlaneObj extends GameObj{
    //我方飞机的血量
    public static int health=1;

    //记录我方飞机碰撞敌方补给的次数
    public static int times=0;
    LittleBoss1 littleBoss1=new LittleBoss1();
    LittleBoss2 littleBoss2=new LittleBoss2();

    //重写构造方式，碰撞检测方法，paintself方法
    public PlaneObj() {
        super();
    }

    public PlaneObj(Image img, int width, int height, int x, int y, double speed, GameWin frame) {
        super(img, width, height, x, y, speed, frame);
        //添加鼠标的移动事件
        //这里获取我方飞机的坐标
        //并让飞机的坐标等于我们鼠标的坐标分别减去飞机宽度和长度的一半
        this.frame.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                PlaneObj.super.x=e.getX()-19;//减去宽度的一半
                PlaneObj.super.y=e.getY()-20;//减去长度的一半
            }
        });
    }

    public PlaneObj(Image img, int x, int y, double speed) {
        super(img, x, y, speed);
    }

    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
        if(health>0) {
            //进行碰撞检测
            //1.我方飞机和敌方小飞机碰撞
            //（for-each循环的使用，冒号：后写需要遍历的集合，
            // 遍历出来的元素用冒号前方的这个Enemy1Obj类型来接收，enemy1Obj就是变量名
            // 在大括号中写判条件）
            //这里的判断条件就是我方飞机和敌方飞机是否发生碰撞，即两个矩形是否发生交叉
            //Rectangle类的intersects()方法用于判断两个矩形是否相交（即是否发生碰撞）
            for (Enemy1Obj enemy1Obj : GameUtils.enemy1ObjList) {
                if (this.getRec().intersects(enemy1Obj.getRec()) == true&&health==1) {
                    //1.1二者都发生爆炸
                    //发生碰撞，产生爆炸
                    //创建爆炸对象
                    ExplodeObj explodeObj = new ExplodeObj(x, y);
                    //放入到爆炸集合
                    GameUtils.explodeObjList.add(explodeObj);
                    //由于爆炸发生一次，所以还要放入移出集合
                    GameUtils.removeList.add(explodeObj);
                    //移动敌方飞机到画面外表示“消失”
                    enemy1Obj.setX(-100);
                    enemy1Obj.setY(-100);
                    //移动我方飞机到画面外表示“消失”
                    this.x = -200;
                    this.y = -200;
                    //将二者加入到移除的集合中
                    GameUtils.removeList.add(enemy1Obj);
                    GameUtils.removeList.add(this);
                    health--;
                    GameWin.state=3;
                } else if (this.getRec().intersects(enemy1Obj.getRec()) == true&&health>1) {
                    //1.2小飞机爆炸，我方飞机扣血
                    ExplodeObj explodeObj = new ExplodeObj(x, y);
                    GameUtils.explodeObjList.add(explodeObj);
                    GameUtils.removeList.add(explodeObj);
                    enemy1Obj.setX(-100);
                    enemy1Obj.setY(-100);
                    GameUtils.removeList.add(enemy1Obj);
                    health--;
                }
            }

            //2.我方飞机与敌方大飞机碰撞
            for (Enemy2Obj enemy2Obj : GameUtils.enemy2ObjList) {
                if (this.getRec().intersects(enemy2Obj.getRec()) == true&&health==1) {
                    //2.1二者都发生爆炸
                    ExplodeObj explodeObj = new ExplodeObj(x, y);
                    GameUtils.explodeObjList.add(explodeObj);
                    GameUtils.removeList.add(explodeObj);
                    enemy2Obj.setX(-100);
                    enemy2Obj.setY(-100);
                    this.x = -200;
                    this.y = -200;
                    GameUtils.removeList.add(enemy2Obj);
                    GameUtils.removeList.add(this);
                    health--;
                    GameWin.state=3;
                } else if (this.getRec().intersects(enemy2Obj.getRec()) == true&&health>1) {
                    //2.2大飞机发生爆炸，我方飞机扣血
                    ExplodeObj explodeObj = new ExplodeObj(x, y);
                    GameUtils.explodeObjList.add(explodeObj);
                    GameUtils.removeList.add(explodeObj);
                    enemy2Obj.setX(-100);
                    enemy2Obj.setY(-100);
                    GameUtils.removeList.add(enemy2Obj);
                    health--;
                }
            }

            //3.我方飞机与敌方大飞机子弹
            for (Enemy2Bullet enemy2BulletObj : GameUtils.enemy2BulletList) {
                if (this.getRec().intersects(enemy2BulletObj.getRec()) == true&&health==1) {
                    //3.1二者都发生爆炸
                    ExplodeObj explodeObj = new ExplodeObj(x, y);
                    GameUtils.explodeObjList.add(explodeObj);
                    GameUtils.removeList.add(explodeObj);
                    enemy2BulletObj.setX(-100);
                    enemy2BulletObj.setY(-100);
                    this.x = -200;
                    this.y = -200;
                    GameUtils.removeList.add(enemy2BulletObj);
                    GameUtils.removeList.add(this);
                    health--;
                    GameWin.state=3;
                } else if (this.getRec().intersects(enemy2BulletObj.getRec()) == true&&health>1) {
                    //3.2大飞机子弹发生爆炸，我方飞机扣血
                    ExplodeObj explodeObj = new ExplodeObj(x, y);
                    GameUtils.explodeObjList.add(explodeObj);
                    GameUtils.removeList.add(explodeObj);
                    enemy2BulletObj.setX(-100);
                    enemy2BulletObj.setY(-100);
                    GameUtils.removeList.add(enemy2BulletObj);
                    health--;
                }
            }

            //4.敌方小boss1与我方飞机发生碰撞
            if (this.getRec().intersects(littleBoss1.getRec()) == true) {
                //二者直接爆炸
                ExplodeObj explodeObj = new ExplodeObj(x, y);
                GameUtils.explodeObjList.add(explodeObj);
                GameUtils.removeList.add(explodeObj);
                this.x = -200;
                this.y = -200;
                GameUtils.removeList.add(this);
                health=0;
                GameWin.state=3;
            }

            //5.敌方小boss1与我方飞机发生碰撞
            if (this.getRec().intersects(littleBoss2.getRec()) == true) {
                //二者直接爆炸
                ExplodeObj explodeObj = new ExplodeObj(x, y);
                GameUtils.explodeObjList.add(explodeObj);
                GameUtils.removeList.add(explodeObj);
                this.x = -200;
                this.y = -200;
                GameUtils.removeList.add(this);
                health=0;
                GameWin.state=3;
            }

            //6.我方飞机与敌方小boss1子弹发生碰撞
            for (LittleBoss1Bullet littboss1bullet : GameUtils.littleBoss1BulletList) {
                if (this.getRec().intersects(littboss1bullet.getRec()) == true&&health==1) {
                    //6.1二者直接爆炸
                    ExplodeObj explodeObj = new ExplodeObj(x, y);
                    GameUtils.explodeObjList.add(explodeObj);
                    GameUtils.removeList.add(explodeObj);
                    littboss1bullet.setX(-100);
                    littboss1bullet.setY(-100);
                    this.x = -200;
                    this.y = -200;
                    GameUtils.removeList.add(littboss1bullet);
                    GameUtils.removeList.add(this);
                    health--;
                    GameWin.state=3;
                } else if (this.getRec().intersects(littboss1bullet.getRec()) == true&&health>1) {
                    //6.2小boss1子弹发生爆炸，我方飞机扣血
                    ExplodeObj explodeObj = new ExplodeObj(x, y);
                    GameUtils.explodeObjList.add(explodeObj);
                    GameUtils.removeList.add(explodeObj);
                    littboss1bullet.setX(-100);
                    littboss1bullet.setY(-100);
                    GameUtils.removeList.add(littboss1bullet);
                    health--;
                }
            }

            //7.我方飞机与敌方小boss2子弹发生碰撞
            for (LittleBoss2Bullet littboss2bullet : GameUtils.littleBoss2BulletList) {
                if (this.getRec().intersects(littboss2bullet.getRec()) == true&&health==1) {
                    //7.1二者直接爆炸
                    ExplodeObj explodeObj = new ExplodeObj(x, y);
                    GameUtils.explodeObjList.add(explodeObj);
                    GameUtils.removeList.add(explodeObj);
                    littboss2bullet.setX(-100);
                    littboss2bullet.setY(-100);
                    this.x = -200;
                    this.y = -200;
                    GameUtils.removeList.add(littboss2bullet);
                    GameUtils.removeList.add(this);
                    health--;
                    GameWin.state=3;
                } else if (this.getRec().intersects(littboss2bullet.getRec()) == true&&health>1) {
                    //7.2小boss1子弹发生爆炸，我方飞机扣血
                    ExplodeObj explodeObj = new ExplodeObj(x, y);
                    GameUtils.explodeObjList.add(explodeObj);
                    GameUtils.removeList.add(explodeObj);
                    littboss2bullet.setX(-100);
                    littboss2bullet.setY(-100);
                    GameUtils.removeList.add(littboss2bullet);
                    health--;
                }
            }

            //8.我方飞机与补给品发生碰撞
            for (GiftObj giftObj : GameUtils.giftObjList) {
                if (this.getRec().intersects(giftObj.getRec()) == true) {
                    giftObj.setX(-100);
                    giftObj.setY(-100);
                    GameUtils.removeList.add(giftObj);
                    times++;
                    health++;
                }
            }

            //9.我方飞机与boss子弹发生碰撞
            for (BossBullet bossBullet:GameUtils.bossbulletlist){
                if(this.getRec().intersects(bossBullet.getRec())==true&&health==1){
                    //9.1二者都发生爆炸
                    ExplodeObj explodeObj = new ExplodeObj(x, y);
                    GameUtils.explodeObjList.add(explodeObj);
                    GameUtils.removeList.add(explodeObj);
                    bossBullet.setX(-100);
                    bossBullet.setY(-100);
                    this.x = -200;
                    this.y = -200;
                    GameUtils.removeList.add(bossBullet);
                    GameUtils.removeList.add(this);
                    health--;
                    GameWin.state=3;
                } else if (this.getRec().intersects(bossBullet.getRec())==true&&health>1) {
                    //9.2boss子弹发生爆炸，我方飞机扣血
                    ExplodeObj explodeObj = new ExplodeObj(x, y);
                    GameUtils.explodeObjList.add(explodeObj);
                    GameUtils.removeList.add(explodeObj);
                    bossBullet.setX(-100);
                    bossBullet.setY(-100);
                    GameUtils.removeList.add(bossBullet);
                    health--;
                }
            }
        }
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
