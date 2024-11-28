package com.sxt.utils;

import com.sxt.obj.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

//这个类是游戏元素的父类
public class GameUtils {
    //1.获取背景图片
    public static Image bdImg= Toolkit.getDefaultToolkit().getImage("D:\\JAVACode\\project\\PlaneWar\\image\\bg.jpg");

    //2.获取boss图片
    public static Image bossImg= Toolkit.getDefaultToolkit().getImage("D:\\JAVACode\\project\\PlaneWar\\image\\boss.png");

    //3.获取爆炸图片
    public static Image explodeImg= Toolkit.getDefaultToolkit().getImage("D:\\JAVACode\\project\\PlaneWar\\image\\explode\\e6.gif");

    //4.获取我方飞机的图片
    public static Image planeImg= Toolkit.getDefaultToolkit().getImage("D:\\JAVACode\\project\\PlaneWar\\image\\plane.png");

    //5.获取我方飞机的子弹的照片
    //1级子弹
    public static Image shellImg=Toolkit.getDefaultToolkit().getImage("D:\\JAVACode\\project\\PlaneWar\\image\\shell.png");
    //2级子弹
    public static Image doubleshellImg=Toolkit.getDefaultToolkit().getImage("D:\\JAVACode\\project\\PlaneWar\\image\\doubleshell.png");
    //3级子弹
    public static Image tripleshellImg=Toolkit.getDefaultToolkit().getImage("D:\\JAVACode\\project\\PlaneWar\\image\\tripleshell.png");

    //6.获取两种敌方飞机的照片
    public static Image enemy1Img=Toolkit.getDefaultToolkit().getImage("D:\\JAVACode\\project\\PlaneWar\\image\\enemy1.png");
    public static Image enemy2Img=Toolkit.getDefaultToolkit().getImage("D:\\JAVACode\\project\\PlaneWar\\image\\enemy2.png");

    //7.获取敌方大飞机的子弹
    public static Image enemy2bulletImg=Toolkit.getDefaultToolkit().getImage("D:\\JAVACode\\project\\PlaneWar\\image\\enemy2bullet.png");

    //8.获取两种敌方小BOSS
    public static Image littleboss1Img=Toolkit.getDefaultToolkit().getImage("D:\\JAVACode\\project\\PlaneWar\\image\\littleboss1.png");
    public static Image littleboss2Img=Toolkit.getDefaultToolkit().getImage("D:\\JAVACode\\project\\PlaneWar\\image\\littleboss2.png");

    //9.获取敌方两种小boss的子弹
    public static Image littleboss1bulletImg=Toolkit.getDefaultToolkit().getImage("D:\\JAVACode\\project\\PlaneWar\\image\\littleboss1bullet.png");
    public static Image Littleboss2bulletImg=Toolkit.getDefaultToolkit().getImage("D:\\JAVACode\\project\\PlaneWar\\image\\littleboss2bullet.png");

    //10.获取补给类的图片
    public static Image giftImg=Toolkit.getDefaultToolkit().getImage("D:\\JAVACode\\project\\PlaneWar\\image\\gift.png");

    //11.boss的子弹图片
    public static Image bossbulletImg=Toolkit.getDefaultToolkit().getImage("D:\\JAVACode\\project\\PlaneWar\\image\\bossbullet.png");

    //12.警示的图片
    public static Image warningImg=Toolkit.getDefaultToolkit().getImage("D:\\JAVACode\\project\\PlaneWar\\image\\warning.gif");


//集合类


    //1.我方飞机子弹的集合
    //1级子弹
    public static List<ShellObj> shellObjList=new ArrayList<>();
    //2级子弹
    public static List<DoubleShellObj> doubleshellObjList=new ArrayList<>();
    //3级子弹
    public static List<TripleShellObj> tripleshellObjList=new ArrayList<>();

    //2.两种敌方飞机的集合
    public static List<Enemy1Obj> enemy1ObjList=new ArrayList<>();
    public static List<Enemy2Obj> enemy2ObjList=new ArrayList<>();

    //3.敌方大飞机的子弹集合
    public static List<Enemy2Bullet> enemy2BulletList=new ArrayList<>();

    //4.爆炸集合
    public static List<ExplodeObj> explodeObjList=new ArrayList<>();

    //5.两种小boss的集合
    public static List<LittleBoss1> littleBoss1List=new ArrayList<>();
    public static List<LittleBoss2> littleBoss2List=new ArrayList<>();

    //6.两种小boss的子弹的集合
    public static List<LittleBoss1Bullet> littleBoss1BulletList=new ArrayList<>();
    public static List<LittleBoss2Bullet> littleBoss2BulletList=new ArrayList<>();

    //7.补给的集合
    public static List<GiftObj> giftObjList=new ArrayList<>();


    //8.所有元素的集合
    public static List<GameObj> gameObjList=new ArrayList<>();

    //9.移出游戏窗口的集合
    public static List<GameObj> removeList=new ArrayList<>();

    //10.boss的子弹的集合
    public static List<BossBullet> bossbulletlist=new ArrayList<>();


//方法类
    //1.在界面上绘制文字的方法
    public static void drawWord(Graphics gImage,String string,Color color,int size,int x,int y){
        gImage.setColor(color);
        gImage.setFont(new Font("仿宋",Font.BOLD,size));
        gImage.drawString(string,x,y);
    }


}