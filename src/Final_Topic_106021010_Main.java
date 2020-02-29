import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class Final_Topic_106021010_Main extends JFrame implements ActionListener{

    static Final_Topic_106021010_Main frm = new Final_Topic_106021010_Main();
    static JButton btn_picture[][]=new JButton[6][6];                 //建立4*4的按鍵
    static JButton btn_function[] = new JButton[5];                     //建立5個功能按鍵
    static JButton btn_check[]=new JButton[2];                          //暫時存放圖按鍵
    static JLabel leb_sorce=new JLabel("分數=");                  //建立分數標籤
    static JLabel time_countdoen=new JLabel("剩餘時間=");           //建立倒數計時標籤
    static JLabel difficult_display=new JLabel("選擇難度:");                //建立難度選擇JLabel
    static MenuBar difficult_choice_menubar=new MenuBar();                      //建立難度選擇MenuBar
    static Menu difficult_choice_menu=new Menu("Difficult Choice");         //建立難度選擇Menu
    static MenuItem simple=new MenuItem("Simple");                      //建立簡單難度MenuItem
    static MenuItem ordinary=new MenuItem("Ordinary");                  //建立普通難度MenuItem
    static MenuItem difficult=new MenuItem("difficult");                    //建立困難難度MenuItem
    static int[] Array=new int[16];
    static int random_cnt = 0;                                             //設定放置亂數陣列Array[]的次數計算
    static int success_count = 0;                                            //計算成功顯示圖片數目
    static int picture_name[]=new int[2];                                  //判斷兩個圖片是否相同，儲存圖片名稱的陣列
    static ImageIcon picture_route[][]=new ImageIcon[4][4];                //儲存圖片路徑的陣列
    static ImageIcon blank=new ImageIcon("Images/blank");   //取得空白圖片路徑
    static int score = 0;                                                 //設定初始分數
    static boolean game_end=false;                                  //判斷遊戲是否結束
    static boolean game_start=false;                                    //判斷遊戲是否開始
    static boolean card_open[]=new boolean[16];
    static int sleep_see ;                                          //設定玩家看牌時間
    static int sleep_error ;                                        //設定蓋牌延遲時間
    static int delaytime;                                               //存放睡眠時間變數
    static int interval;                                                //存放整數時間變數
    static Timer timer;                                             //建立time物件
    static int timecountdown;                                    //時間倒數字串
    static int time;                                             //難度時間變數
    static int score_start_time;                                    //正式開始遊戲時間變數
    static int delay = 1000;
    static int period = 1000;
    static Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
    static int width=(int)screen.width/100;
    static int height=(int)screen.height/100;
    //static boolean game_start=false;


    class MyThread extends Thread{
        public MyThread(int st){
            delaytime = st;
        }
        public void run()
        {
            try {
                sleep(delaytime);
            } catch (InterruptedException e) { }
            if (delaytime == sleep_error){                                        //讓玩家看一段時間後把牌蓋回去
                btn_check[0].setIcon(blank);
                btn_check[0].setBackground(Final_Topic_106021010_Minor.colors);
                btn_check[1].setIcon(blank);
                btn_check[1].setBackground(Final_Topic_106021010_Minor.colors);
            }else if (delaytime == sleep_see) {
                for (int i = 0; i < 4; i++) {                                   //讓玩家看幾秒後把牌蓋回去
                    for (int j = 0; j < 4; j++) {
                        btn_picture[i][j].setIcon(blank);
                        btn_picture[i][j].setBackground(Final_Topic_106021010_Minor.colors);
                    }
                }
            }
        }
    }

    public static void main(String[] agrs) {

        frm.setTitle("記憶翻牌遊戲");
        frm.setLayout(null);
        frm.setSize(width*42, height*95);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setResizable(false);

        //功能按鍵設定
        btn_function[0] = new JButton("關閉");
        btn_function[0].setBounds(width*1, height*1, width*7, height*5);
        btn_function[0].addActionListener(frm);
        btn_function[1]=new JButton("規則說明");
        btn_function[1].setBounds(width*9,height*1,width*7,height*5);
        btn_function[1].addActionListener(frm);
        btn_function[2]=new JButton("開始");
        btn_function[2].setBounds(width*17,height*1,width*7,height*5);
        btn_function[2].addActionListener(frm);
        btn_function[3]=new JButton("重新開始");
        btn_function[3].setBounds(width*25,height*1,width*7,height*5);
        btn_function[3].addActionListener(frm);
        btn_function[4]=new JButton("自訂");
        btn_function[4].setBounds(width*33,height*1,width*7,height*5);
        btn_function[4].addActionListener(frm);

        //時間倒數、分數、難度選擇標籤設定
        leb_sorce.setBounds(width*2,width*43,width*10,height*5);
        time_countdoen.setBounds(width*20,width*43,width*10,height*5);
        difficult_display.setBounds(width*22,width*4,width*10,height*5);

        //設定字型的樣式
        Font fnt=new Font("",Font.ITALIC+Font.BOLD,20);
        leb_sorce.setFont(fnt);
        leb_sorce.setForeground(Color.RED);
        time_countdoen.setFont(fnt);
        time_countdoen.setForeground(Color.RED);
        difficult_display.setFont(fnt);
        difficult_display.setForeground(Color.BLUE);
        difficult_choice_menubar.setFont(fnt);

        //添加功能表項目
        difficult_choice_menubar.add(difficult_choice_menu);
        difficult_choice_menu.add(simple);
        difficult_choice_menu.add(ordinary);
        difficult_choice_menu.add(difficult);

        //設定功能項目傾聽者
        simple.addActionListener(frm);
        ordinary.addActionListener(frm);
        difficult.addActionListener(frm);

        frm.setMenuBar(difficult_choice_menubar);                           //將MenuBar加入frm

        //將按鍵、標籤加入frm
        frm.add(btn_function[0]);
        frm.add(btn_function[1]);
        frm.add(btn_function[2]);
        frm.add(btn_function[3]);
        frm.add(btn_function[4]);
        frm.add(leb_sorce);
        frm.add(time_countdoen);
        frm.add(difficult_display);

        for (int i=0;i<16;i++){
            card_open[i]=false;
        }

        //設定4X4圖牌按鈕位置、顏色
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Final_Topic_106021010_Minor.checksize=true;
                btn_picture[i][j] = new JButton();
                btn_picture[i][j].setBackground(new Color(100, 50, 50));
                btn_picture[i][j].setBounds(i * width*9+width*2 , j *width*9+width*7 , width*9, width*9);
                frm.add(btn_picture[i][j]);
                btn_picture[i][j].addActionListener(frm);
                btn_picture[i][j].setEnabled(false);
            }
        }
        frm.setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {


        //難度選擇
        if (e.getSource() == simple) {
            if (game_start==true){
                game_start=false;
            }else if (game_start==false) {
                game_start=true;
                sleep_see = 5000;      //ms
                sleep_error = 1000;     //ms
                time = 80;    //s
                score_start_time = time - sleep_see / 1000;
                difficult_display.setText("難度選擇 : 簡單");
                difficult_display.setForeground(Color.BLUE);
            }

        } else if (e.getSource() == ordinary) {
            if (game_start==true){
                game_start=false;
            }else if (game_start==false) {
                game_start = true;
                sleep_see = 4000;       //ms
                sleep_error = 800;      //ms
                time = 60;     //s
                score_start_time = time - sleep_see / 1000;
                difficult_display.setText("難度選擇 : 普通");
                difficult_display.setForeground(Color.BLUE);
            }

        } else if (e.getSource() == difficult) {
            if (game_start==true){
                game_start=false;
            }else if (game_start==false) {
                game_start = true;
                sleep_see = 3000;    //ms
                sleep_error = 600;   //ms
                time = 40;   //s
                score_start_time = time - sleep_see / 1000;
                difficult_display.setText("難度選擇 : 困難");
                difficult_display.setForeground(Color.BLUE);
            }
        }


        //關閉視窗
        if (e.getSource() == btn_function[0]) {
            System.exit(0);
        }

        //建立子視窗，規則說明
        else if (e.getSource() == btn_function[1]) {
            JFrame jf = new JFrame("規則說明");
            JTextArea jta = new JTextArea();
            jta.setEditable(false);
            jta.setSize( width*45,  height*60);
            jta.setText("1.遊戲一開始先選擇難度。" + "\n\n" + "2.如果翻出兩張不一樣的牌會先讓玩家看一段時間後再將牌蓋回去。" + "\n\n" +
                    "3.如果翻出兩張一樣的牌分數加100，翻出兩張不一樣的牌分數減10。" + "\n\n" + "4.時間倒數完畢即跳出時間已到視窗。" + "\n\n" +
                    "5.成功翻完16張牌或時間結束，顯示遊戲結束畫面。" + "\n\n" + "6.*若翻出兩張不一樣的牌，請等牌蓋回去後再翻下一張牌。" + "\n\n" +
                    "7.如果想更改圖牌大小、圖牌及背景顏色或遊戲時間可以按自訂。" + "\n\n" +
                    "8.*若不選擇難度，則須在自訂中選擇三個時間，否則無法開始遊戲。"
                    + "\n\n" + "9.如果想重新遊戲，可以按重新開始按鈕。");
            Font fnt = new Font("", Font.ITALIC + Font.BOLD, 20);
            jta.setFont(fnt);
            jf.setLayout(null);
            jf.add(jta);
            jf.setBounds(width*40, height*20, width*45, height*60);
            jf.setVisible(true);
        }

        //產生8對圖片
        else if (e.getSource() == btn_function[2]) {
            if (game_start == true) {
                game_end = false;
                //Final_Topic_106021010_Minor.checksize=true;

                //時間倒數
                timer = new Timer();
                interval = time;
                time_countdoen.setText("時間剩餘 : " + time + "秒");
                time_countdoen.setForeground(Color.RED);
                timer.scheduleAtFixedRate(new TimerTask() {
                    public void run() {
                        //System.out.println(setInterval());
                        timecountdown = setInterval();                          //  呼叫setInterval()
                        time_countdoen.setText("時間剩餘 : " + timecountdown + "秒");

                        //當時間為0時，跳出遊戲結束視窗
                        if (timecountdown == 0) {
                            for (int i = 0; i < 4; i++) {
                                for (int j = 0; j < 4; j++) {
                                    btn_picture[i][j].setEnabled(false);
                                }
                            }
                            JFrame gameend_jf = new JFrame("時間已到");
                            JLabel gameend_jlb = new JLabel("", JLabel.CENTER);
                            gameend_jlb.setFont(new Font("", 1, 24));
                            gameend_jlb.setText("時間已到 !     分數 = " + score);
                            gameend_jlb.setVerticalAlignment(JLabel.CENTER);                 //字體至中
                            gameend_jlb.setHorizontalAlignment(JLabel.CENTER);               //字體至中
                            gameend_jf.setBounds(width*20, height*40, width*30, height*20);
                            gameend_jf.add(gameend_jlb);
                            gameend_jf.setVisible(true);
                        }
                    }
                }, delay, period);

                //產生不重複亂數1~16
                int[] num = new int[16];
                for (int i = 0; i < num.length; i++) {
                    num[i] = i + 1;
                    //System.out.print(num[i]+" ");
                }
                Array = getRandom(num);           //呼叫函式getRandom

                //設定4X4按鍵的圖片、背景顏色
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (Array[random_cnt] < 10) {
                            ImageIcon text1 = new ImageIcon(getClass().getResource("Images/0" + Integer.toString(Array[random_cnt]) + ".png"));      //取得圖片路徑
                            btn_picture[i][j].setIcon(text1);
                            btn_picture[i][j].setBackground(Color.WHITE);
                            picture_route[i][j] = text1;
                        } else if (Array[random_cnt] >= 10) {
                            ImageIcon text1 = new ImageIcon(getClass().getResource("Images/" + Integer.toString(Array[random_cnt]) + ".png"));            //取得圖片路徑
                            btn_picture[i][j].setIcon(text1);
                            btn_picture[i][j].setBackground(Color.WHITE);
                            picture_route[i][j] = text1;
                        }
                        random_cnt++;
                    }
                }
                Thread ct = new MyThread(sleep_see);
                ct.start();
                //game_start = true;
            }
            else if (game_start==false){
                JFrame jfr = new JFrame("錯誤");
                JLabel jl = new JLabel();
                jl.setSize(width*30, height*20);
                jl.setText("\n請選擇難度或自定時間");
                jl.setVerticalAlignment(JLabel.CENTER);                          //字體至中
                jl.setHorizontalAlignment(JLabel.CENTER);                            //字體至中
                Font fnt = new Font("", Font.ITALIC + Font.BOLD , 20);
                jl.setFont(fnt);
                jl.setForeground(Color.RED);
                jfr.setLayout(null);
                jfr.add(jl);
                jfr.setBounds(width*20, height*20, width*30, height*25);
                jfr.setVisible(true);
            }
        }


        //設定重新開始事件處理
        else if (e.getSource() == btn_function[3]) {
            for (int i=0;i<16;i++){
                card_open[i]=false;
            }
            success_count=0;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    btn_picture[i][j].setIcon(blank);
                    btn_picture[i][j].setBackground(Final_Topic_106021010_Minor.colors);
                    score = 0;
                    leb_sorce.setText("分數: " + Integer.toString(score));
                    interval = 0;
                }
            }
            Array = null;
            random_cnt = 0;
        }

        //設定自訂事件處理
        else if (e.getSource() == btn_function[4]) {
            Final_Topic_106021010_Minor newframe = new Final_Topic_106021010_Minor();
            newframe.set();
        }

        //判斷兩張牌是否一樣
        if (game_start== true){
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    btn_picture[i][j].setEnabled(true);
                    if ((e.getSource() == btn_picture[i][j]) && ((success_count % 2 == 0) || (success_count % 2 == 1))) {
                        btn_picture[i][j].setIcon(picture_route[i][j]);
                        btn_picture[i][j].setBackground(Color.WHITE);
                        if (success_count % 2 == 0) {                                            //翻第一張牌
                            btn_check[0] = btn_picture[i][j];                           //將btn_picture[i][j]指定給btn_check[0]
                            picture_name[0] = Array[(i*4)+j];                        //將圖片名稱放入picture_name[]
                            success_count++;
                        } else if (success_count % 2 == 1) {                                     //翻第二張牌
                            btn_check[1] = btn_picture[i][j];                           //將btn_picture[i][j]指定給btn_check[1]
                            picture_name[1] = Array[(i*4)+j];                        //將圖片名稱放入picture_name[]
                            success_count++;
                        }

                        //分數計算
                        if ((success_count % 2 == 0) && (timecountdown < score_start_time + 1)) {
                            if (((picture_name[0] == (picture_name[1] + 8)) || (picture_name[0] == (picture_name[1] - 8)))&&
                                    ((card_open[picture_name[0]-1]==false)&&(card_open[picture_name[1]-1]==false))) {                 //若兩張牌一樣
                                score = score + 100;
                                card_open[picture_name[0]-1]=true;
                                card_open[picture_name[1]-1]=true;
                            } else if (((picture_name[0] != (picture_name[1] + 8)) || (picture_name[0] != (picture_name[1] - 8)))&&
                                    ((card_open[picture_name[0]-1]==false)&&(card_open[picture_name[1]-1]==false))) {          //若兩張牌不一樣
                                Thread ct1 = new MyThread(sleep_error);
                                ct1.start();                             //讓玩家先看一段時間後再蓋牌
                                score = score - 10;
                                success_count = success_count - 2;                          //將成功顯示圖片數-2
                            }
                            leb_sorce.setText("分數: " + score);
                            leb_sorce.setForeground(Color.RED);
                            //System.out.println(score);
                        }
                    }
                }
            }

            //如果圖片全部顯示成功，跳出遊戲結束視窗
            if (success_count == 16) {
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        btn_picture[i][j].setEnabled(false);
                    }
                }
                game_end = true;
                JFrame gameend_jf = new JFrame("遊戲結束");
                JLabel gameend_jlb = new JLabel("遊戲結束 : ", JLabel.CENTER);
                gameend_jlb.setFont(new Font("", 1, 24));
                gameend_jlb.setText("遊戲結束 !     分數 = " + score);
                gameend_jlb.setHorizontalAlignment(JLabel.CENTER);                           //字體置中
                gameend_jlb.setVerticalAlignment(JLabel.CENTER);                         //字體置中
                gameend_jf.setBounds(width*20, height*20, width*30, height*20);
                gameend_jf.add(gameend_jlb);
                gameend_jf.setVisible(true);
            }
        }
    }



    //時間倒數函式
    private static final int setInterval() {
        if ((interval == 1)||(game_end==true)||(interval==0))
            timer.cancel();
        return --interval;
    }

    //產生不重複亂數函式
    public static int[] getRandom(int[] num)
    {
        int[] arr = new int[16];
        int n;
        for(int i = 0; i < arr.length; i++)
        {
            n = (int)(Math.random()*(16-i));
            arr[i] = num[n];
            for(int j = n; j < num.length - 1; j++)
            {
                num[j] = num[j+1];
            }
        }
        return arr;
    }
}