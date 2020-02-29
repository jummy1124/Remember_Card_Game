import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class Final_Topic_106021010_Minor extends Final_Topic_106021010_Main implements ItemListener{
    static Final_Topic_106021010_Minor fram = new Final_Topic_106021010_Minor();
    static Container cp=frm.getContentPane();
    static JRadioButton rb1=new JRadioButton("小");            //建立rb1物件
    static JRadioButton rb2=new JRadioButton("中");            //建立rb2物件
    static JRadioButton rb3=new JRadioButton("大");            //建立rb3物件
    static JButton cardcolor_choice_btn=new JButton("圖牌顏色選擇器");
    static JButton frm_backgroundcolor_choice_btn=new JButton("視窗背景顏色選擇器");
    static JButton sumbit_btn=new JButton("確定");
    static JColorChooser JCC=new JColorChooser();       //建立JCC物件
    static Color colors;                         //宣告Color型態的變數colors
    static Color colors_frm;                    //宣告Color型態的變數colors_frm
    static JLabel btnsize_lab=new JLabel("圖牌大小設定:");
    static JLabel games_time=new JLabel("遊戲時間選擇:");
    static JLabel see_card_time=new JLabel("圖牌顯示時間選擇:");
    static JLabel error_see_card_time=new JLabel("圖牌延遲蓋牌時間選擇:");
    static JLabel time_choice_lab_game=new JLabel("選擇時間:");
    static JLabel time_choice_lab_see=new JLabel("選擇時間:");
    static JLabel time_choice_lab_error=new JLabel("選擇時間:");
    static Choice game_time_chc=new Choice();                    //建立game_time_chc物件
    static Choice see_card_time_chc=new Choice();                    //建立see_card_time_chc物件
    static Choice error_see_card_time_chc=new Choice();                  //建立error_see_card_time_chc物件
    static boolean checksize=false;
    static boolean game_start_time=false;
    static boolean game_start_seetime=false;
    static boolean game_start_errortime=false;
    static Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
    static int width=(int)screen.width/100;
    static int height=(int)screen.height/100;


    //自訂視窗設定
    public static void set() {
        fram.setTitle("自訂遊戲");
        Font fnt = new Font("", Font.ITALIC + Font.BOLD, 20);


        //JRadioButton、JButton、JLabel、Choice位置、大小設定
        rb1.setBounds(width*2, height*8, width*10, height*5);
        rb2.setBounds(width*17, height*8, width*10, height*5);
        rb3.setBounds(width*32, height*8, width*10, height*5);
        cardcolor_choice_btn.setBounds(width*2, height*18, width*10, height*5);
        frm_backgroundcolor_choice_btn.setBounds(width*17, height*18, width*10,height*5);
        sumbit_btn.setBounds(width*20, height*60, width*10, height*5);
        game_time_chc.setBounds(width*2, height*40, width*10, height*5);
        see_card_time_chc.setBounds(width*17, height*40, width*10, height*5);
        error_see_card_time_chc.setBounds(width*32, height*40, width*10, height*5);

        btnsize_lab.setBounds(width*2, height*2, width*15, height*5);
        games_time.setBounds(width*2, height*30, width*15, height*5);
        see_card_time.setBounds(width*17, height*30, width*15, height*5);
        error_see_card_time.setBounds(width*32, height*30, width*15, height*5);
        time_choice_lab_game.setBounds(width*2, height*50, width*10, height*5);
        time_choice_lab_see.setBounds(width*17, height*50, width*10, height*5);
        time_choice_lab_error.setBounds(width*32, height*50, width*10, height*5);


        ButtonGroup bgroup = new ButtonGroup();                   //建立ButtonGroup物件
        bgroup.add(rb1);                                        //將rb1設為單選
        bgroup.add(rb2);                                         //將rb2設為單選
        bgroup.add(rb3);                                         //將rb3設為單選


        //添加Choice項目
        game_time_chc.add(" ");
        game_time_chc.add("10s");
        game_time_chc.add("20s");
        game_time_chc.add("30s");
        game_time_chc.add("40s");
        game_time_chc.add("50s");
        game_time_chc.add("60s");
        game_time_chc.add("70s");
        game_time_chc.add("80s");
        game_time_chc.add("90s");
        game_time_chc.add("100s");

        see_card_time_chc.add(" ");
        see_card_time_chc.add("1s");
        see_card_time_chc.add("2s");
        see_card_time_chc.add("3s");
        see_card_time_chc.add("4s");
        see_card_time_chc.add("5s");
        see_card_time_chc.add("6s");
        see_card_time_chc.add("7s");
        see_card_time_chc.add("8s");
        see_card_time_chc.add("9s");
        see_card_time_chc.add("10s");

        error_see_card_time_chc.add(" ");
        error_see_card_time_chc.add("0.5s");
        error_see_card_time_chc.add("0.6s");
        error_see_card_time_chc.add("0.7s");
        error_see_card_time_chc.add("0.8s");
        error_see_card_time_chc.add("0.9s");
        error_see_card_time_chc.add("1.0s");
        error_see_card_time_chc.add("1.1s");
        error_see_card_time_chc.add("1.2s");
        error_see_card_time_chc.add("1.3s");
        error_see_card_time_chc.add("1.4s");
        error_see_card_time_chc.add("1.5s");


        //傾聽者設定
        rb1.addActionListener(fram);
        rb2.addActionListener(fram);
        rb3.addActionListener(fram);
        sumbit_btn.addActionListener(fram);
        cardcolor_choice_btn.addActionListener(new ActLis());
        frm_backgroundcolor_choice_btn.addActionListener(new Act());
        game_time_chc.addItemListener(fram);
        see_card_time_chc.addItemListener(fram);
        error_see_card_time_chc.addItemListener(fram);


        //將元件加入farm
        fram.add(rb1);
        fram.add(rb2);
        fram.add(rb3);
        fram.add(cardcolor_choice_btn);
        fram.add(frm_backgroundcolor_choice_btn);
        fram.add(sumbit_btn);
        fram.add(btnsize_lab);
        fram.add(time_choice_lab_game);
        fram.add(time_choice_lab_see);
        fram.add(time_choice_lab_error);
        fram.add(games_time);
        fram.add(see_card_time);
        fram.add(error_see_card_time);
        fram.add(game_time_chc);
        fram.add(see_card_time_chc);
        fram.add(error_see_card_time_chc);


        //字型設定
        btnsize_lab.setFont(fnt);
        games_time.setFont(fnt);
        see_card_time.setFont(fnt);
        error_see_card_time.setFont(fnt);


        fram.setLayout(null);
        fram.setBounds(width*40, height*10, width*50, height*85);
        fram.setVisible(true);
    }



    //farm繪圖設定
    public void paint(Graphics g){
        super.paint(g);
        g.setColor(Color.RED);
        g.drawLine(width*0,height*18,width*50,height*18);
        g.drawLine(width*0,height*30,width*50,height*30);
        g.drawLine(width*15,height*30,width*15,height*60);
        g.drawLine(width*30,height*30,width*30,height*60);
        g.drawLine(width*0,height*60,width*50,height*60);
        g.setFont(new Font("",Font.ITALIC,32));
        g.setColor(Color.black);
        g.drawString("林映辰製作",width*20,height*75);
        g.setColor(Color.BLUE);
        g.drawRect(width*15,height*70,width*20,height*8);
    }


    //圖牌按鍵大小設定
    public void actionPerformed(ActionEvent e) {
        //JRadioButton jrb=(JRadioButton)e.getSource();
        if (e.getSource()==rb1) {
            if (checksize == true) {
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        btn_picture[i][j].setVisible(false);
                    }
                }
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        btn_picture[i][j] = new JButton();
                        btn_picture[i][j].setBackground(new Color(100, 50, 50));
                        btn_picture[i][j].setBounds(i * width*7+width*6 , j *width*7+width*10 , width*7, width*7);
                        frm.add(btn_picture[i][j]);
                        btn_picture[i][j].addActionListener(frm);
                        btn_picture[i][j].setEnabled(false);
                    }
                }
            } else if (checksize == false) {
                checksize = true;
                //設定4X4按鍵圖的位置、大小、顏色
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        btn_picture[i][j] = new JButton();
                        btn_picture[i][j].setBackground(new Color(100, 50, 50));
                        btn_picture[i][j].setBounds(i * width*7+width*6 , j *width*7+width*10 , width*7, width*7);
                        frm.add(btn_picture[i][j]);
                        btn_picture[i][j].addActionListener(frm);
                        btn_picture[i][j].setEnabled(false);
                    }
                }
            }
        }else if (e.getSource()==rb2) {
            if (checksize == true) {
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        btn_picture[i][j].setVisible(false);
                    }
                }
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        btn_picture[i][j] = new JButton();
                        btn_picture[i][j].setBackground(new Color(100, 50, 50));
                        btn_picture[i][j].setBounds(i * width*8+width*4 , j *width*8+width*8 , width*8, width*8);
                        frm.add(btn_picture[i][j]);
                        btn_picture[i][j].addActionListener(frm);
                        btn_picture[i][j].setEnabled(false);
                    }
                }
            } else if (checksize == false) {
                checksize = true;
                //設定4X4按鍵圖的位置、大小、顏色
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        btn_picture[i][j] = new JButton();
                        btn_picture[i][j].setBackground(new Color(100, 50, 50));
                        btn_picture[i][j].setBounds(i * width*8+width*4 , j *width*8+width*8 , width*8, width*8);
                        frm.add(btn_picture[i][j]);
                        btn_picture[i][j].addActionListener(frm);
                        btn_picture[i][j].setEnabled(false);
                    }
                }
            }
        }else if (e.getSource()==rb3) {
            if (checksize == true) {
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        btn_picture[i][j].setVisible(false);
                    }
                }
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        btn_picture[i][j] = new JButton();
                        btn_picture[i][j].setBackground(new Color(100, 50, 50));
                        btn_picture[i][j].setBounds(i * width*9+width*2 , j *width*9+width*7 , width*9, width*9);
                        frm.add(btn_picture[i][j]);
                        btn_picture[i][j].addActionListener(frm);
                        btn_picture[i][j].setEnabled(false);
                    }
                }
            } else if (checksize == false) {
                checksize = true;
                //設定4X4按鍵圖的位置、大小、顏色
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        btn_picture[i][j] = new JButton();
                        btn_picture[i][j].setBackground(new Color(100, 50, 50));
                        btn_picture[i][j].setBounds(i * width*9+width*2 , j *width*9+width*7 , width*9, width*9);
                        frm.add(btn_picture[i][j]);
                        btn_picture[i][j].addActionListener(frm);
                        btn_picture[i][j].setEnabled(false);
                    }
                }
            }
        }
        else if (e.getSource()==sumbit_btn){
            if ((game_start_time==true)&&(game_start_seetime==true)&&(game_start_errortime==true)) {
                Final_Topic_106021010_Main.score_start_time = Final_Topic_106021010_Main.time - Final_Topic_106021010_Main.sleep_see / 1000;
                fram.dispatchEvent(new WindowEvent(fram, WindowEvent.WINDOW_CLOSING));                //關閉fram
                Final_Topic_106021010_Main.game_start=true;
            }else if ((game_start_time==false)||(game_start_seetime==false)||(game_start_errortime==false)){
                JFrame jfre = new JFrame("請選擇時間");
                JLabel jl = new JLabel();
                jl.setSize(width*20, height*20);
                jl.setText("\n\n請選擇時間");
                jl.setVerticalAlignment(JLabel.CENTER);                                 //字體置中
                jl.setHorizontalAlignment(JLabel.CENTER);                                //字體置中
                Font fnt = new Font("", Font.ITALIC + Font.BOLD , 24);
                jl.setFont(fnt);
                jl.setForeground(Color.RED);
                jfre.setLayout(null);
                jfre.add(jl);
                jfre.setBounds(width*50, height*40, width*20, height*25);
                jfre.setVisible(true);
            }
        }
    }


    //圖牌按鍵顏色選擇器設定
    static class ActLis implements ActionListener{
        public void actionPerformed(ActionEvent e){
            colors=JCC.showDialog(fram,"圖牌顏色選擇器",Color.pink);
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    btn_picture[i][j].setBackground(colors);
                }
            }
        }
    }


    //視窗背景顏色選擇器設定
    static class Act implements ActionListener{
        public void actionPerformed(ActionEvent e){
            colors_frm=JCC.showDialog(fram,"視窗背景顏色選擇器",Color.pink);
            cp.setBackground(colors_frm);
        }
    }


    //Choice項目設定
    public void itemStateChanged(ItemEvent e){
        if (game_time_chc.getSelectedItem()==" "){
            game_start_time=false;
            time_choice_lab_game.setText("選擇時間:");
        }else if (game_time_chc.getSelectedItem()=="10s"){
            game_start_time=true;
            Final_Topic_106021010_Main.time=10;
            time_choice_lab_game.setText("選擇時間:"+Final_Topic_106021010_Main.time+"s");
        }else if (game_time_chc.getSelectedItem()=="20s") {
            game_start_time=true;
            Final_Topic_106021010_Main.time=20;
            time_choice_lab_game.setText("選擇時間:"+Final_Topic_106021010_Main.time+"s");
        }else if (game_time_chc.getSelectedItem()=="30s"){
            game_start_time=true;
            Final_Topic_106021010_Main.time=30;
            time_choice_lab_game.setText("選擇時間::"+Final_Topic_106021010_Main.time+"s");
        }else if (game_time_chc.getSelectedItem()=="40s"){
            game_start_time=true;
            Final_Topic_106021010_Main.time=40;
            time_choice_lab_game.setText("選擇時間:"+Final_Topic_106021010_Main.time+"s");
        }else if (game_time_chc.getSelectedItem()=="50s"){
            game_start_time=true;
            Final_Topic_106021010_Main.time=50;
            time_choice_lab_game.setText("選擇時間:"+Final_Topic_106021010_Main.time+"s");
        }else if (game_time_chc.getSelectedItem()=="60s"){
            game_start_time=true;
            Final_Topic_106021010_Main.time=60;
            time_choice_lab_game.setText("選擇時間:"+Final_Topic_106021010_Main.time+"s");
        }else if (game_time_chc.getSelectedItem()=="70s"){
            game_start_time=true;
            Final_Topic_106021010_Main.time=70;
            time_choice_lab_game.setText("選擇時間:"+Final_Topic_106021010_Main.time+"s");
        }else if (game_time_chc.getSelectedItem()=="80s"){
            game_start_time=true;
            Final_Topic_106021010_Main.time=80;
            time_choice_lab_game.setText("選擇時間:"+Final_Topic_106021010_Main.time+"s");
        }else if (game_time_chc.getSelectedItem()=="90s"){
            game_start_time=true;
            Final_Topic_106021010_Main.time=90;
            time_choice_lab_game.setText("選擇時間:"+Final_Topic_106021010_Main.time+"s");
        }else if (game_time_chc.getSelectedItem()=="100s"){
            game_start_time=true;
            Final_Topic_106021010_Main.time=100;
            time_choice_lab_game.setText("選擇時間:"+Final_Topic_106021010_Main.time+"s");
        }

        if (see_card_time_chc.getSelectedItem()==" "){
            game_start_seetime=false;
            time_choice_lab_see.setText("選擇時間:");
        } else if (see_card_time_chc.getSelectedItem()=="1s"){
            game_start_seetime=true;
            Final_Topic_106021010_Main.sleep_see=1000;
            time_choice_lab_see.setText("選擇時間:"+Final_Topic_106021010_Main.sleep_see/1000+"s");
        }else if (see_card_time_chc.getSelectedItem()=="2s"){
            game_start_seetime=true;
            Final_Topic_106021010_Main.sleep_see=2000;
            time_choice_lab_see.setText("選擇時間:"+Final_Topic_106021010_Main.sleep_see/1000+"s");
        }else if (see_card_time_chc.getSelectedItem()=="3s"){
            game_start_seetime=true;
            Final_Topic_106021010_Main.sleep_see=3000;
            time_choice_lab_see.setText("選擇時間:"+Final_Topic_106021010_Main.sleep_see/1000+"s");
        }else if (see_card_time_chc.getSelectedItem()=="4s"){
            game_start_seetime=true;
            Final_Topic_106021010_Main.sleep_see=4000;
            time_choice_lab_see.setText("選擇時間:"+Final_Topic_106021010_Main.sleep_see/1000+"s");
        }else if (see_card_time_chc.getSelectedItem()=="5s"){
            game_start_seetime=true;
            Final_Topic_106021010_Main.sleep_see=5000;
            time_choice_lab_see.setText("選擇時間:"+Final_Topic_106021010_Main.sleep_see/1000+"s");
        }else if (see_card_time_chc.getSelectedItem()=="6s"){
            game_start_seetime=true;
            Final_Topic_106021010_Main.sleep_see=6000;
            time_choice_lab_see.setText("選擇時間:"+Final_Topic_106021010_Main.sleep_see/1000+"s");
        }else if (see_card_time_chc.getSelectedItem()=="7s"){
            game_start_seetime=true;
            Final_Topic_106021010_Main.sleep_see=7000;
            time_choice_lab_see.setText("選擇時間:"+Final_Topic_106021010_Main.sleep_see/1000+"s");
        }else if (see_card_time_chc.getSelectedItem()=="8s"){
            game_start_seetime=true;
            Final_Topic_106021010_Main.sleep_see=8000;
            time_choice_lab_see.setText("選擇時間:"+Final_Topic_106021010_Main.sleep_see/1000+"s");
        }else if (see_card_time_chc.getSelectedItem()=="9s"){
            game_start_seetime=true;
            Final_Topic_106021010_Main.sleep_see=9000;
            time_choice_lab_see.setText("選擇時間:"+Final_Topic_106021010_Main.sleep_see/1000+"s");
        }else if (see_card_time_chc.getSelectedItem()=="10s"){
            game_start_seetime=true;
            Final_Topic_106021010_Main.sleep_see=10000;
            time_choice_lab_see.setText("選擇時間:"+Final_Topic_106021010_Main.sleep_see/1000+"s");
        }

        if (error_see_card_time_chc.getSelectedItem()==" "){
            game_start_errortime=false;
            time_choice_lab_error.setText("選擇時間:");
        }else if (error_see_card_time_chc.getSelectedItem()=="0.5s"){
            game_start_errortime=true;
            Final_Topic_106021010_Main.sleep_error=500;
            time_choice_lab_error.setText("選擇時間:0.5s");
        }else if (error_see_card_time_chc.getSelectedItem()=="0.6s"){
            game_start_errortime=true;
            Final_Topic_106021010_Main.sleep_error=600;
            time_choice_lab_error.setText("選擇時間:0.6s");
        }else if (error_see_card_time_chc.getSelectedItem()=="0.7s"){
            game_start_errortime=true;
            Final_Topic_106021010_Main.sleep_error=700;
            time_choice_lab_error.setText("選擇時間:0.7s");
        }else if (error_see_card_time_chc.getSelectedItem()=="0.8s"){
            game_start_errortime=true;
            Final_Topic_106021010_Main.sleep_error=800;
            time_choice_lab_error.setText("選擇時間:0.8s");
        }else if (error_see_card_time_chc.getSelectedItem()=="0.9s"){
            game_start_errortime=true;
            Final_Topic_106021010_Main.sleep_error=900;
            time_choice_lab_error.setText("選擇時間:0.9s");
        }else if (error_see_card_time_chc.getSelectedItem()=="1.0s"){
            game_start_errortime=true;
            Final_Topic_106021010_Main.sleep_error=1000;
            time_choice_lab_error.setText("選擇時間:1.0s");
        }else if (error_see_card_time_chc.getSelectedItem()=="1.1s"){
            game_start_errortime=true;
            Final_Topic_106021010_Main.sleep_error=1100;
            time_choice_lab_error.setText("選擇時間:1.1s");
        }else if (error_see_card_time_chc.getSelectedItem()=="1.2s"){
            game_start_errortime=true;
            Final_Topic_106021010_Main.sleep_error=1200;
            time_choice_lab_error.setText("選擇時間:1.2s");
        }else if (error_see_card_time_chc.getSelectedItem()=="1.3s"){
            game_start_errortime=true;
            Final_Topic_106021010_Main.sleep_error=1300;
            time_choice_lab_error.setText("選擇時間:1.3s");
        }else if (error_see_card_time_chc.getSelectedItem()=="1.4s"){
            game_start_errortime=true;
            Final_Topic_106021010_Main.sleep_error=1400;
            time_choice_lab_error.setText("選擇時間:1.4s");
        }else if (error_see_card_time_chc.getSelectedItem()=="1.5s"){
            game_start_errortime=true;
            Final_Topic_106021010_Main.sleep_error=1500;
            time_choice_lab_error.setText("選擇時間:1.5s");
        }
    }
}
