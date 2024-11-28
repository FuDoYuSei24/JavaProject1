package com.sxt;

import com.sxt.obj.*;
import com.sxt.utils.GameUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameWin extends JFrame {

    //记录游戏状态的变量
    //0未开始，1游戏中，2暂停，3失败，4通关
    public static int state;

    //添加背景图对象属性
    //背景图是从上往下移动，开始绘制时要在一点的位置绘制
    Bgobj bgobj=new Bgobj(GameUtils.bdImg,0,-1800,2);


    //定义一个图片变量
    Image offScreenImage=null;

    //引入我方飞机的对象
    //我方飞机的图片，图片的宽，图片的高，初始位置
    PlaneObj planeobj=new PlaneObj(GameUtils.planeImg,37,41,290,550,0,this);


    //获取我方子弹的对象
    //我方子弹是在我方飞机的位置生成的
    //所以通过getX和getY获取我方子弹的位置
    //PlaneObj shellobj=new PlaneObj(GameUtils.shellImg,14,29,planeobj.getX(),planeobj.getY(),5,this);

    //记录游戏绘制的次数
    public static int count=1;

    //敌方1号小BOSS的对象
    //敌方2号小BOSS的对象
    LittleBoss1 littleBoss1=new LittleBoss1(GameUtils.littleboss1Img,172,112,-200,350,3,this);
    LittleBoss2 littleBoss2=new LittleBoss2(GameUtils.littleboss2Img,172,112,300,-150,2,this);


    //获取敌方BOSS的对象
    BossObj bossObj=new BossObj(GameUtils.bossImg,240,174,180,-180,3,this);

    //获取警示的对象
    WarningObj warningObj=new WarningObj(GameUtils.warningImg,599,90,0,350,0,this);

    //定义一个变量，来记录我方飞机的索引
    public static int planeindex=0;

    //定义一个变量，来记录游戏分数
    public static int score=0;


    //
//窗口
    public void launch(){
        //窗口是否可见
        this.setVisible(true);
        //窗口大小
        this.setSize(600,800);
        //窗口位置
        this.setLocationRelativeTo(null);
        //窗口的标题
        this.setTitle("飞机大战");
        //关闭窗口会自动结束进程
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //将所有要绘制的游戏物体全部放入所有元素集合中进行绘制
        GameUtils.gameObjList.add(bgobj);//背景
        GameUtils.gameObjList.add(planeobj);//我方飞机
        planeindex=GameUtils.gameObjList.indexOf(planeobj);//这里拿到我方飞机的索引值
        //鼠标的点击事件
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                if(e.getButton()==1&&state==0){//当我们的游戏有鼠标左键点击并且游戏处于未开始状态时
                    state=1;//切换游戏状态
                    repaint();

                }
            }

        });

        //键盘的监听事件
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==32){
                    if(state==1){
                        state=2;
                    } else if (state==2) {
                        state=1;
                    }
                }
            }
        });

        //必须的while循环,实现游戏画面的重复绘制
        /*while(true){
            createObj();
            repaint();
            //添加线程休眠，防止绘制过快
            //try-catch是一个异常处理代码块，
            // 用于捕获可能在调用 Thread.sleep(25) 时抛出的异常
            //Thread.sleep() 方法可能会抛出 InterruptedException
            //这段代码块就可以将其捕获并打印
            //便于开发者了解代码出错的地方和错误的堆栈信息
           try{
               Thread.sleep(25);//25毫秒
           }catch(Exception e){
               e.printStackTrace();
           }
        }

         */
        Timer timer = new Timer(25, e -> {
            createObj();
            repaint();
        });
        timer.start();
    }

//createObj这个方法用来批量创建物体
    void createObj(){
        //1.绘制我方飞机子弹
        if(count%10==0) {
            //1.1一级子弹
           if(planeobj.times==0){
               GameUtils.shellObjList.add(new ShellObj(GameUtils.shellImg, 14, 29, planeobj.getX() + 12, planeobj.getY() - 20, 5, this));
               //添加到所有元素集合中的对象是新new出来的对象，不是整个子弹集合
               GameUtils.gameObjList.add(GameUtils.shellObjList.get(GameUtils.shellObjList.size() - 1));
           }
           //1.2二级子弹
           if(planeobj.times==1&&count%20==0){
               GameUtils.doubleshellObjList.add(new DoubleShellObj(GameUtils.doubleshellImg, 32, 64, planeobj.getX() + 5, planeobj.getY() - 100, 10, this));
               GameUtils.gameObjList.add(GameUtils.doubleshellObjList.get(GameUtils.doubleshellObjList.size() - 1));
           }
           //1.3三级子弹
           if(planeobj.times>=2&&count%20==0){
               GameUtils.tripleshellObjList.add(new TripleShellObj(GameUtils.tripleshellImg, 64, 182, planeobj.getX()-5, planeobj.getY() - 20, 15, this));
               GameUtils.gameObjList.add(GameUtils.tripleshellObjList.get(GameUtils.tripleshellObjList.size() - 1));
           }
        }
        //2.绘制敌方小飞机
        if(count%35==0){
            GameUtils.enemy1ObjList.add(new Enemy1Obj(GameUtils.enemy1Img,32,24,(int)(Math.random()*10)*60,0,5,this));
            GameUtils.gameObjList.add(GameUtils.enemy1ObjList.get(GameUtils.enemy1ObjList.size()-1));
        }
        //3.绘制敌方大飞机
        if(count%50==0) {
            //3.1绘制敌方大飞机
            if (count % 100 == 0) {
                GameUtils.enemy2ObjList.add(new Enemy2Obj(GameUtils.enemy2Img, 44, 67, (int) (Math.random() * 10) * 60, 0, 3, this));
                GameUtils.gameObjList.add(GameUtils.enemy2ObjList.get(GameUtils.enemy2ObjList.size() - 1));
            }
            //3.2绘制敌方大飞机子弹
            if(GameUtils.enemy2ObjList.size()>0){//大飞机的集合长度大于0，说明此时存在大飞机，故可以产生大飞机的子弹
                //这里的（x，y）就是最新产生的敌方大飞机对象的位置
                int x=(GameUtils.enemy2ObjList.get(GameUtils.enemy2ObjList.size()-1)).getX();
                int y=(GameUtils.enemy2ObjList.get(GameUtils.enemy2ObjList.size()-1)).getY();
                GameUtils.enemy2BulletList.add(new Enemy2Bullet(GameUtils.enemy2bulletImg,14,25,x+17,y+55,6,this));
                GameUtils.gameObjList.add(GameUtils.enemy2BulletList.get(GameUtils.enemy2BulletList.size()-1));
            }
        }
        //4.绘制小boss1
        if(count%700==0){
            GameUtils.gameObjList.add(littleBoss2);
        }
        //5.绘制小boss2
        if(count%800==0){
            GameUtils.gameObjList.add(littleBoss1);
        }
        //6.生成敌方小boss1的子弹
        if(count%50==0){
            if(GameUtils.gameObjList.contains(littleBoss1)) {
                GameUtils.littleBoss1BulletList.add(new LittleBoss1Bullet(GameUtils.littleboss1bulletImg, 42, 42, littleBoss1.getX()+75, littleBoss1.getY()+100, 4, this));
                GameUtils.gameObjList.add(GameUtils.littleBoss1BulletList.get(GameUtils.littleBoss1BulletList.size() - 1));
            }
        }
        //7.生成敌方小boss2的子弹
        if(count%50==0){
            if(GameUtils.gameObjList.contains(littleBoss2)){
                GameUtils.littleBoss2BulletList.add(new LittleBoss2Bullet(GameUtils.Littleboss2bulletImg,21,59, littleBoss2.getX()+78, littleBoss2.getY()+100,7,this ));
                GameUtils.gameObjList.add(GameUtils.littleBoss2BulletList.get(GameUtils.littleBoss2BulletList.size()-1));
            }
        }
        //8.生成boss
        if(count==1300&&(!GameUtils.gameObjList.contains(bossObj))){
            GameUtils.gameObjList.add(bossObj);
        }
        //9.生成boss子弹
        if(count%20==0) {
            if (GameUtils.gameObjList.contains(bossObj) == true) {
                //9.1小boss1的子弹
                GameUtils.littleBoss1BulletList.add(new LittleBoss1Bullet(GameUtils.littleboss1bulletImg, 42, 42, bossObj.getX() + 10, bossObj.getY() + 130, 6, this));
                GameUtils.gameObjList.add(GameUtils.littleBoss1BulletList.get(GameUtils.littleBoss1BulletList.size() - 1));
                //9.2小boss2的子弹
                if(count%40==0) {
                    GameUtils.littleBoss2BulletList.add(new LittleBoss2Bullet(GameUtils.Littleboss2bulletImg, 21, 59, bossObj.getX() + 220, bossObj.getY() + 130, 10, this));
                    GameUtils.gameObjList.add(GameUtils.littleBoss2BulletList.get(GameUtils.littleBoss2BulletList.size() - 1));
                }
                //9.3boss的子弹
                GameUtils.bossbulletlist.add(new BossBullet(GameUtils.bossbulletImg, 51, 72, bossObj.getX() + 70, bossObj.getY() + 100, 9, this));
                GameUtils.gameObjList.add(GameUtils.bossbulletlist.get(GameUtils.bossbulletlist.size() - 1));
            }
        }
        //10.绘制警示图片
        if(count==1250&&!GameUtils.gameObjList.contains(warningObj)){
            GameUtils.gameObjList.add(warningObj);
        }
        //11.清除警示图片
        if(count==1290){
            GameUtils.removeList.add(warningObj);
        }

    }

//main方法
    public static void main(String[] args) {
        GameWin gamewin=new GameWin();
        gamewin.launch();
    }

//paint在窗口（x，y）处绘制图片
    @Override
    public void paint(Graphics g){
        //代码是在进行图形绘制前准备一个图像缓冲区,在缓存区进行绘制
        //而不是直接在屏幕上进行绘制
        //初始化图片缓存区
        if(offScreenImage==null){
            offScreenImage=createImage(600,800);//大小要和游戏窗口大小相同
        }
        //获取图片缓存区的图形上下文
        //可以在这个图形上下文绘制形状、文本、图像等
        Graphics gImage=offScreenImage.getGraphics();
        //在图形上下文的（0，0）处绘制图形
        gImage.fillRect(0,0,600,800);

        //游戏状态为0，即未开始状态开始绘制
        if(state==0) {
            gImage.drawImage(GameUtils.bdImg, 0, 0, null);//绘制背景
            gImage.drawImage(GameUtils.bossImg, 190, 70, null);//绘制boss
            gImage.drawImage(GameUtils.explodeImg, 270, 350, null);//绘制爆炸
            gImage.drawImage(GameUtils.planeImg, 280, 470, null);//绘制玩家

            //绘制游戏开始界面的文字
            gImage.setColor(Color.BLUE);//设置字体颜色
            gImage.setFont(new Font("仿宋", Font.BOLD, 30));//设置字体为仿宋，加粗，字号30
            gImage.drawString("鼠标左键开始游戏", 180, 300);//在窗口（x，y）位置绘制文字
        }
        if(state==1){

            //将爆炸元素添加到所有元素的集合中
            GameUtils.gameObjList.addAll(GameUtils.explodeObjList);

            //bgobj.paintSelf(gImage);//绘制背景图自己
            //planeobj.paintSelf(gImage);//绘制我方飞机
            //shellobj.paintSelf(gImage);//绘制我方飞机子弹

            //不在单独绘制每一个元素，因为每个元素都放入了所有元素集合
            //只需要将集合中所有元素遍历出来，然后绘制自身即可
            for(int i=0;i<GameUtils.gameObjList.size()-1;i++){
                GameUtils.gameObjList.get(i).paintSelf(gImage);
            }
            //将要移除的元素从所有集合的元素中删除
            //这样可以减小绘制的压力
            //由于gameObjList中包含removeList中的所有元素
            //故可以通过removeALL这个方法来移除这些重合的部分
            GameUtils.gameObjList.removeAll(GameUtils.removeList);
            count++;//每绘制一次，记录加一

        }
        if(state==2){
            gImage.drawImage(GameUtils.bdImg,0,0,null);
            GameUtils.drawWord(gImage,"游戏暂停",Color.yellow,30,220,300);
        }
        if(state==3){
            gImage.drawImage(GameUtils.bdImg,0,0,null);
            GameUtils.drawWord(gImage,"游戏失败",Color.red,30,220,300);

        }
        if(state==4){
            gImage.drawImage(GameUtils.bdImg,0,0,null);
            GameUtils.drawWord(gImage,"游戏通关",Color.green,30,220,300);

        }
        //绘制游戏积分面板
        GameUtils.drawWord(gImage,score+"分",Color.green,40,30,100);
        //将双缓存图片绘制在游戏窗口
        g.drawImage(offScreenImage,0,0,null);

        System.out.println(GameUtils.gameObjList.size());
    }
}
