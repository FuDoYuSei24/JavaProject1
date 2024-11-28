package com.sxt.obj;

import java.awt.*;

//这个类是爆炸效果类
public class ExplodeObj extends GameObj{
    //爆炸效果是一组图片
    //添加一个静态数组来保存这组图片
    static Image[] explodePic=new Image[16];
    //定义一个变量来记录爆炸图的次数
    int explodeCount=0;
    //定义一个静态代码块来将爆炸图片放到数组中
    static{
        for(int i=0;i< explodePic.length;i++){
            explodePic[i]=Toolkit.getDefaultToolkit().getImage("D:\\JAVACode\\project\\PlaneWar\\image\\explode\\e"+(i+1)+".gif");

        }
    }

    @Override
    public void paintSelf(Graphics g) {
        if(explodeCount<16){//当这个记录变量小于16时，说明爆炸动画还没有播完

            super.img=explodePic[explodeCount];
            super.paintSelf(g);
            explodeCount++;

        }
    }

    public ExplodeObj(int x, int y) {
        super(x, y);
    }
}
