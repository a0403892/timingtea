import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import java.util.ArrayList;
import java.sql.*;

public class index {
	
	static int i = 0;
	static int cashcash = 0;	//總金額
	static String selectedNumber = "";
	static String item [] = new String [20];
	static int qty [] = new int [20];
	static int cash [] = new int [20];
	private static ArrayList<String> items = new ArrayList<String>();
	private static ArrayList<Integer> qtys = new ArrayList<Integer>();;
    private static ArrayList<Integer> cashes = new ArrayList<Integer>();;
	
    
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306/testdb3?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

	// 兒童時光
	public static void childtime() {
		JFrame beverageFrame = new JFrame("兒童時光");
        beverageFrame.setSize(300, 400);
        beverageFrame.setLayout(null);
        beverageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 關閉飲料設定視窗時不影響主視窗

        JPanel panel = new JPanel();
        beverageFrame.add(panel);
        
        JLabel title, bv, ice, sugar, quantity;
        title = new JLabel("兒童時光：薯條、雞塊、熱狗");
        title.setFont(new Font("新細明體", Font.ITALIC, 16));
        bv = new JLabel("飲料");
        ice = new JLabel("冰塊");
        sugar = new JLabel("甜度");
        quantity = new JLabel("數量");
        
        JRadioButton bvRed = new JRadioButton("紅茶");
        JRadioButton bvGreen = new JRadioButton("綠茶");
        JRadioButton bvMilk = new JRadioButton("奶茶");
        ButtonGroup bvGroup = new ButtonGroup();
        bvGroup.add(bvRed);
        bvGroup.add(bvGreen);
        bvGroup.add(bvMilk);

        JRadioButton noIce = new JRadioButton("無冰");
        JRadioButton littleIce = new JRadioButton("少冰");
        JRadioButton normalIce = new JRadioButton("正常冰");
        ButtonGroup iceGroup = new ButtonGroup();
        iceGroup.add(noIce);
        iceGroup.add(littleIce);
        iceGroup.add(normalIce);
        
        JRadioButton noSugar = new JRadioButton("無糖");
        JRadioButton halfSugar = new JRadioButton("半糖");
        JRadioButton fullSugar = new JRadioButton("全糖");
        ButtonGroup sugarGroup = new ButtonGroup();
        sugarGroup.add(noSugar);
        sugarGroup.add(halfSugar);
        sugarGroup.add(fullSugar);

        title.setBounds(20, 10, 300, 30);
        bv.setBounds(20, 50, 100, 30);
        ice.setBounds(20, 110, 100, 30);
        sugar.setBounds(20, 170, 100, 30);
        quantity.setBounds(20, 230, 100, 30);
        
        bvRed.setBounds(70, 50, 70, 30);
        bvGreen.setBounds(140, 50, 70, 30);
        bvMilk.setBounds(210, 50, 70, 30);
        
        noIce.setBounds(70, 110, 70, 30);
        littleIce.setBounds(140, 110, 70, 30);
        normalIce.setBounds(210, 110, 70, 30);
        
        noSugar.setBounds(70, 170, 70, 30);
        halfSugar.setBounds(140, 170, 70, 30);
        fullSugar.setBounds(210, 170, 70, 30);
                
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1); // 設定 JSpinner 的最小、最大值和初始值
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setBounds(110, 235, 60, 30);
        
        beverageFrame.add(title);
        beverageFrame.add(bv);
        beverageFrame.add(bvRed);
        beverageFrame.add(bvGreen);
        beverageFrame.add(bvMilk);
        beverageFrame.add(ice);
        beverageFrame.add(noIce);
        beverageFrame.add(littleIce);
        beverageFrame.add(normalIce);
        beverageFrame.add(sugar);
        beverageFrame.add(noSugar);
        beverageFrame.add(halfSugar);
        beverageFrame.add(fullSugar);
        beverageFrame.add(quantity);
        beverageFrame.add(quantitySpinner);
        
        // 新增取消按鈕
        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(30, 300, 100, 30);
        beverageFrame.add(cancelButton);

        // 新增確定按鈕
        JButton confirmButton = new JButton("確定");
        confirmButton.setBounds(150, 300, 100, 30);
        beverageFrame.add(confirmButton);
        
        // 設定取消按鈕的操作
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	beverageFrame.dispose(); // 關閉視窗
            }
        });        
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 
            	if (!bvRed.isSelected() && !bvGreen.isSelected() && !bvMilk.isSelected()) {
                    JOptionPane.showMessageDialog(beverageFrame, "請選擇飲料", "提示", JOptionPane.WARNING_MESSAGE);
                }
            	else if (!noIce.isSelected() && !littleIce.isSelected() && !normalIce.isSelected()) {
                    JOptionPane.showMessageDialog(beverageFrame, "請選擇冰塊選項", "提示", JOptionPane.WARNING_MESSAGE);
                }
            	else if (!noSugar.isSelected() && !halfSugar.isSelected() && !fullSugar.isSelected()) {
                        JOptionPane.showMessageDialog(beverageFrame, "請選擇甜度選項", "提示", JOptionPane.WARNING_MESSAGE);
                    }
            	else {
            		
            		String selectedbv = "";
                    if (bvRed.isSelected()) {
                    	selectedbv = "紅茶";
                    } else if (bvGreen.isSelected()) {
                    	selectedbv = "綠茶";
                    } else if (bvMilk.isSelected()) {
                    	selectedbv = "奶茶";
                    }

            		String selectedSugar = "";
                    if (noSugar.isSelected()) {
                        selectedSugar = "無糖";
                    } else if (halfSugar.isSelected()) {
                        selectedSugar = "半糖";
                    } else if (fullSugar.isSelected()) {
                        selectedSugar = "全糖";
                    }
                    
                    String selectedIce = "";
                    if (noIce.isSelected()) {
                        selectedIce = "無冰";
                    } else if (littleIce.isSelected()) {
                        selectedIce = "少冰";
                    } else if (normalIce.isSelected()) {
                        selectedIce = "正常冰";
                    }                   
                    int selectedQuantity = (int) quantitySpinner.getValue();                   
                    // 建立提示訊息字串
                    String message = "您選擇的項目:兒童時光\n";
                    message += selectedbv + " \n";
                    message += "甜度: " + selectedSugar + "\n";
                    message += "冰塊: " + selectedIce + "\n";
                    message += "數量: " + selectedQuantity;
                    // 顯示提示訊息視窗
                    JOptionPane.showMessageDialog(beverageFrame, message, "訂單詳情", JOptionPane.INFORMATION_MESSAGE);
                    
                    beverageFrame.dispose();
                    
                    item[i] = "兒童時光";
                    qty[i] = selectedQuantity;
                    cash[i] = 75*selectedQuantity;
                    items.add(item[i]);
                    qtys.add(qty[i]);
                    cashes.add(cash[i]);
                    i++;
                    cashcash += 75*selectedQuantity;
                }
            }
        });
        beverageFrame.setVisible(true);
	}
	// 輕食時光
	public static void easytime() {
		JFrame beverageFrame = new JFrame("輕食時光");
        beverageFrame.setSize(300, 400);
        beverageFrame.setLayout(null);
        beverageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 關閉飲料設定視窗時不影響主視窗

        JPanel panel = new JPanel();
        beverageFrame.add(panel);
        
        JLabel title, bv, ice, sugar, sala, quantity;
        title = new JLabel("輕食時光：果醬湯種、炒蛋、沙拉");
        title.setFont((new Font("新細明體", Font.ITALIC, 16)));
        bv = new JLabel("飲料");
        ice = new JLabel("冰塊");
        sugar = new JLabel("甜度");
        sala = new JLabel("沙拉醬");
        quantity = new JLabel("數量");
        
        JRadioButton bvRed = new JRadioButton("紅茶");
        JRadioButton bvGreen = new JRadioButton("綠茶");
        JRadioButton bvMilk = new JRadioButton("奶茶");
        ButtonGroup bvGroup = new ButtonGroup();
        bvGroup.add(bvRed);
        bvGroup.add(bvGreen);
        bvGroup.add(bvMilk);

        JRadioButton noIce = new JRadioButton("無冰");
        JRadioButton littleIce = new JRadioButton("少冰");
        JRadioButton normalIce = new JRadioButton("正常冰");
        ButtonGroup iceGroup = new ButtonGroup();
        iceGroup.add(noIce);
        iceGroup.add(littleIce);
        iceGroup.add(normalIce);
        
        JRadioButton noSugar = new JRadioButton("無糖");
        JRadioButton halfSugar = new JRadioButton("半糖");
        JRadioButton fullSugar = new JRadioButton("全糖");
        ButtonGroup sugarGroup = new ButtonGroup();
        sugarGroup.add(noSugar);
        sugarGroup.add(halfSugar);
        sugarGroup.add(fullSugar);
        
        JRadioButton kaiza = new JRadioButton("凱撒");
        JRadioButton wind = new JRadioButton("和風");
        JRadioButton thousandisland = new JRadioButton("千島");
        ButtonGroup salaGroup = new ButtonGroup();
        salaGroup.add(kaiza);
        salaGroup.add(wind);
        salaGroup.add(thousandisland);

        title.setBounds(20, 10, 300, 30);
        bv.setBounds(20, 40, 100, 30);
        ice.setBounds(20, 80, 100, 30);
        sugar.setBounds(20, 120, 100, 30);
        sala.setBounds(20, 160, 100, 30);
        quantity.setBounds(20, 200, 100, 30);
        
        bvRed.setBounds(70, 40, 70, 30);
        bvGreen.setBounds(140, 40, 70, 30);
        bvMilk.setBounds(210, 40, 70, 30);
        
        noIce.setBounds(70, 80, 70, 30);
        littleIce.setBounds(140, 80, 70, 30);
        normalIce.setBounds(210, 80, 70, 30);
        
        noSugar.setBounds(70, 120, 70, 30);
        halfSugar.setBounds(140, 120, 70, 30);
        fullSugar.setBounds(210, 120, 70, 30);
        
        kaiza.setBounds(70, 160, 70, 30);
        wind.setBounds(140, 160, 70, 30);
        thousandisland.setBounds(210, 160, 70, 30);
                
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1); // 設定 JSpinner 的最小、最大值和初始值
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setBounds(110, 205, 60, 30);
        
        beverageFrame.add(title);
        beverageFrame.add(bv);
        beverageFrame.add(bvRed);
        beverageFrame.add(bvGreen);
        beverageFrame.add(bvMilk);
        beverageFrame.add(ice);
        beverageFrame.add(noIce);
        beverageFrame.add(littleIce);
        beverageFrame.add(normalIce);
        beverageFrame.add(sugar);
        beverageFrame.add(noSugar);
        beverageFrame.add(halfSugar);
        beverageFrame.add(fullSugar);
        beverageFrame.add(sala);
        beverageFrame.add(kaiza);
        beverageFrame.add(wind);
        beverageFrame.add(thousandisland);
        beverageFrame.add(quantity);
        beverageFrame.add(quantitySpinner);
        
        // 新增取消按鈕
        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(30, 300, 100, 30);
        beverageFrame.add(cancelButton);

        // 新增確定按鈕
        JButton confirmButton = new JButton("確定");
        confirmButton.setBounds(150, 300, 100, 30);
        beverageFrame.add(confirmButton);
        
        // 設定取消按鈕的操作
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	beverageFrame.dispose(); // 關閉視窗
            }
        });        
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 
            	if (!bvRed.isSelected() && !bvGreen.isSelected() && !bvMilk.isSelected()) {
                    JOptionPane.showMessageDialog(beverageFrame, "請選擇飲料", "提示", JOptionPane.WARNING_MESSAGE);
                }
            	else if (!noIce.isSelected() && !littleIce.isSelected() && !normalIce.isSelected()) {
                    JOptionPane.showMessageDialog(beverageFrame, "請選擇冰塊選項", "提示", JOptionPane.WARNING_MESSAGE);
                }
            	else if (!noSugar.isSelected() && !halfSugar.isSelected() && !fullSugar.isSelected()) {
                        JOptionPane.showMessageDialog(beverageFrame, "請選擇甜度選項", "提示", JOptionPane.WARNING_MESSAGE);
                    }
            	else if (!kaiza.isSelected() && !wind.isSelected() && !thousandisland.isSelected()) {
                    JOptionPane.showMessageDialog(beverageFrame, "請選擇沙拉醬", "提示", JOptionPane.WARNING_MESSAGE);
                }
            	else {
            		
            		String selectedbv = "";
                    if (bvRed.isSelected()) {
                    	selectedbv = "紅茶";
                    } else if (bvGreen.isSelected()) {
                    	selectedbv = "綠茶";
                    } else if (bvMilk.isSelected()) {
                    	selectedbv = "奶茶";
                    }

            		String selectedSugar = "";
                    if (noSugar.isSelected()) {
                        selectedSugar = "無糖";
                    } else if (halfSugar.isSelected()) {
                        selectedSugar = "半糖";
                    } else if (fullSugar.isSelected()) {
                        selectedSugar = "全糖";
                    }
                    
                    String selectedIce = "";
                    if (noIce.isSelected()) {
                        selectedIce = "無冰";
                    } else if (littleIce.isSelected()) {
                        selectedIce = "少冰";
                    } else if (normalIce.isSelected()) {
                        selectedIce = "正常冰";
                    }
                    String selectedSala = "";
                    if (kaiza.isSelected()) {
                    	selectedSala = "凱薩";
                    } else if (wind.isSelected()) {
                    	selectedSala = "和風";
                    } else if (thousandisland.isSelected()) {
                    	selectedSala = "千島";
                    }                   
                    int selectedQuantity = (int) quantitySpinner.getValue();                   
                    // 建立提示訊息字串
                    String message = "您選擇的項目:輕食時光\n";
                    message += selectedbv + " \n";
                    message += "甜度: " + selectedSugar + "\n";
                    message += "冰塊: " + selectedIce + "\n";
                    message += "沙拉醬: " + selectedSala + "\n";
                    message += "數量: " + selectedQuantity;
                    // 顯示提示訊息視窗
                    JOptionPane.showMessageDialog(beverageFrame, message, "訂單詳情", JOptionPane.INFORMATION_MESSAGE);
                    
                    beverageFrame.dispose();
                    
                    item[i] = "輕食時光";
                    qty[i] = selectedQuantity;
                    cash[i] = 85*selectedQuantity;
                    items.add(item[i]);
                    qtys.add(qty[i]);
                    cashes.add(cash[i]);
                    i++;
                    cashcash += 85*selectedQuantity;
                }
            }
        });
        beverageFrame.setVisible(true);
	}
	// 遇見幸福
	public static void meetlove() {
		JFrame beverageFrame = new JFrame("遇見幸福");
        beverageFrame.setSize(300, 400);
        beverageFrame.setLayout(null);
        beverageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 關閉飲料設定視窗時不影響主視窗

        JPanel panel = new JPanel();
        beverageFrame.add(panel);
        
        JLabel title, bv, ice, sugar, sala, quantity;
        title = new JLabel("遇見幸福：法國吐司、德式香腸、沙拉");
        title.setFont((new Font("新細明體", Font.ITALIC, 14)));
        bv = new JLabel("飲料");
        ice = new JLabel("冰塊");
        sugar = new JLabel("甜度");
        sala = new JLabel("沙拉醬");
        quantity = new JLabel("數量");
        
        JRadioButton bvRed = new JRadioButton("紅茶");
        JRadioButton bvGreen = new JRadioButton("綠茶");
        JRadioButton bvMilk = new JRadioButton("奶茶");
        ButtonGroup bvGroup = new ButtonGroup();
        bvGroup.add(bvRed);
        bvGroup.add(bvGreen);
        bvGroup.add(bvMilk);

        JRadioButton noIce = new JRadioButton("無冰");
        JRadioButton littleIce = new JRadioButton("少冰");
        JRadioButton normalIce = new JRadioButton("正常冰");
        ButtonGroup iceGroup = new ButtonGroup();
        iceGroup.add(noIce);
        iceGroup.add(littleIce);
        iceGroup.add(normalIce);
        
        JRadioButton noSugar = new JRadioButton("無糖");
        JRadioButton halfSugar = new JRadioButton("半糖");
        JRadioButton fullSugar = new JRadioButton("全糖");
        ButtonGroup sugarGroup = new ButtonGroup();
        sugarGroup.add(noSugar);
        sugarGroup.add(halfSugar);
        sugarGroup.add(fullSugar);
        
        JRadioButton kaiza = new JRadioButton("凱撒");
        JRadioButton wind = new JRadioButton("和風");
        JRadioButton thousandisland = new JRadioButton("千島");
        ButtonGroup salaGroup = new ButtonGroup();
        salaGroup.add(kaiza);
        salaGroup.add(wind);
        salaGroup.add(thousandisland);

        title.setBounds(20, 10, 300, 30);
        bv.setBounds(20, 40, 100, 30);
        ice.setBounds(20, 80, 100, 30);
        sugar.setBounds(20, 120, 100, 30);
        sala.setBounds(20, 160, 100, 30);
        quantity.setBounds(20, 200, 100, 30);
        
        bvRed.setBounds(70, 40, 70, 30);
        bvGreen.setBounds(140, 40, 70, 30);
        bvMilk.setBounds(210, 40, 70, 30);
        
        noIce.setBounds(70, 80, 70, 30);
        littleIce.setBounds(140, 80, 70, 30);
        normalIce.setBounds(210, 80, 70, 30);
        
        noSugar.setBounds(70, 120, 70, 30);
        halfSugar.setBounds(140, 120, 70, 30);
        fullSugar.setBounds(210, 120, 70, 30);
        
        kaiza.setBounds(70, 160, 70, 30);
        wind.setBounds(140, 160, 70, 30);
        thousandisland.setBounds(210, 160, 70, 30);
                
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1); // 設定 JSpinner 的最小、最大值和初始值
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setBounds(110, 205, 60, 30);
        
        beverageFrame.add(title);
        beverageFrame.add(bv);
        beverageFrame.add(bvRed);
        beverageFrame.add(bvGreen);
        beverageFrame.add(bvMilk);
        beverageFrame.add(ice);
        beverageFrame.add(noIce);
        beverageFrame.add(littleIce);
        beverageFrame.add(normalIce);
        beverageFrame.add(sugar);
        beverageFrame.add(noSugar);
        beverageFrame.add(halfSugar);
        beverageFrame.add(fullSugar);
        beverageFrame.add(sala);
        beverageFrame.add(kaiza);
        beverageFrame.add(wind);
        beverageFrame.add(thousandisland);
        beverageFrame.add(quantity);
        beverageFrame.add(quantitySpinner);
        
        // 新增取消按鈕
        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(30, 300, 100, 30);
        beverageFrame.add(cancelButton);

        // 新增確定按鈕
        JButton confirmButton = new JButton("確定");
        confirmButton.setBounds(150, 300, 100, 30);
        beverageFrame.add(confirmButton);
        
        // 設定取消按鈕的操作
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	beverageFrame.dispose(); // 關閉視窗
            }
        });        
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 
            	if (!bvRed.isSelected() && !bvGreen.isSelected() && !bvMilk.isSelected()) {
                    JOptionPane.showMessageDialog(beverageFrame, "請選擇飲料", "提示", JOptionPane.WARNING_MESSAGE);
                }
            	else if (!noIce.isSelected() && !littleIce.isSelected() && !normalIce.isSelected()) {
                    JOptionPane.showMessageDialog(beverageFrame, "請選擇冰塊選項", "提示", JOptionPane.WARNING_MESSAGE);
                }
            	else if (!noSugar.isSelected() && !halfSugar.isSelected() && !fullSugar.isSelected()) {
                        JOptionPane.showMessageDialog(beverageFrame, "請選擇甜度選項", "提示", JOptionPane.WARNING_MESSAGE);
                    }
            	else if (!kaiza.isSelected() && !wind.isSelected() && !thousandisland.isSelected()) {
                    JOptionPane.showMessageDialog(beverageFrame, "請選擇沙拉醬", "提示", JOptionPane.WARNING_MESSAGE);
                }
            	else {
            		
            		String selectedbv = "";
                    if (bvRed.isSelected()) {
                    	selectedbv = "紅茶";
                    } else if (bvGreen.isSelected()) {
                    	selectedbv = "綠茶";
                    } else if (bvMilk.isSelected()) {
                    	selectedbv = "奶茶";
                    }

            		String selectedSugar = "";
                    if (noSugar.isSelected()) {
                        selectedSugar = "無糖";
                    } else if (halfSugar.isSelected()) {
                        selectedSugar = "半糖";
                    } else if (fullSugar.isSelected()) {
                        selectedSugar = "全糖";
                    }
                    
                    String selectedIce = "";
                    if (noIce.isSelected()) {
                        selectedIce = "無冰";
                    } else if (littleIce.isSelected()) {
                        selectedIce = "少冰";
                    } else if (normalIce.isSelected()) {
                        selectedIce = "正常冰";
                    }
                    String selectedSala = "";
                    if (kaiza.isSelected()) {
                    	selectedSala = "凱薩";
                    } else if (wind.isSelected()) {
                    	selectedSala = "和風";
                    } else if (thousandisland.isSelected()) {
                    	selectedSala = "千島";
                    }                   
                    int selectedQuantity = (int) quantitySpinner.getValue();                   
                    // 建立提示訊息字串
                    String message = "您選擇的項目:遇見幸福\n";
                    message += selectedbv + " \n";
                    message += "甜度: " + selectedSugar + "\n";
                    message += "冰塊: " + selectedIce + "\n";
                    message += "沙拉醬: " + selectedSala + "\n";
                    message += "數量: " + selectedQuantity;
                    // 顯示提示訊息視窗
                    JOptionPane.showMessageDialog(beverageFrame, message, "訂單詳情", JOptionPane.INFORMATION_MESSAGE);
                    
                    beverageFrame.dispose();
                    
                    item[i] = "遇見幸福";
                    qty[i] = selectedQuantity;
                    cash[i] = 85*selectedQuantity;
                    items.add(item[i]);
                    qtys.add(qty[i]);
                    cashes.add(cash[i]);
                    i++;
                    cashcash += 85*selectedQuantity;
                }
            }
        });
        beverageFrame.setVisible(true);
	}
	// 法式茶旅
	public static void francetea() {
		JFrame beverageFrame = new JFrame("法式茶旅");
        beverageFrame.setSize(300, 400);
        beverageFrame.setLayout(null);
        beverageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 關閉飲料設定視窗時不影響主視窗

        JPanel panel = new JPanel();
        beverageFrame.add(panel);
        
        JLabel title, bv, ice, sugar, sala, quantity;
        title = new JLabel("法式茶旅：香蒜麵包、培根、薯餅、炒蛋、沙拉");
        title.setFont((new Font("新細明體", Font.ITALIC, 12)));
        bv = new JLabel("飲料");
        ice = new JLabel("冰塊");
        sugar = new JLabel("甜度");
        sala = new JLabel("沙拉醬");
        quantity = new JLabel("數量");
        
        JRadioButton bvRed = new JRadioButton("紅茶");
        JRadioButton bvGreen = new JRadioButton("綠茶");
        JRadioButton bvMilk = new JRadioButton("奶茶");
        ButtonGroup bvGroup = new ButtonGroup();
        bvGroup.add(bvRed);
        bvGroup.add(bvGreen);
        bvGroup.add(bvMilk);

        JRadioButton noIce = new JRadioButton("無冰");
        JRadioButton littleIce = new JRadioButton("少冰");
        JRadioButton normalIce = new JRadioButton("正常冰");
        ButtonGroup iceGroup = new ButtonGroup();
        iceGroup.add(noIce);
        iceGroup.add(littleIce);
        iceGroup.add(normalIce);
        
        JRadioButton noSugar = new JRadioButton("無糖");
        JRadioButton halfSugar = new JRadioButton("半糖");
        JRadioButton fullSugar = new JRadioButton("全糖");
        ButtonGroup sugarGroup = new ButtonGroup();
        sugarGroup.add(noSugar);
        sugarGroup.add(halfSugar);
        sugarGroup.add(fullSugar);
        
        JRadioButton kaiza = new JRadioButton("凱撒");
        JRadioButton wind = new JRadioButton("和風");
        JRadioButton thousandisland = new JRadioButton("千島");
        ButtonGroup salaGroup = new ButtonGroup();
        salaGroup.add(kaiza);
        salaGroup.add(wind);
        salaGroup.add(thousandisland);

        title.setBounds(20, 10, 300, 30);
        bv.setBounds(20, 40, 100, 30);
        ice.setBounds(20, 80, 100, 30);
        sugar.setBounds(20, 120, 100, 30);
        sala.setBounds(20, 160, 100, 30);
        quantity.setBounds(20, 200, 100, 30);
        
        bvRed.setBounds(70, 40, 70, 30);
        bvGreen.setBounds(140, 40, 70, 30);
        bvMilk.setBounds(210, 40, 70, 30);
        
        noIce.setBounds(70, 80, 70, 30);
        littleIce.setBounds(140, 80, 70, 30);
        normalIce.setBounds(210, 80, 70, 30);
        
        noSugar.setBounds(70, 120, 70, 30);
        halfSugar.setBounds(140, 120, 70, 30);
        fullSugar.setBounds(210, 120, 70, 30);
        
        kaiza.setBounds(70, 160, 70, 30);
        wind.setBounds(140, 160, 70, 30);
        thousandisland.setBounds(210, 160, 70, 30);
                
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1); // 設定 JSpinner 的最小、最大值和初始值
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setBounds(110, 205, 60, 30);
        
        beverageFrame.add(title);
        beverageFrame.add(bv);
        beverageFrame.add(bvRed);
        beverageFrame.add(bvGreen);
        beverageFrame.add(bvMilk);
        beverageFrame.add(ice);
        beverageFrame.add(noIce);
        beverageFrame.add(littleIce);
        beverageFrame.add(normalIce);
        beverageFrame.add(sugar);
        beverageFrame.add(noSugar);
        beverageFrame.add(halfSugar);
        beverageFrame.add(fullSugar);
        beverageFrame.add(sala);
        beverageFrame.add(kaiza);
        beverageFrame.add(wind);
        beverageFrame.add(thousandisland);
        beverageFrame.add(quantity);
        beverageFrame.add(quantitySpinner);
        
        // 新增取消按鈕
        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(30, 300, 100, 30);
        beverageFrame.add(cancelButton);

        // 新增確定按鈕
        JButton confirmButton = new JButton("確定");
        confirmButton.setBounds(150, 300, 100, 30);
        beverageFrame.add(confirmButton);
        
        // 設定取消按鈕的操作
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	beverageFrame.dispose(); // 關閉視窗
            }
        });        
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 
            	if (!bvRed.isSelected() && !bvGreen.isSelected() && !bvMilk.isSelected()) {
                    JOptionPane.showMessageDialog(beverageFrame, "請選擇飲料", "提示", JOptionPane.WARNING_MESSAGE);
                }
            	else if (!noIce.isSelected() && !littleIce.isSelected() && !normalIce.isSelected()) {
                    JOptionPane.showMessageDialog(beverageFrame, "請選擇冰塊選項", "提示", JOptionPane.WARNING_MESSAGE);
                }
            	else if (!noSugar.isSelected() && !halfSugar.isSelected() && !fullSugar.isSelected()) {
                        JOptionPane.showMessageDialog(beverageFrame, "請選擇甜度選項", "提示", JOptionPane.WARNING_MESSAGE);
                    }
            	else if (!kaiza.isSelected() && !wind.isSelected() && !thousandisland.isSelected()) {
                    JOptionPane.showMessageDialog(beverageFrame, "請選擇沙拉醬", "提示", JOptionPane.WARNING_MESSAGE);
                }
            	else {
            		
            		String selectedbv = "";
                    if (bvRed.isSelected()) {
                    	selectedbv = "紅茶";
                    } else if (bvGreen.isSelected()) {
                    	selectedbv = "綠茶";
                    } else if (bvMilk.isSelected()) {
                    	selectedbv = "奶茶";
                    }

            		String selectedSugar = "";
                    if (noSugar.isSelected()) {
                        selectedSugar = "無糖";
                    } else if (halfSugar.isSelected()) {
                        selectedSugar = "半糖";
                    } else if (fullSugar.isSelected()) {
                        selectedSugar = "全糖";
                    }
                    
                    String selectedIce = "";
                    if (noIce.isSelected()) {
                        selectedIce = "無冰";
                    } else if (littleIce.isSelected()) {
                        selectedIce = "少冰";
                    } else if (normalIce.isSelected()) {
                        selectedIce = "正常冰";
                    }
                    String selectedSala = "";
                    if (kaiza.isSelected()) {
                    	selectedSala = "凱薩";
                    } else if (wind.isSelected()) {
                    	selectedSala = "和風";
                    } else if (thousandisland.isSelected()) {
                    	selectedSala = "千島";
                    }                   
                    int selectedQuantity = (int) quantitySpinner.getValue();                   
                    // 建立提示訊息字串
                    String message = "您選擇的項目:法式茶旅\n";
                    message += selectedbv + " \n";
                    message += "甜度: " + selectedSugar + "\n";
                    message += "冰塊: " + selectedIce + "\n";
                    message += "沙拉醬: " + selectedSala + "\n";
                    message += "數量: " + selectedQuantity;
                    // 顯示提示訊息視窗
                    JOptionPane.showMessageDialog(beverageFrame, message, "訂單詳情", JOptionPane.INFORMATION_MESSAGE);
                    
                    beverageFrame.dispose();
                    
                    item[i] = "法式茶旅";
                    qty[i] = selectedQuantity;
                    cash[i] = 90*selectedQuantity;
                    items.add(item[i]);
                    qtys.add(qty[i]);
                    cashes.add(cash[i]);
                    i++;
                    cashcash += 90*selectedQuantity;
                }
            }
        });
        beverageFrame.setVisible(true);
	}
	// 活力滿滿
	public static void powerfull() {
		JFrame beverageFrame = new JFrame("活力滿滿");
        beverageFrame.setSize(300, 400);
        beverageFrame.setLayout(null);
        beverageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 關閉飲料設定視窗時不影響主視窗

        JPanel panel = new JPanel();
        beverageFrame.add(panel);
        
        JLabel title, bv, ice, sugar, sala, quantity;
        title = new JLabel("活力滿滿：香蒜麵包、豬排、薯餅蛋塔、沙拉");
        title.setFont((new Font("新細明體", Font.ITALIC, 12)));
        bv = new JLabel("飲料");
        ice = new JLabel("冰塊");
        sugar = new JLabel("甜度");
        sala = new JLabel("沙拉醬");
        quantity = new JLabel("數量");
        
        JRadioButton bvRed = new JRadioButton("紅茶");
        JRadioButton bvGreen = new JRadioButton("綠茶");
        JRadioButton bvMilk = new JRadioButton("奶茶");
        ButtonGroup bvGroup = new ButtonGroup();
        bvGroup.add(bvRed);
        bvGroup.add(bvGreen);
        bvGroup.add(bvMilk);

        JRadioButton noIce = new JRadioButton("無冰");
        JRadioButton littleIce = new JRadioButton("少冰");
        JRadioButton normalIce = new JRadioButton("正常冰");
        ButtonGroup iceGroup = new ButtonGroup();
        iceGroup.add(noIce);
        iceGroup.add(littleIce);
        iceGroup.add(normalIce);
        
        JRadioButton noSugar = new JRadioButton("無糖");
        JRadioButton halfSugar = new JRadioButton("半糖");
        JRadioButton fullSugar = new JRadioButton("全糖");
        ButtonGroup sugarGroup = new ButtonGroup();
        sugarGroup.add(noSugar);
        sugarGroup.add(halfSugar);
        sugarGroup.add(fullSugar);
        
        JRadioButton kaiza = new JRadioButton("凱撒");
        JRadioButton wind = new JRadioButton("和風");
        JRadioButton thousandisland = new JRadioButton("千島");
        ButtonGroup salaGroup = new ButtonGroup();
        salaGroup.add(kaiza);
        salaGroup.add(wind);
        salaGroup.add(thousandisland);

        title.setBounds(20, 10, 300, 30);
        bv.setBounds(20, 40, 100, 30);
        ice.setBounds(20, 80, 100, 30);
        sugar.setBounds(20, 120, 100, 30);
        sala.setBounds(20, 160, 100, 30);
        quantity.setBounds(20, 200, 100, 30);
        
        bvRed.setBounds(70, 40, 70, 30);
        bvGreen.setBounds(140, 40, 70, 30);
        bvMilk.setBounds(210, 40, 70, 30);
        
        noIce.setBounds(70, 80, 70, 30);
        littleIce.setBounds(140, 80, 70, 30);
        normalIce.setBounds(210, 80, 70, 30);
        
        noSugar.setBounds(70, 120, 70, 30);
        halfSugar.setBounds(140, 120, 70, 30);
        fullSugar.setBounds(210, 120, 70, 30);
        
        kaiza.setBounds(70, 160, 70, 30);
        wind.setBounds(140, 160, 70, 30);
        thousandisland.setBounds(210, 160, 70, 30);
                
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1); // 設定 JSpinner 的最小、最大值和初始值
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setBounds(110, 205, 60, 30);
        
        beverageFrame.add(title);
        beverageFrame.add(bv);
        beverageFrame.add(bvRed);
        beverageFrame.add(bvGreen);
        beverageFrame.add(bvMilk);
        beverageFrame.add(ice);
        beverageFrame.add(noIce);
        beverageFrame.add(littleIce);
        beverageFrame.add(normalIce);
        beverageFrame.add(sugar);
        beverageFrame.add(noSugar);
        beverageFrame.add(halfSugar);
        beverageFrame.add(fullSugar);
        beverageFrame.add(sala);
        beverageFrame.add(kaiza);
        beverageFrame.add(wind);
        beverageFrame.add(thousandisland);
        beverageFrame.add(quantity);
        beverageFrame.add(quantitySpinner);
        
        // 新增取消按鈕
        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(30, 300, 100, 30);
        beverageFrame.add(cancelButton);

        // 新增確定按鈕
        JButton confirmButton = new JButton("確定");
        confirmButton.setBounds(150, 300, 100, 30);
        beverageFrame.add(confirmButton);
        
        // 設定取消按鈕的操作
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	beverageFrame.dispose(); // 關閉視窗
            }
        });        
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 
            	if (!bvRed.isSelected() && !bvGreen.isSelected() && !bvMilk.isSelected()) {
                    JOptionPane.showMessageDialog(beverageFrame, "請選擇飲料", "提示", JOptionPane.WARNING_MESSAGE);
                }
            	else if (!noIce.isSelected() && !littleIce.isSelected() && !normalIce.isSelected()) {
                    JOptionPane.showMessageDialog(beverageFrame, "請選擇冰塊選項", "提示", JOptionPane.WARNING_MESSAGE);
                }
            	else if (!noSugar.isSelected() && !halfSugar.isSelected() && !fullSugar.isSelected()) {
                        JOptionPane.showMessageDialog(beverageFrame, "請選擇甜度選項", "提示", JOptionPane.WARNING_MESSAGE);
                    }
            	else if (!kaiza.isSelected() && !wind.isSelected() && !thousandisland.isSelected()) {
                    JOptionPane.showMessageDialog(beverageFrame, "請選擇沙拉醬", "提示", JOptionPane.WARNING_MESSAGE);
                }
            	else {
            		
            		String selectedbv = "";
                    if (bvRed.isSelected()) {
                    	selectedbv = "紅茶";
                    } else if (bvGreen.isSelected()) {
                    	selectedbv = "綠茶";
                    } else if (bvMilk.isSelected()) {
                    	selectedbv = "奶茶";
                    }

            		String selectedSugar = "";
                    if (noSugar.isSelected()) {
                        selectedSugar = "無糖";
                    } else if (halfSugar.isSelected()) {
                        selectedSugar = "半糖";
                    } else if (fullSugar.isSelected()) {
                        selectedSugar = "全糖";
                    }
                    
                    String selectedIce = "";
                    if (noIce.isSelected()) {
                        selectedIce = "無冰";
                    } else if (littleIce.isSelected()) {
                        selectedIce = "少冰";
                    } else if (normalIce.isSelected()) {
                        selectedIce = "正常冰";
                    }
                    String selectedSala = "";
                    if (kaiza.isSelected()) {
                    	selectedSala = "凱薩";
                    } else if (wind.isSelected()) {
                    	selectedSala = "和風";
                    } else if (thousandisland.isSelected()) {
                    	selectedSala = "千島";
                    }                   
                    int selectedQuantity = (int) quantitySpinner.getValue();                   
                    // 建立提示訊息字串
                    String message = "您選擇的項目:活力滿滿\n";
                    message += selectedbv + " \n";
                    message += "甜度: " + selectedSugar + "\n";
                    message += "冰塊: " + selectedIce + "\n";
                    message += "沙拉醬: " + selectedSala + "\n";
                    message += "數量: " + selectedQuantity;
                    // 顯示提示訊息視窗
                    JOptionPane.showMessageDialog(beverageFrame, message, "訂單詳情", JOptionPane.INFORMATION_MESSAGE);
                    
                    beverageFrame.dispose();
                    
                    item[i] = "活力滿滿";
                    qty[i] = selectedQuantity;
                    cash[i] = 90*selectedQuantity;
                    items.add(item[i]);
                    qtys.add(qty[i]);
                    cashes.add(cash[i]);
                    i++;
                    cashcash += 90*selectedQuantity;
                }
            }
        });
        beverageFrame.setVisible(true);
	}
	// 千層蛋燒
	public static void thousandegg() {
		JFrame beverageFrame = new JFrame("千層蛋燒");
        beverageFrame.setSize(300, 400);
        beverageFrame.setLayout(null);
        beverageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 關閉飲料設定視窗時不影響主視窗

        JPanel panel = new JPanel();
        beverageFrame.add(panel);
        
        JLabel title, bv, ice, sugar, sala, quantity;
        title = new JLabel("千層蛋燒：燒餅、蛋、玉米、沙拉");
        title.setFont((new Font("新細明體", Font.ITALIC, 16)));
        bv = new JLabel("飲料");
        ice = new JLabel("冰塊");
        sugar = new JLabel("甜度");
        sala = new JLabel("沙拉醬");
        quantity = new JLabel("數量");
        
        JRadioButton bvRed = new JRadioButton("紅茶");
        JRadioButton bvGreen = new JRadioButton("綠茶");
        JRadioButton bvMilk = new JRadioButton("奶茶");
        ButtonGroup bvGroup = new ButtonGroup();
        bvGroup.add(bvRed);
        bvGroup.add(bvGreen);
        bvGroup.add(bvMilk);

        JRadioButton noIce = new JRadioButton("無冰");
        JRadioButton littleIce = new JRadioButton("少冰");
        JRadioButton normalIce = new JRadioButton("正常冰");
        ButtonGroup iceGroup = new ButtonGroup();
        iceGroup.add(noIce);
        iceGroup.add(littleIce);
        iceGroup.add(normalIce);
        
        JRadioButton noSugar = new JRadioButton("無糖");
        JRadioButton halfSugar = new JRadioButton("半糖");
        JRadioButton fullSugar = new JRadioButton("全糖");
        ButtonGroup sugarGroup = new ButtonGroup();
        sugarGroup.add(noSugar);
        sugarGroup.add(halfSugar);
        sugarGroup.add(fullSugar);
        
        JRadioButton kaiza = new JRadioButton("凱撒");
        JRadioButton wind = new JRadioButton("和風");
        JRadioButton thousandisland = new JRadioButton("千島");
        ButtonGroup salaGroup = new ButtonGroup();
        salaGroup.add(kaiza);
        salaGroup.add(wind);
        salaGroup.add(thousandisland);

        title.setBounds(20, 10, 300, 30);
        bv.setBounds(20, 40, 100, 30);
        ice.setBounds(20, 80, 100, 30);
        sugar.setBounds(20, 120, 100, 30);
        sala.setBounds(20, 160, 100, 30);
        quantity.setBounds(20, 200, 100, 30);
        
        bvRed.setBounds(70, 40, 70, 30);
        bvGreen.setBounds(140, 40, 70, 30);
        bvMilk.setBounds(210, 40, 70, 30);
        
        noIce.setBounds(70, 80, 70, 30);
        littleIce.setBounds(140, 80, 70, 30);
        normalIce.setBounds(210, 80, 70, 30);
        
        noSugar.setBounds(70, 120, 70, 30);
        halfSugar.setBounds(140, 120, 70, 30);
        fullSugar.setBounds(210, 120, 70, 30);
        
        kaiza.setBounds(70, 160, 70, 30);
        wind.setBounds(140, 160, 70, 30);
        thousandisland.setBounds(210, 160, 70, 30);
                
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1); // 設定 JSpinner 的最小、最大值和初始值
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setBounds(110, 205, 60, 30);
        
        beverageFrame.add(title);
        beverageFrame.add(bv);
        beverageFrame.add(bvRed);
        beverageFrame.add(bvGreen);
        beverageFrame.add(bvMilk);
        beverageFrame.add(ice);
        beverageFrame.add(noIce);
        beverageFrame.add(littleIce);
        beverageFrame.add(normalIce);
        beverageFrame.add(sugar);
        beverageFrame.add(noSugar);
        beverageFrame.add(halfSugar);
        beverageFrame.add(fullSugar);
        beverageFrame.add(sala);
        beverageFrame.add(kaiza);
        beverageFrame.add(wind);
        beverageFrame.add(thousandisland);
        beverageFrame.add(quantity);
        beverageFrame.add(quantitySpinner);
        
        // 新增取消按鈕
        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(30, 300, 100, 30);
        beverageFrame.add(cancelButton);

        // 新增確定按鈕
        JButton confirmButton = new JButton("確定");
        confirmButton.setBounds(150, 300, 100, 30);
        beverageFrame.add(confirmButton);
        
        // 設定取消按鈕的操作
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	beverageFrame.dispose(); // 關閉視窗
            }
        });        
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 
            	if (!bvRed.isSelected() && !bvGreen.isSelected() && !bvMilk.isSelected()) {
                    JOptionPane.showMessageDialog(beverageFrame, "請選擇飲料", "提示", JOptionPane.WARNING_MESSAGE);
                }
            	else if (!noIce.isSelected() && !littleIce.isSelected() && !normalIce.isSelected()) {
                    JOptionPane.showMessageDialog(beverageFrame, "請選擇冰塊選項", "提示", JOptionPane.WARNING_MESSAGE);
                }
            	else if (!noSugar.isSelected() && !halfSugar.isSelected() && !fullSugar.isSelected()) {
                        JOptionPane.showMessageDialog(beverageFrame, "請選擇甜度選項", "提示", JOptionPane.WARNING_MESSAGE);
                    }
            	else if (!kaiza.isSelected() && !wind.isSelected() && !thousandisland.isSelected()) {
                    JOptionPane.showMessageDialog(beverageFrame, "請選擇沙拉醬", "提示", JOptionPane.WARNING_MESSAGE);
                }
            	else {
            		
            		String selectedbv = "";
                    if (bvRed.isSelected()) {
                    	selectedbv = "紅茶";
                    } else if (bvGreen.isSelected()) {
                    	selectedbv = "綠茶";
                    } else if (bvMilk.isSelected()) {
                    	selectedbv = "奶茶";
                    }

            		String selectedSugar = "";
                    if (noSugar.isSelected()) {
                        selectedSugar = "無糖";
                    } else if (halfSugar.isSelected()) {
                        selectedSugar = "半糖";
                    } else if (fullSugar.isSelected()) {
                        selectedSugar = "全糖";
                    }
                    
                    String selectedIce = "";
                    if (noIce.isSelected()) {
                        selectedIce = "無冰";
                    } else if (littleIce.isSelected()) {
                        selectedIce = "少冰";
                    } else if (normalIce.isSelected()) {
                        selectedIce = "正常冰";
                    }
                    String selectedSala = "";
                    if (kaiza.isSelected()) {
                    	selectedSala = "凱薩";
                    } else if (wind.isSelected()) {
                    	selectedSala = "和風";
                    } else if (thousandisland.isSelected()) {
                    	selectedSala = "千島";
                    }                   
                    int selectedQuantity = (int) quantitySpinner.getValue();                   
                    // 建立提示訊息字串
                    String message = "您選擇的項目:千層蛋燒\n";
                    message += selectedbv + " \n";
                    message += "甜度: " + selectedSugar + "\n";
                    message += "冰塊: " + selectedIce + "\n";
                    message += "沙拉醬: " + selectedSala + "\n";
                    message += "數量: " + selectedQuantity;
                    // 顯示提示訊息視窗
                    JOptionPane.showMessageDialog(beverageFrame, message, "訂單詳情", JOptionPane.INFORMATION_MESSAGE);
                    
                    beverageFrame.dispose();
                    
                    item[i] = "千層蛋燒";
                    qty[i] = selectedQuantity;
                    cash[i] = 100*selectedQuantity;
                    items.add(item[i]);
                    qtys.add(qty[i]);
                    cashes.add(cash[i]);
                    i++;
                    cashcash += 100*selectedQuantity;
                }
            }
        });
        beverageFrame.setVisible(true);
	}
	// 純牛漢堡排
	public static void beefhamber() {
		JFrame beverageFrame = new JFrame("純牛漢堡排");
        beverageFrame.setSize(300, 400);
        beverageFrame.setLayout(null);
        beverageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 關閉飲料設定視窗時不影響主視窗

        JPanel panel = new JPanel();
        beverageFrame.add(panel);
        
        JLabel title, bv, ice, sugar, sala, quantity;
        title = new JLabel("純牛漢堡排：厚牛、香蒜麵包、脆薯、沙拉");
        title.setFont((new Font("新細明體", Font.ITALIC, 12)));
        bv = new JLabel("飲料");
        ice = new JLabel("冰塊");
        sugar = new JLabel("甜度");
        sala = new JLabel("沙拉醬");
        quantity = new JLabel("數量");
        
        JRadioButton bvRed = new JRadioButton("紅茶");
        JRadioButton bvGreen = new JRadioButton("綠茶");
        JRadioButton bvMilk = new JRadioButton("奶茶");
        ButtonGroup bvGroup = new ButtonGroup();
        bvGroup.add(bvRed);
        bvGroup.add(bvGreen);
        bvGroup.add(bvMilk);

        JRadioButton noIce = new JRadioButton("無冰");
        JRadioButton littleIce = new JRadioButton("少冰");
        JRadioButton normalIce = new JRadioButton("正常冰");
        ButtonGroup iceGroup = new ButtonGroup();
        iceGroup.add(noIce);
        iceGroup.add(littleIce);
        iceGroup.add(normalIce);
        
        JRadioButton noSugar = new JRadioButton("無糖");
        JRadioButton halfSugar = new JRadioButton("半糖");
        JRadioButton fullSugar = new JRadioButton("全糖");
        ButtonGroup sugarGroup = new ButtonGroup();
        sugarGroup.add(noSugar);
        sugarGroup.add(halfSugar);
        sugarGroup.add(fullSugar);
        
        JRadioButton kaiza = new JRadioButton("凱撒");
        JRadioButton wind = new JRadioButton("和風");
        JRadioButton thousandisland = new JRadioButton("千島");
        ButtonGroup salaGroup = new ButtonGroup();
        salaGroup.add(kaiza);
        salaGroup.add(wind);
        salaGroup.add(thousandisland);

        title.setBounds(20, 10, 300, 30);
        bv.setBounds(20, 40, 100, 30);
        ice.setBounds(20, 80, 100, 30);
        sugar.setBounds(20, 120, 100, 30);
        sala.setBounds(20, 160, 100, 30);
        quantity.setBounds(20, 200, 100, 30);
        
        bvRed.setBounds(70, 40, 70, 30);
        bvGreen.setBounds(140, 40, 70, 30);
        bvMilk.setBounds(210, 40, 70, 30);
        
        noIce.setBounds(70, 80, 70, 30);
        littleIce.setBounds(140, 80, 70, 30);
        normalIce.setBounds(210, 80, 70, 30);
        
        noSugar.setBounds(70, 120, 70, 30);
        halfSugar.setBounds(140, 120, 70, 30);
        fullSugar.setBounds(210, 120, 70, 30);
        
        kaiza.setBounds(70, 160, 70, 30);
        wind.setBounds(140, 160, 70, 30);
        thousandisland.setBounds(210, 160, 70, 30);
                
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1); // 設定 JSpinner 的最小、最大值和初始值
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setBounds(110, 205, 60, 30);
        
        beverageFrame.add(title);
        beverageFrame.add(bv);
        beverageFrame.add(bvRed);
        beverageFrame.add(bvGreen);
        beverageFrame.add(bvMilk);
        beverageFrame.add(ice);
        beverageFrame.add(noIce);
        beverageFrame.add(littleIce);
        beverageFrame.add(normalIce);
        beverageFrame.add(sugar);
        beverageFrame.add(noSugar);
        beverageFrame.add(halfSugar);
        beverageFrame.add(fullSugar);
        beverageFrame.add(sala);
        beverageFrame.add(kaiza);
        beverageFrame.add(wind);
        beverageFrame.add(thousandisland);
        beverageFrame.add(quantity);
        beverageFrame.add(quantitySpinner);
        
        // 新增取消按鈕
        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(30, 300, 100, 30);
        beverageFrame.add(cancelButton);

        // 新增確定按鈕
        JButton confirmButton = new JButton("確定");
        confirmButton.setBounds(150, 300, 100, 30);
        beverageFrame.add(confirmButton);
        
        // 設定取消按鈕的操作
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	beverageFrame.dispose(); // 關閉視窗
            }
        });        
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 
            	if (!bvRed.isSelected() && !bvGreen.isSelected() && !bvMilk.isSelected()) {
                    JOptionPane.showMessageDialog(beverageFrame, "請選擇飲料", "提示", JOptionPane.WARNING_MESSAGE);
                }
            	else if (!noIce.isSelected() && !littleIce.isSelected() && !normalIce.isSelected()) {
                    JOptionPane.showMessageDialog(beverageFrame, "請選擇冰塊選項", "提示", JOptionPane.WARNING_MESSAGE);
                }
            	else if (!noSugar.isSelected() && !halfSugar.isSelected() && !fullSugar.isSelected()) {
                        JOptionPane.showMessageDialog(beverageFrame, "請選擇甜度選項", "提示", JOptionPane.WARNING_MESSAGE);
                    }
            	else if (!kaiza.isSelected() && !wind.isSelected() && !thousandisland.isSelected()) {
                    JOptionPane.showMessageDialog(beverageFrame, "請選擇沙拉醬", "提示", JOptionPane.WARNING_MESSAGE);
                }
            	else {
            		
            		String selectedbv = "";
                    if (bvRed.isSelected()) {
                    	selectedbv = "紅茶";
                    } else if (bvGreen.isSelected()) {
                    	selectedbv = "綠茶";
                    } else if (bvMilk.isSelected()) {
                    	selectedbv = "奶茶";
                    }

            		String selectedSugar = "";
                    if (noSugar.isSelected()) {
                        selectedSugar = "無糖";
                    } else if (halfSugar.isSelected()) {
                        selectedSugar = "半糖";
                    } else if (fullSugar.isSelected()) {
                        selectedSugar = "全糖";
                    }
                    
                    String selectedIce = "";
                    if (noIce.isSelected()) {
                        selectedIce = "無冰";
                    } else if (littleIce.isSelected()) {
                        selectedIce = "少冰";
                    } else if (normalIce.isSelected()) {
                        selectedIce = "正常冰";
                    }
                    String selectedSala = "";
                    if (kaiza.isSelected()) {
                    	selectedSala = "凱薩";
                    } else if (wind.isSelected()) {
                    	selectedSala = "和風";
                    } else if (thousandisland.isSelected()) {
                    	selectedSala = "千島";
                    }                   
                    int selectedQuantity = (int) quantitySpinner.getValue();                   
                    // 建立提示訊息字串
                    String message = "您選擇的項目:純牛漢堡排\n";
                    message += selectedbv + " \n";
                    message += "甜度: " + selectedSugar + "\n";
                    message += "冰塊: " + selectedIce + "\n";
                    message += "沙拉醬: " + selectedSala + "\n";
                    message += "數量: " + selectedQuantity;
                    // 顯示提示訊息視窗
                    JOptionPane.showMessageDialog(beverageFrame, message, "訂單詳情", JOptionPane.INFORMATION_MESSAGE);
                    
                    beverageFrame.dispose();
                    
                    item[i] = "純牛滿堡排";
                    qty[i] = selectedQuantity;
                    cash[i] = 120*selectedQuantity;
                    items.add(item[i]);
                    qtys.add(qty[i]);
                    cashes.add(cash[i]);
                    i++;
                    cashcash += 120*selectedQuantity;
                }
            }
        });
        beverageFrame.setVisible(true);
	}

	// 綠茶
	public static void greentea() {
        JFrame beverageFrame = new JFrame("飲料設定");
        beverageFrame.setSize(300, 400);
        beverageFrame.setLayout(null);
        beverageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 關閉飲料設定視窗時不影響主視窗

        JPanel panel = new JPanel();
        beverageFrame.add(panel);
        
        JLabel ice, sugar, quantity;
        ice = new JLabel("冰塊");
        sugar = new JLabel("甜度");
        quantity = new JLabel("數量");

        JRadioButton noSugar = new JRadioButton("無糖");
        JRadioButton halfSugar = new JRadioButton("半糖");
        JRadioButton fullSugar = new JRadioButton("全糖");
        ButtonGroup sugarGroup = new ButtonGroup();
        sugarGroup.add(noSugar);
        sugarGroup.add(halfSugar);
        sugarGroup.add(fullSugar);
        
        JRadioButton noIce = new JRadioButton("無冰");
        JRadioButton littleIce = new JRadioButton("少冰");
        JRadioButton normalIce = new JRadioButton("正常冰");
        ButtonGroup iceGroup = new ButtonGroup();
        iceGroup.add(noIce);
        iceGroup.add(littleIce);
        iceGroup.add(normalIce);

        ice.setBounds(20, 50, 100, 30);
        noIce.setBounds(70, 50, 70, 30);
        littleIce.setBounds(140, 50, 70, 30);
        normalIce.setBounds(210, 50, 70, 30);
        
        sugar.setBounds(20, 110, 100, 30);
        noSugar.setBounds(70, 110, 70, 30);
        halfSugar.setBounds(140, 110, 70, 30);
        fullSugar.setBounds(210, 110, 70, 30);
        
        quantity.setBounds(20, 170, 100, 30);
        
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1); // 設定 JSpinner 的最小、最大值和初始值
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setBounds(110, 175, 60, 30);
        
        beverageFrame.add(ice);
        beverageFrame.add(noIce);
        beverageFrame.add(littleIce);
        beverageFrame.add(normalIce);
        beverageFrame.add(sugar);
        beverageFrame.add(noSugar);
        beverageFrame.add(halfSugar);
        beverageFrame.add(fullSugar);
        beverageFrame.add(quantity);
        beverageFrame.add(quantitySpinner);
        
        // 新增取消按鈕
        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(30, 240, 100, 30);
        beverageFrame.add(cancelButton);

        // 新增確定按鈕
        JButton confirmButton = new JButton("確定");
        confirmButton.setBounds(150, 240, 100, 30);
        beverageFrame.add(confirmButton);
        
        // 設定取消按鈕的操作
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	beverageFrame.dispose(); // 關閉視窗
            }
        });        
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 檢查甜度和冰塊的JRadioButton是否都未選擇
            	if (!noIce.isSelected() && !littleIce.isSelected() && !normalIce.isSelected()) {
                    JOptionPane.showMessageDialog(beverageFrame, "請選擇冰塊選項", "提示", JOptionPane.WARNING_MESSAGE);
                }
            	else if (!noSugar.isSelected() && !halfSugar.isSelected() && !fullSugar.isSelected()) {
                        JOptionPane.showMessageDialog(beverageFrame, "請選擇甜度選項", "提示", JOptionPane.WARNING_MESSAGE);
                    }
            	else {
                    // 如果有兩個選擇，執行確定的操作
                    // 這裡可以將所選的甜度和冰塊選項以及數量儲存到資料庫或進行其他操作
            		String selectedSugar = "";
                    if (noSugar.isSelected()) {
                        selectedSugar = "無糖";
                    } else if (halfSugar.isSelected()) {
                        selectedSugar = "半糖";
                    } else if (fullSugar.isSelected()) {
                        selectedSugar = "全糖";
                    }
                    
                    String selectedIce = "";
                    if (noIce.isSelected()) {
                        selectedIce = "無冰";
                    } else if (littleIce.isSelected()) {
                        selectedIce = "少冰";
                    } else if (normalIce.isSelected()) {
                        selectedIce = "正常冰";
                    }                   
                    int selectedQuantity = (int) quantitySpinner.getValue();                   
                    // 建立提示訊息字串
                    String message = "您選擇的項目:\n";
                    message += "綠茶\n";
                    message += "甜度: " + selectedSugar + "\n";
                    message += "冰塊: " + selectedIce + "\n";
                    message += "數量: " + selectedQuantity;
                    // 顯示提示訊息視窗
                    JOptionPane.showMessageDialog(beverageFrame, message, "訂單詳情", JOptionPane.INFORMATION_MESSAGE);
                    
                    beverageFrame.dispose();
                    
                    item[i] = "綠茶";
                    qty[i] = selectedQuantity;
                    cash[i] = 20*selectedQuantity;
                    items.add(item[i]);
                    qtys.add(qty[i]);
                    cashes.add(cash[i]);
                    i++;
                    cashcash += 20*selectedQuantity;
                }
            }
        });
        beverageFrame.setVisible(true);
    }
	// 紅茶
	public static void redtea() {
        JFrame beverageFrame = new JFrame("飲料設定");
        beverageFrame.setSize(300, 400);
        beverageFrame.setLayout(null);
        beverageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 關閉飲料設定視窗時不影響主視窗

        JPanel panel = new JPanel();
        beverageFrame.add(panel);
        
        JLabel ice, sugar, quantity;
        ice = new JLabel("冰塊");
        sugar = new JLabel("甜度");
        quantity = new JLabel("數量");

        JRadioButton noSugar = new JRadioButton("無糖");
        JRadioButton halfSugar = new JRadioButton("半糖");
        JRadioButton fullSugar = new JRadioButton("全糖");
        ButtonGroup sugarGroup = new ButtonGroup();
        sugarGroup.add(noSugar);
        sugarGroup.add(halfSugar);
        sugarGroup.add(fullSugar);
        
        JRadioButton noIce = new JRadioButton("無冰");
        JRadioButton littleIce = new JRadioButton("少冰");
        JRadioButton normalIce = new JRadioButton("正常冰");
        ButtonGroup iceGroup = new ButtonGroup();
        iceGroup.add(noIce);
        iceGroup.add(littleIce);
        iceGroup.add(normalIce);

        ice.setBounds(20, 50, 100, 30);
        noIce.setBounds(70, 50, 70, 30);
        littleIce.setBounds(140, 50, 70, 30);
        normalIce.setBounds(210, 50, 70, 30);
        
        sugar.setBounds(20, 110, 100, 30);
        noSugar.setBounds(70, 110, 70, 30);
        halfSugar.setBounds(140, 110, 70, 30);
        fullSugar.setBounds(210, 110, 70, 30);
        
        quantity.setBounds(20, 170, 100, 30);
        
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1); // 設定 JSpinner 的最小、最大值和初始值
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setBounds(110, 175, 60, 30);
        
        beverageFrame.add(ice);
        beverageFrame.add(noIce);
        beverageFrame.add(littleIce);
        beverageFrame.add(normalIce);
        beverageFrame.add(sugar);
        beverageFrame.add(noSugar);
        beverageFrame.add(halfSugar);
        beverageFrame.add(fullSugar);
        beverageFrame.add(quantity);
        beverageFrame.add(quantitySpinner);
        
        // 新增取消按鈕
        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(30, 240, 100, 30);
        beverageFrame.add(cancelButton);

        // 新增確定按鈕
        JButton confirmButton = new JButton("確定");
        confirmButton.setBounds(150, 240, 100, 30);
        beverageFrame.add(confirmButton);
        
        // 設定取消按鈕的操作
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	beverageFrame.dispose(); // 關閉視窗
            }
        });        
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 檢查甜度和冰塊的JRadioButton是否都未選擇
            	if (!noIce.isSelected() && !littleIce.isSelected() && !normalIce.isSelected()) {
                    JOptionPane.showMessageDialog(beverageFrame, "請選擇冰塊選項", "提示", JOptionPane.WARNING_MESSAGE);
                }
            	else if (!noSugar.isSelected() && !halfSugar.isSelected() && !fullSugar.isSelected()) {
                        JOptionPane.showMessageDialog(beverageFrame, "請選擇甜度選項", "提示", JOptionPane.WARNING_MESSAGE);
                    }
            	else {
                    // 如果有兩個選擇，執行確定的操作
                    // 這裡可以將所選的甜度和冰塊選項以及數量儲存到資料庫或進行其他操作
            		String selectedSugar = "";
                    if (noSugar.isSelected()) {
                        selectedSugar = "無糖";
                    } else if (halfSugar.isSelected()) {
                        selectedSugar = "半糖";
                    } else if (fullSugar.isSelected()) {
                        selectedSugar = "全糖";
                    }
                    
                    String selectedIce = "";
                    if (noIce.isSelected()) {
                        selectedIce = "無冰";
                    } else if (littleIce.isSelected()) {
                        selectedIce = "少冰";
                    } else if (normalIce.isSelected()) {
                        selectedIce = "正常冰";
                    }                   
                    int selectedQuantity = (int) quantitySpinner.getValue();                   
                    // 建立提示訊息字串
                    String message = "您選擇的項目:\n";
                    message += "紅茶\n";
                    message += "甜度: " + selectedSugar + "\n";
                    message += "冰塊: " + selectedIce + "\n";
                    message += "數量: " + selectedQuantity;
                    // 顯示提示訊息視窗
                    JOptionPane.showMessageDialog(beverageFrame, message, "訂單詳情", JOptionPane.INFORMATION_MESSAGE);
                    
                    beverageFrame.dispose();
                    
                    item[i] = "紅茶";
                    qty[i] = selectedQuantity;
                    cash[i] = 20*selectedQuantity;
                    items.add(item[i]);
                    qtys.add(qty[i]);
                    cashes.add(cash[i]);
                    i++;
                    cashcash += 20*selectedQuantity;
                }
            }
        });
        beverageFrame.setVisible(true);
    }
	// 奶茶
	public static void milktea() {
        JFrame beverageFrame = new JFrame("飲料設定");
        beverageFrame.setSize(300, 400);
        beverageFrame.setLayout(null);
        beverageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 關閉飲料設定視窗時不影響主視窗

        JPanel panel = new JPanel();
        beverageFrame.add(panel);
        
        JLabel ice, sugar, quantity;
        ice = new JLabel("冰塊");
        sugar = new JLabel("甜度");
        quantity = new JLabel("數量");

        JRadioButton noSugar = new JRadioButton("無糖");
        JRadioButton halfSugar = new JRadioButton("半糖");
        JRadioButton fullSugar = new JRadioButton("全糖");
        ButtonGroup sugarGroup = new ButtonGroup();
        sugarGroup.add(noSugar);
        sugarGroup.add(halfSugar);
        sugarGroup.add(fullSugar);
        
        JRadioButton noIce = new JRadioButton("無冰");
        JRadioButton littleIce = new JRadioButton("少冰");
        JRadioButton normalIce = new JRadioButton("正常冰");
        ButtonGroup iceGroup = new ButtonGroup();
        iceGroup.add(noIce);
        iceGroup.add(littleIce);
        iceGroup.add(normalIce);

        ice.setBounds(20, 50, 100, 30);
        noIce.setBounds(70, 50, 70, 30);
        littleIce.setBounds(140, 50, 70, 30);
        normalIce.setBounds(210, 50, 70, 30);
        
        sugar.setBounds(20, 110, 100, 30);
        noSugar.setBounds(70, 110, 70, 30);
        halfSugar.setBounds(140, 110, 70, 30);
        fullSugar.setBounds(210, 110, 70, 30);
        
        quantity.setBounds(20, 170, 100, 30);
        
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1); // 設定 JSpinner 的最小、最大值和初始值
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setBounds(110, 175, 60, 30);
        
        beverageFrame.add(ice);
        beverageFrame.add(noIce);
        beverageFrame.add(littleIce);
        beverageFrame.add(normalIce);
        beverageFrame.add(sugar);
        beverageFrame.add(noSugar);
        beverageFrame.add(halfSugar);
        beverageFrame.add(fullSugar);
        beverageFrame.add(quantity);
        beverageFrame.add(quantitySpinner);
        
        // 新增取消按鈕
        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(30, 240, 100, 30);
        beverageFrame.add(cancelButton);

        // 新增確定按鈕
        JButton confirmButton = new JButton("確定");
        confirmButton.setBounds(150, 240, 100, 30);
        beverageFrame.add(confirmButton);
        
        // 設定取消按鈕的操作
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	beverageFrame.dispose(); // 關閉視窗
            }
        });        
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 檢查甜度和冰塊的JRadioButton是否都未選擇
            	if (!noIce.isSelected() && !littleIce.isSelected() && !normalIce.isSelected()) {
                    JOptionPane.showMessageDialog(beverageFrame, "請選擇冰塊選項", "提示", JOptionPane.WARNING_MESSAGE);
                }
            	else if (!noSugar.isSelected() && !halfSugar.isSelected() && !fullSugar.isSelected()) {
                        JOptionPane.showMessageDialog(beverageFrame, "請選擇甜度選項", "提示", JOptionPane.WARNING_MESSAGE);
                    }
            	else {
                    // 如果有兩個選擇，執行確定的操作
                    // 這裡可以將所選的甜度和冰塊選項以及數量儲存到資料庫或進行其他操作
            		String selectedSugar = "";
                    if (noSugar.isSelected()) {
                        selectedSugar = "無糖";
                    } else if (halfSugar.isSelected()) {
                        selectedSugar = "半糖";
                    } else if (fullSugar.isSelected()) {
                        selectedSugar = "全糖";
                    }
                    
                    String selectedIce = "";
                    if (noIce.isSelected()) {
                        selectedIce = "無冰";
                    } else if (littleIce.isSelected()) {
                        selectedIce = "少冰";
                    } else if (normalIce.isSelected()) {
                        selectedIce = "正常冰";
                    }                   
                    int selectedQuantity = (int) quantitySpinner.getValue();                   
                    // 建立提示訊息字串
                    String message = "您選擇的項目:\n";
                    message += "奶茶\n";
                    message += "甜度: " + selectedSugar + "\n";
                    message += "冰塊: " + selectedIce + "\n";
                    message += "數量: " + selectedQuantity;
                    // 顯示提示訊息視窗
                    JOptionPane.showMessageDialog(beverageFrame, message, "訂單詳情", JOptionPane.INFORMATION_MESSAGE);
                    
                    beverageFrame.dispose();
                    
                    item[i] = "奶茶";
                    qty[i] = selectedQuantity;
                    cash[i] = 25*selectedQuantity;
                    items.add(item[i]);
                    qtys.add(qty[i]);
                    cashes.add(cash[i]);
                    i++;
                    cashcash += 25*selectedQuantity;
                }
            }
        });
        beverageFrame.setVisible(true);
    }
	// 豆漿
	public static void soymilk() {
		JFrame beverageFrame = new JFrame("飲料設定");
	    beverageFrame.setSize(300, 400);
	    beverageFrame.setLayout(null);
	    beverageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 關閉飲料設定視窗時不影響主視窗

	    JPanel panel = new JPanel();
	    beverageFrame.add(panel);
	        
	    JLabel ice, quantity;
	    ice = new JLabel("冰塊");
	    quantity = new JLabel("數量");
	        
	    JRadioButton noIce = new JRadioButton("無冰");
        JRadioButton littleIce = new JRadioButton("少冰");
        JRadioButton normalIce = new JRadioButton("正常冰");
        ButtonGroup iceGroup = new ButtonGroup();
        iceGroup.add(noIce);
        iceGroup.add(littleIce);
        iceGroup.add(normalIce);

        ice.setBounds(20, 50, 100, 30);
        noIce.setBounds(70, 50, 70, 30);
        littleIce.setBounds(140, 50, 70, 30);
        normalIce.setBounds(210, 50, 70, 30);
        
        quantity.setBounds(20, 110, 100, 30);
	        
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1); // 設定 JSpinner 的最小、最大值和初始值
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setBounds(110, 115, 60, 30);
	        
        beverageFrame.add(ice);
        beverageFrame.add(noIce);
        beverageFrame.add(littleIce);
        beverageFrame.add(normalIce);

        beverageFrame.add(quantity);
        beverageFrame.add(quantitySpinner);	        
        // 新增取消按鈕
        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(30, 180, 100, 30);
        beverageFrame.add(cancelButton);
        // 新增確定按鈕
        JButton confirmButton = new JButton("確定");
        confirmButton.setBounds(150, 180, 100, 30);
        beverageFrame.add(confirmButton);
	        
        // 設定取消按鈕的操作
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	beverageFrame.dispose(); // 關閉視窗
            }
        });        
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 檢查冰塊的JRadioButton是否都未選擇
            	if (!noIce.isSelected() && !littleIce.isSelected() && !normalIce.isSelected()) {
                    JOptionPane.showMessageDialog(beverageFrame, "請選擇冰塊選項", "提示", JOptionPane.WARNING_MESSAGE);
                }
            	else {	                    
                    String selectedIce = "";
                    if (noIce.isSelected()) {
                        selectedIce = "無冰";
                    } else if (littleIce.isSelected()) {
                        selectedIce = "少冰";
                    } else if (normalIce.isSelected()) {
                        selectedIce = "正常冰";
                    }                   
                    int selectedQuantity = (int) quantitySpinner.getValue();                   
                    // 建立提示訊息字串
                    String message = "您選擇的項目:\n";
                    message += "豆漿\n";
                    message += "冰塊: " + selectedIce + "\n";
                    message += "數量: " + selectedQuantity;
                    // 顯示提示訊息視窗
                    JOptionPane.showMessageDialog(beverageFrame, message, "訂單詳情", JOptionPane.INFORMATION_MESSAGE);
	                    
                    beverageFrame.dispose();
                    
                    item[i] = "豆漿";
                    qty[i] = selectedQuantity;
                    cash[i] = 25*selectedQuantity;
                    items.add(item[i]);
                    qtys.add(qty[i]);
                    cashes.add(cash[i]);
                    i++;
                    cashcash += 25*selectedQuantity;
                }
            }
        });
        beverageFrame.setVisible(true);
	    }
	// 鮮奶茶
	public static void milk() {
		JFrame beverageFrame = new JFrame("飲料設定");
	    beverageFrame.setSize(300, 400);
	    beverageFrame.setLayout(null);
	    beverageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 關閉飲料設定視窗時不影響主視窗

	    JPanel panel = new JPanel();
	    beverageFrame.add(panel);
	        
	    JLabel ice, quantity;
	    ice = new JLabel("冰塊");
	    quantity = new JLabel("數量");
	        
	    JRadioButton noIce = new JRadioButton("無冰");
        JRadioButton littleIce = new JRadioButton("少冰");
        JRadioButton normalIce = new JRadioButton("正常冰");
        ButtonGroup iceGroup = new ButtonGroup();
        iceGroup.add(noIce);
        iceGroup.add(littleIce);
        iceGroup.add(normalIce);

        ice.setBounds(20, 50, 100, 30);
        noIce.setBounds(70, 50, 70, 30);
        littleIce.setBounds(140, 50, 70, 30);
        normalIce.setBounds(210, 50, 70, 30);
        
        quantity.setBounds(20, 110, 100, 30);
	        
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1); // 設定 JSpinner 的最小、最大值和初始值
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setBounds(110, 115, 60, 30);
	        
        beverageFrame.add(ice);
        beverageFrame.add(noIce);
        beverageFrame.add(littleIce);
        beverageFrame.add(normalIce);

        beverageFrame.add(quantity);
        beverageFrame.add(quantitySpinner);	        
        // 新增取消按鈕
        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(30, 180, 100, 30);
        beverageFrame.add(cancelButton);
        // 新增確定按鈕
        JButton confirmButton = new JButton("確定");
        confirmButton.setBounds(150, 180, 100, 30);
        beverageFrame.add(confirmButton);
	        
        // 設定取消按鈕的操作
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	beverageFrame.dispose(); // 關閉視窗
            }
        });        
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 檢查冰塊的JRadioButton是否都未選擇
            	if (!noIce.isSelected() && !littleIce.isSelected() && !normalIce.isSelected()) {
                    JOptionPane.showMessageDialog(beverageFrame, "請選擇冰塊選項", "提示", JOptionPane.WARNING_MESSAGE);
                }
            	else {	                    
                    String selectedIce = "";
                    if (noIce.isSelected()) {
                        selectedIce = "無冰";
                    } else if (littleIce.isSelected()) {
                        selectedIce = "少冰";
                    } else if (normalIce.isSelected()) {
                        selectedIce = "正常冰";
                    }                   
                    int selectedQuantity = (int) quantitySpinner.getValue();                   
                    // 建立提示訊息字串
                    String message = "您選擇的項目:\n";
                    message += "鮮奶茶\n";
                    message += "冰塊: " + selectedIce + "\n";
                    message += "數量: " + selectedQuantity;
                    // 顯示提示訊息視窗
                    JOptionPane.showMessageDialog(beverageFrame, message, "訂單詳情", JOptionPane.INFORMATION_MESSAGE);
	                    
                    beverageFrame.dispose();
                    
                    item[i] = "鮮奶茶";
                    qty[i] = selectedQuantity;
                    cash[i] = 35*selectedQuantity;
                    items.add(item[i]);
                    qtys.add(qty[i]);
                    cashes.add(cash[i]);
                    i++;
                    cashcash += 35*selectedQuantity;
                }
            }
        });
        beverageFrame.setVisible(true);
	    }
	// 可可牛奶
	public static void cocomilk() {
		JFrame beverageFrame = new JFrame("飲料設定");
	    beverageFrame.setSize(300, 400);
	    beverageFrame.setLayout(null);
	    beverageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 關閉飲料設定視窗時不影響主視窗

	    JPanel panel = new JPanel();
	    beverageFrame.add(panel);
	        
	    JLabel ice, quantity;
	    ice = new JLabel("冰塊");
	    quantity = new JLabel("數量");
	        
	    JRadioButton noIce = new JRadioButton("無冰");
        JRadioButton littleIce = new JRadioButton("少冰");
        JRadioButton normalIce = new JRadioButton("正常冰");
        ButtonGroup iceGroup = new ButtonGroup();
        iceGroup.add(noIce);
        iceGroup.add(littleIce);
        iceGroup.add(normalIce);

        ice.setBounds(20, 50, 100, 30);
        noIce.setBounds(70, 50, 70, 30);
        littleIce.setBounds(140, 50, 70, 30);
        normalIce.setBounds(210, 50, 70, 30);
        
        quantity.setBounds(20, 110, 100, 30);
	        
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1); // 設定 JSpinner 的最小、最大值和初始值
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setBounds(110, 115, 60, 30);
	        
        beverageFrame.add(ice);
        beverageFrame.add(noIce);
        beverageFrame.add(littleIce);
        beverageFrame.add(normalIce);

        beverageFrame.add(quantity);
        beverageFrame.add(quantitySpinner);	        
        // 新增取消按鈕
        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(30, 180, 100, 30);
        beverageFrame.add(cancelButton);
        // 新增確定按鈕
        JButton confirmButton = new JButton("確定");
        confirmButton.setBounds(150, 180, 100, 30);
        beverageFrame.add(confirmButton);
	        
        // 設定取消按鈕的操作
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	beverageFrame.dispose(); // 關閉視窗
            }
        });        
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 檢查冰塊的JRadioButton是否都未選擇
            	if (!noIce.isSelected() && !littleIce.isSelected() && !normalIce.isSelected()) {
                    JOptionPane.showMessageDialog(beverageFrame, "請選擇冰塊選項", "提示", JOptionPane.WARNING_MESSAGE);
                }
            	else {	                    
                    String selectedIce = "";
                    if (noIce.isSelected()) {
                        selectedIce = "無冰";
                    } else if (littleIce.isSelected()) {
                        selectedIce = "少冰";
                    } else if (normalIce.isSelected()) {
                        selectedIce = "正常冰";
                    }                   
                    int selectedQuantity = (int) quantitySpinner.getValue();                   
                    // 建立提示訊息字串
                    String message = "您選擇的項目:\n";
                    message += "可可牛奶\n";
                    message += "冰塊: " + selectedIce + "\n";
                    message += "數量: " + selectedQuantity;
                    // 顯示提示訊息視窗
                    JOptionPane.showMessageDialog(beverageFrame, message, "訂單詳情", JOptionPane.INFORMATION_MESSAGE);
	                    
                    beverageFrame.dispose();
                    
                    item[i] = "可可牛奶";
                    qty[i] = selectedQuantity;
                    cash[i] = 35*selectedQuantity;
                    items.add(item[i]);
                    qtys.add(qty[i]);
                    cashes.add(cash[i]);
                    i++;
                    cashcash += 35*selectedQuantity;
                }
            }
        });
        beverageFrame.setVisible(true);
	    }
	// 薰衣草
	public static void lavender() {
		JFrame beverageFrame = new JFrame("飲料設定");
	    beverageFrame.setSize(300, 400);
	    beverageFrame.setLayout(null);
	    beverageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 關閉飲料設定視窗時不影響主視窗

	    JPanel panel = new JPanel();
	    beverageFrame.add(panel);
	        
	    JLabel ice, quantity;
	    ice = new JLabel("冰塊");
	    quantity = new JLabel("數量");
	        
	    JRadioButton noIce = new JRadioButton("無冰");
        JRadioButton littleIce = new JRadioButton("少冰");
        JRadioButton normalIce = new JRadioButton("正常冰");
        ButtonGroup iceGroup = new ButtonGroup();
        iceGroup.add(noIce);
        iceGroup.add(littleIce);
        iceGroup.add(normalIce);

        ice.setBounds(20, 50, 100, 30);
        noIce.setBounds(70, 50, 70, 30);
        littleIce.setBounds(140, 50, 70, 30);
        normalIce.setBounds(210, 50, 70, 30);
        
        quantity.setBounds(20, 110, 100, 30);
	        
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1); // 設定 JSpinner 的最小、最大值和初始值
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setBounds(110, 115, 60, 30);
	        
        beverageFrame.add(ice);
        beverageFrame.add(noIce);
        beverageFrame.add(littleIce);
        beverageFrame.add(normalIce);

        beverageFrame.add(quantity);
        beverageFrame.add(quantitySpinner);	        
        // 新增取消按鈕
        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(30, 180, 100, 30);
        beverageFrame.add(cancelButton);
        // 新增確定按鈕
        JButton confirmButton = new JButton("確定");
        confirmButton.setBounds(150, 180, 100, 30);
        beverageFrame.add(confirmButton);
	        
        // 設定取消按鈕的操作
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	beverageFrame.dispose(); // 關閉視窗
            }
        });        
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 檢查冰塊的JRadioButton是否都未選擇
            	if (!noIce.isSelected() && !littleIce.isSelected() && !normalIce.isSelected()) {
                    JOptionPane.showMessageDialog(beverageFrame, "請選擇冰塊選項", "提示", JOptionPane.WARNING_MESSAGE);
                }
            	else {	                    
                    String selectedIce = "";
                    if (noIce.isSelected()) {
                        selectedIce = "無冰";
                    } else if (littleIce.isSelected()) {
                        selectedIce = "少冰";
                    } else if (normalIce.isSelected()) {
                        selectedIce = "正常冰";
                    }                   
                    int selectedQuantity = (int) quantitySpinner.getValue();                   
                    // 建立提示訊息字串
                    String message = "您選擇的項目:\n";
                    message += "薰衣草奶茶\n";
                    message += "冰塊: " + selectedIce + "\n";
                    message += "數量: " + selectedQuantity;
                    // 顯示提示訊息視窗
                    JOptionPane.showMessageDialog(beverageFrame, message, "訂單詳情", JOptionPane.INFORMATION_MESSAGE);
	                    
                    beverageFrame.dispose();
                    
                    item[i] = "薰衣草奶茶";
                    qty[i] = selectedQuantity;
                    cash[i] = 40*selectedQuantity;
                    items.add(item[i]);
                    qtys.add(qty[i]);
                    cashes.add(cash[i]);
                    i++;
                    cashcash += 40*selectedQuantity;
                }
            }
        });
        beverageFrame.setVisible(true);
	    }
	// 山藥薏仁
	public static void barley() {
		JFrame beverageFrame = new JFrame("飲料設定");
	    beverageFrame.setSize(300, 400);
	    beverageFrame.setLayout(null);
	    beverageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 關閉飲料設定視窗時不影響主視窗

	    JPanel panel = new JPanel();
	    beverageFrame.add(panel);
	        
	    JLabel ice, quantity;
	    ice = new JLabel("冰塊");
	    quantity = new JLabel("數量");
	        
	    JRadioButton noIce = new JRadioButton("無冰");
        JRadioButton littleIce = new JRadioButton("少冰");
        JRadioButton normalIce = new JRadioButton("正常冰");
        ButtonGroup iceGroup = new ButtonGroup();
        iceGroup.add(noIce);
        iceGroup.add(littleIce);
        iceGroup.add(normalIce);

        ice.setBounds(20, 50, 100, 30);
        noIce.setBounds(70, 50, 70, 30);
        littleIce.setBounds(140, 50, 70, 30);
        normalIce.setBounds(210, 50, 70, 30);
        
        quantity.setBounds(20, 110, 100, 30);
	        
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1); // 設定 JSpinner 的最小、最大值和初始值
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setBounds(110, 115, 60, 30);
	        
        beverageFrame.add(ice);
        beverageFrame.add(noIce);
        beverageFrame.add(littleIce);
        beverageFrame.add(normalIce);

        beverageFrame.add(quantity);
        beverageFrame.add(quantitySpinner);	        
        // 新增取消按鈕
        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(30, 180, 100, 30);
        beverageFrame.add(cancelButton);
        // 新增確定按鈕
        JButton confirmButton = new JButton("確定");
        confirmButton.setBounds(150, 180, 100, 30);
        beverageFrame.add(confirmButton);
	        
        // 設定取消按鈕的操作
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	beverageFrame.dispose(); // 關閉視窗
            }
        });        
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 檢查冰塊的JRadioButton是否都未選擇
            	if (!noIce.isSelected() && !littleIce.isSelected() && !normalIce.isSelected()) {
                    JOptionPane.showMessageDialog(beverageFrame, "請選擇冰塊選項", "提示", JOptionPane.WARNING_MESSAGE);
                }
            	else {	                    
                    String selectedIce = "";
                    if (noIce.isSelected()) {
                        selectedIce = "無冰";
                    } else if (littleIce.isSelected()) {
                        selectedIce = "少冰";
                    } else if (normalIce.isSelected()) {
                        selectedIce = "正常冰";
                    }                   
                    int selectedQuantity = (int) quantitySpinner.getValue();                   
                    // 建立提示訊息字串
                    String message = "您選擇的項目:\n";
                    message += "山藥薏仁\n";
                    message += "冰塊: " + selectedIce + "\n";
                    message += "數量: " + selectedQuantity;
                    // 顯示提示訊息視窗
                    JOptionPane.showMessageDialog(beverageFrame, message, "訂單詳情", JOptionPane.INFORMATION_MESSAGE);
	                    
                    beverageFrame.dispose();
                    
                    item[i] = "山藥薏仁";
                    qty[i] = selectedQuantity;
                    cash[i] = 40*selectedQuantity;
                    items.add(item[i]);
                    qtys.add(qty[i]);
                    cashes.add(cash[i]);
                    i++;
                    cashcash += 40*selectedQuantity;
                }
            }
        });
        beverageFrame.setVisible(true);
	    }
	// 柳橙汁
	public static void orange() {
		JFrame beverageFrame = new JFrame("飲料設定");
	    beverageFrame.setSize(300, 400);
	    beverageFrame.setLayout(null);
	    beverageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 關閉飲料設定視窗時不影響主視窗

	    JPanel panel = new JPanel();
	    beverageFrame.add(panel);
	        
	    JLabel ice, quantity;
	    ice = new JLabel("冰塊");
	    quantity = new JLabel("數量");
	        
	    JRadioButton noIce = new JRadioButton("無冰");
        JRadioButton littleIce = new JRadioButton("少冰");
        JRadioButton normalIce = new JRadioButton("正常冰");
        ButtonGroup iceGroup = new ButtonGroup();
        iceGroup.add(noIce);
        iceGroup.add(littleIce);
        iceGroup.add(normalIce);

        ice.setBounds(20, 50, 100, 30);
        noIce.setBounds(70, 50, 70, 30);
        littleIce.setBounds(140, 50, 70, 30);
        normalIce.setBounds(210, 50, 70, 30);
        
        quantity.setBounds(20, 110, 100, 30);
	        
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1); // 設定 JSpinner 的最小、最大值和初始值
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setBounds(110, 115, 60, 30);
	        
        beverageFrame.add(ice);
        beverageFrame.add(noIce);
        beverageFrame.add(littleIce);
        beverageFrame.add(normalIce);

        beverageFrame.add(quantity);
        beverageFrame.add(quantitySpinner);	        
        // 新增取消按鈕
        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(30, 180, 100, 30);
        beverageFrame.add(cancelButton);
        // 新增確定按鈕
        JButton confirmButton = new JButton("確定");
        confirmButton.setBounds(150, 180, 100, 30);
        beverageFrame.add(confirmButton);
	        
        // 設定取消按鈕的操作
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	beverageFrame.dispose(); // 關閉視窗
            }
        });        
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 檢查冰塊的JRadioButton是否都未選擇
            	if (!noIce.isSelected() && !littleIce.isSelected() && !normalIce.isSelected()) {
                    JOptionPane.showMessageDialog(beverageFrame, "請選擇冰塊選項", "提示", JOptionPane.WARNING_MESSAGE);
                }
            	else {	                    
                    String selectedIce = "";
                    if (noIce.isSelected()) {
                        selectedIce = "無冰";
                    } else if (littleIce.isSelected()) {
                        selectedIce = "少冰";
                    } else if (normalIce.isSelected()) {
                        selectedIce = "正常冰";
                    }                   
                    int selectedQuantity = (int) quantitySpinner.getValue();                   
                    // 建立提示訊息字串
                    String message = "您選擇的項目:\n";
                    message += "柳橙汁\n";
                    message += "冰塊: " + selectedIce + "\n";
                    message += "數量: " + selectedQuantity;
                    // 顯示提示訊息視窗
                    JOptionPane.showMessageDialog(beverageFrame, message, "訂單詳情", JOptionPane.INFORMATION_MESSAGE);
	                    
                    beverageFrame.dispose();
                    
                    item[i] = "柳橙汁";
                    qty[i] = selectedQuantity;
                    cash[i] = 30*selectedQuantity;
                    items.add(item[i]);
                    qtys.add(qty[i]);
                    cashes.add(cash[i]);
                    i++;
                    cashcash += 30*selectedQuantity;
                }
            }
        });
        beverageFrame.setVisible(true);
	    }
	// 可樂
	public static void cola() {
		JFrame beverageFrame = new JFrame("飲料設定");
	    beverageFrame.setSize(300, 400);
	    beverageFrame.setLayout(null);
	    beverageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 關閉飲料設定視窗時不影響主視窗

	    JPanel panel = new JPanel();
	    beverageFrame.add(panel);
	        
	    JLabel ice, quantity;
	    ice = new JLabel("冰塊");
	    quantity = new JLabel("數量");
	        
	    JRadioButton noIce = new JRadioButton("無冰");
        JRadioButton littleIce = new JRadioButton("少冰");
        JRadioButton normalIce = new JRadioButton("正常冰");
        ButtonGroup iceGroup = new ButtonGroup();
        iceGroup.add(noIce);
        iceGroup.add(littleIce);
        iceGroup.add(normalIce);

        ice.setBounds(20, 50, 100, 30);
        noIce.setBounds(70, 50, 70, 30);
        littleIce.setBounds(140, 50, 70, 30);
        normalIce.setBounds(210, 50, 70, 30);
        
        quantity.setBounds(20, 110, 100, 30);
	        
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1); // 設定 JSpinner 的最小、最大值和初始值
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setBounds(110, 115, 60, 30);
	        
        beverageFrame.add(ice);
        beverageFrame.add(noIce);
        beverageFrame.add(littleIce);
        beverageFrame.add(normalIce);

        beverageFrame.add(quantity);
        beverageFrame.add(quantitySpinner);	        
        // 新增取消按鈕
        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(30, 180, 100, 30);
        beverageFrame.add(cancelButton);
        // 新增確定按鈕
        JButton confirmButton = new JButton("確定");
        confirmButton.setBounds(150, 180, 100, 30);
        beverageFrame.add(confirmButton);
	        
        // 設定取消按鈕的操作
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	beverageFrame.dispose(); // 關閉視窗
            }
        });        
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 檢查冰塊的JRadioButton是否都未選擇
            	if (!noIce.isSelected() && !littleIce.isSelected() && !normalIce.isSelected()) {
                    JOptionPane.showMessageDialog(beverageFrame, "請選擇冰塊選項", "提示", JOptionPane.WARNING_MESSAGE);
                }
            	else {	                    
                    String selectedIce = "";
                    if (noIce.isSelected()) {
                        selectedIce = "無冰";
                    } else if (littleIce.isSelected()) {
                        selectedIce = "少冰";
                    } else if (normalIce.isSelected()) {
                        selectedIce = "正常冰";
                    }                   
                    int selectedQuantity = (int) quantitySpinner.getValue();                   
                    // 建立提示訊息字串
                    String message = "您選擇的項目:\n";
                    message += "可樂\n";
                    message += "冰塊: " + selectedIce + "\n";
                    message += "數量: " + selectedQuantity;
                    // 顯示提示訊息視窗
                    JOptionPane.showMessageDialog(beverageFrame, message, "訂單詳情", JOptionPane.INFORMATION_MESSAGE);
	                    
                    beverageFrame.dispose();
                    
                    item[i] = "可樂";
                    qty[i] = selectedQuantity;
                    cash[i] = 25*selectedQuantity;
                    items.add(item[i]);
                    qtys.add(qty[i]);
                    cashes.add(cash[i]);
                    i++;
                    cashcash += 25*selectedQuantity;
                }
            }
        });
        beverageFrame.setVisible(true);
	    }
	// 蛋餅
	public static void eggcookie() {
        JFrame beverageFrame = new JFrame("數量設定");
        beverageFrame.setSize(300, 400);
        beverageFrame.setLayout(null);
        beverageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 關閉飲料設定視窗時不影響主視窗

        JPanel panel = new JPanel();
        beverageFrame.add(panel);
	        
        JLabel quantity;
        quantity = new JLabel("數量");
        quantity.setBounds(20, 50, 100, 30);
	        
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1); // 設定 JSpinner 的最小、最大值和初始值
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setBounds(110, 55, 60, 30);

        beverageFrame.add(quantity);
        beverageFrame.add(quantitySpinner);
	        
        // 新增取消按鈕
        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(30, 120, 100, 30);
        beverageFrame.add(cancelButton);

        // 新增確定按鈕
        JButton confirmButton = new JButton("確定");
        confirmButton.setBounds(150, 120, 100, 30);
        beverageFrame.add(confirmButton);
	        
        // 設定取消按鈕的操作
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	beverageFrame.dispose(); // 關閉視窗
            }
        });        
	    confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             
                   	int selectedQuantity = (int) quantitySpinner.getValue();                   
                    // 建立提示訊息字串
                    String message = "您選擇的項目:\n";
                    message += "蛋餅\n";
                    message += "數量: " + selectedQuantity;
                    // 顯示提示訊息視窗
                    JOptionPane.showMessageDialog(beverageFrame, message, "訂單詳情", JOptionPane.INFORMATION_MESSAGE);
                    
                    beverageFrame.dispose();
                    
                    item[i] = "蛋餅";
                    qty[i] = selectedQuantity;
                    cash[i] = 25*selectedQuantity;
                    items.add(item[i]);
                    qtys.add(qty[i]);
                    cashes.add(cash[i]);
                    i++;
                    cashcash += 25*selectedQuantity;
              	}
        });
        beverageFrame.setVisible(true);
    }	
	// 蔥抓
	public static void onioncookie() {
        JFrame beverageFrame = new JFrame("數量設定");
        beverageFrame.setSize(300, 400);
        beverageFrame.setLayout(null);
        beverageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 關閉飲料設定視窗時不影響主視窗

        JPanel panel = new JPanel();
        beverageFrame.add(panel);
	        
        JLabel quantity;
        quantity = new JLabel("數量");
        quantity.setBounds(20, 50, 100, 30);
	        
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1); // 設定 JSpinner 的最小、最大值和初始值
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setBounds(110, 55, 60, 30);

        beverageFrame.add(quantity);
        beverageFrame.add(quantitySpinner);
	        
        // 新增取消按鈕
        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(30, 120, 100, 30);
        beverageFrame.add(cancelButton);

        // 新增確定按鈕
        JButton confirmButton = new JButton("確定");
        confirmButton.setBounds(150, 120, 100, 30);
        beverageFrame.add(confirmButton);
	        
        // 設定取消按鈕的操作
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	beverageFrame.dispose(); // 關閉視窗
            }
        });        
	    confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             
                   	int selectedQuantity = (int) quantitySpinner.getValue();                   
                    // 建立提示訊息字串
                    String message = "您選擇的項目:\n";
                    message += "蒽抓餅\n";
                    message += "數量: " + selectedQuantity;
                    // 顯示提示訊息視窗
                    JOptionPane.showMessageDialog(beverageFrame, message, "訂單詳情", JOptionPane.INFORMATION_MESSAGE);
                    
                    beverageFrame.dispose();
                    
                    item[i] = "蔥抓餅";
                    qty[i] = selectedQuantity;
                    cash[i] = 30*selectedQuantity;
                    items.add(item[i]);
                    qtys.add(qty[i]);
                    cashes.add(cash[i]);
                    i++;
                    cashcash += 30*selectedQuantity;
              	}
        });
        beverageFrame.setVisible(true);
    }
	// 蒸餃
	public static void dumpling() {
        JFrame beverageFrame = new JFrame("數量設定");
        beverageFrame.setSize(300, 400);
        beverageFrame.setLayout(null);
        beverageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 關閉飲料設定視窗時不影響主視窗

        JPanel panel = new JPanel();
        beverageFrame.add(panel);
	        
        JLabel quantity;
        quantity = new JLabel("數量");
        quantity.setBounds(20, 50, 100, 30);
	        
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1); // 設定 JSpinner 的最小、最大值和初始值
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setBounds(110, 55, 60, 30);

        beverageFrame.add(quantity);
        beverageFrame.add(quantitySpinner);
	        
        // 新增取消按鈕
        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(30, 120, 100, 30);
        beverageFrame.add(cancelButton);

        // 新增確定按鈕
        JButton confirmButton = new JButton("確定");
        confirmButton.setBounds(150, 120, 100, 30);
        beverageFrame.add(confirmButton);
	        
        // 設定取消按鈕的操作
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	beverageFrame.dispose(); // 關閉視窗
            }
        });        
	    confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             
                   	int selectedQuantity = (int) quantitySpinner.getValue();                   
                    // 建立提示訊息字串
                    String message = "您選擇的項目:\n";
                    message += "蒸餃\n";
                    message += "數量: " + selectedQuantity;
                    // 顯示提示訊息視窗
                    JOptionPane.showMessageDialog(beverageFrame, message, "訂單詳情", JOptionPane.INFORMATION_MESSAGE);
                    
                    beverageFrame.dispose();
                    
                    item[i] = "蒸餃";
                    qty[i] = selectedQuantity;
                    cash[i] = 40*selectedQuantity;
                    items.add(item[i]);
                    qtys.add(qty[i]);
                    cashes.add(cash[i]);
                    i++;
                    cashcash += 40*selectedQuantity;
              	}
        });
        beverageFrame.setVisible(true);
    }
	// 籠包
	public static void bun() {
        JFrame beverageFrame = new JFrame("數量設定");
        beverageFrame.setSize(300, 400);
        beverageFrame.setLayout(null);
        beverageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 關閉飲料設定視窗時不影響主視窗

        JPanel panel = new JPanel();
        beverageFrame.add(panel);
	        
        JLabel quantity;
        quantity = new JLabel("數量");
        quantity.setBounds(20, 50, 100, 30);
	        
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1); // 設定 JSpinner 的最小、最大值和初始值
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setBounds(110, 55, 60, 30);

        beverageFrame.add(quantity);
        beverageFrame.add(quantitySpinner);
	        
        // 新增取消按鈕
        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(30, 120, 100, 30);
        beverageFrame.add(cancelButton);

        // 新增確定按鈕
        JButton confirmButton = new JButton("確定");
        confirmButton.setBounds(150, 120, 100, 30);
        beverageFrame.add(confirmButton);
	        
        // 設定取消按鈕的操作
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	beverageFrame.dispose(); // 關閉視窗
            }
        });        
	    confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             
                   	int selectedQuantity = (int) quantitySpinner.getValue();                   
                    // 建立提示訊息字串
                    String message = "您選擇的項目:\n";
                    message += "小籠包\n";
                    message += "數量: " + selectedQuantity;
                    // 顯示提示訊息視窗
                    JOptionPane.showMessageDialog(beverageFrame, message, "訂單詳情", JOptionPane.INFORMATION_MESSAGE);
                    
                    beverageFrame.dispose();
                    
                    item[i] = "小籠包";
                    qty[i] = selectedQuantity;
                    cash[i] = 60*selectedQuantity;
                    items.add(item[i]);
                    qtys.add(qty[i]);
                    cashes.add(cash[i]);
                    i++;
                    cashcash += 60*selectedQuantity;
              	}
        });
        beverageFrame.setVisible(true);
    }
	// 食光粥
	public static void porridge() {
        JFrame beverageFrame = new JFrame("數量設定");
        beverageFrame.setSize(300, 400);
        beverageFrame.setLayout(null);
        beverageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 關閉飲料設定視窗時不影響主視窗

        JPanel panel = new JPanel();
        beverageFrame.add(panel);
	        
        JLabel quantity;
        quantity = new JLabel("數量");
        quantity.setBounds(20, 50, 100, 30);
	        
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1); // 設定 JSpinner 的最小、最大值和初始值
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setBounds(110, 55, 60, 30);

        beverageFrame.add(quantity);
        beverageFrame.add(quantitySpinner);
	        
        // 新增取消按鈕
        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(30, 120, 100, 30);
        beverageFrame.add(cancelButton);

        // 新增確定按鈕
        JButton confirmButton = new JButton("確定");
        confirmButton.setBounds(150, 120, 100, 30);
        beverageFrame.add(confirmButton);
	        
        // 設定取消按鈕的操作
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	beverageFrame.dispose(); // 關閉視窗
            }
        });        
	    confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             
                   	int selectedQuantity = (int) quantitySpinner.getValue();                   
                    // 建立提示訊息字串
                    String message = "您選擇的項目:\n";
                    message += "食光粥\n";
                    message += "數量: " + selectedQuantity;
                    // 顯示提示訊息視窗
                    JOptionPane.showMessageDialog(beverageFrame, message, "訂單詳情", JOptionPane.INFORMATION_MESSAGE);
                    
                    beverageFrame.dispose();
                    
                    item[i] = "食光粥";
                    qty[i] = selectedQuantity;
                    cash[i] = 50*selectedQuantity;
                    items.add(item[i]);
                    qtys.add(qty[i]);
                    cashes.add(cash[i]);
                    i++;
                    cashcash += 50*selectedQuantity;
              	}
        });
        beverageFrame.setVisible(true);
    }
	// 蘿蔔
	public static void radish() {
        JFrame beverageFrame = new JFrame("數量設定");
        beverageFrame.setSize(300, 400);
        beverageFrame.setLayout(null);
        beverageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 關閉飲料設定視窗時不影響主視窗

        JPanel panel = new JPanel();
        beverageFrame.add(panel);
	        
        JLabel quantity;
        quantity = new JLabel("數量");
        quantity.setBounds(20, 50, 100, 30);
	        
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1); // 設定 JSpinner 的最小、最大值和初始值
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setBounds(110, 55, 60, 30);

        beverageFrame.add(quantity);
        beverageFrame.add(quantitySpinner);
	        
        // 新增取消按鈕
        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(30, 120, 100, 30);
        beverageFrame.add(cancelButton);

        // 新增確定按鈕
        JButton confirmButton = new JButton("確定");
        confirmButton.setBounds(150, 120, 100, 30);
        beverageFrame.add(confirmButton);
	        
        // 設定取消按鈕的操作
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	beverageFrame.dispose(); // 關閉視窗
            }
        });        
	    confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             
                   	int selectedQuantity = (int) quantitySpinner.getValue();                   
                    // 建立提示訊息字串
                    String message = "您選擇的項目:\n";
                    message += "蘿蔔糕\n";
                    message += "數量: " + selectedQuantity;
                    // 顯示提示訊息視窗
                    JOptionPane.showMessageDialog(beverageFrame, message, "訂單詳情", JOptionPane.INFORMATION_MESSAGE);
                    
                    beverageFrame.dispose();
                    
                    item[i] = "蘿蔔糕";
                    qty[i] = selectedQuantity;
                    cash[i] = 30*selectedQuantity;
                    items.add(item[i]);
                    qtys.add(qty[i]);
                    cashes.add(cash[i]);
                    i++;
                    cashcash += 30*selectedQuantity;
              	}
        });
        beverageFrame.setVisible(true);
    }
	// 米漢堡
	public static void ricehumber() {
        JFrame beverageFrame = new JFrame("數量設定");
        beverageFrame.setSize(300, 400);
        beverageFrame.setLayout(null);
        beverageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 關閉飲料設定視窗時不影響主視窗

        JPanel panel = new JPanel();
        beverageFrame.add(panel);
	        
        JLabel quantity;
        quantity = new JLabel("數量");
        quantity.setBounds(20, 50, 100, 30);
	        
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1); // 設定 JSpinner 的最小、最大值和初始值
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setBounds(110, 55, 60, 30);

        beverageFrame.add(quantity);
        beverageFrame.add(quantitySpinner);
	        
        // 新增取消按鈕
        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(30, 120, 100, 30);
        beverageFrame.add(cancelButton);

        // 新增確定按鈕
        JButton confirmButton = new JButton("確定");
        confirmButton.setBounds(150, 120, 100, 30);
        beverageFrame.add(confirmButton);
	        
        // 設定取消按鈕的操作
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	beverageFrame.dispose(); // 關閉視窗
            }
        });        
	    confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             
                   	int selectedQuantity = (int) quantitySpinner.getValue();                   
                    // 建立提示訊息字串
                    String message = "您選擇的項目:\n";
                    message += "米漢堡\n";
                    message += "數量: " + selectedQuantity;
                    // 顯示提示訊息視窗
                    JOptionPane.showMessageDialog(beverageFrame, message, "訂單詳情", JOptionPane.INFORMATION_MESSAGE);
                    
                    beverageFrame.dispose();
                    
                    item[i] = "米漢堡";
                    qty[i] = selectedQuantity;
                    cash[i] = 50*selectedQuantity;
                    items.add(item[i]);
                    qtys.add(qty[i]);
                    cashes.add(cash[i]);
                    i++;
                    cashcash += 50*selectedQuantity;
              	}
        });
        beverageFrame.setVisible(true);
    }
	// 起司球
	public static void cheeseball() {
        JFrame beverageFrame = new JFrame("數量設定");
        beverageFrame.setSize(300, 400);
        beverageFrame.setLayout(null);
        beverageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 關閉飲料設定視窗時不影響主視窗

        JPanel panel = new JPanel();
        beverageFrame.add(panel);
	        
        JLabel quantity;
        quantity = new JLabel("數量");
        quantity.setBounds(20, 50, 100, 30);
	        
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1); // 設定 JSpinner 的最小、最大值和初始值
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setBounds(110, 55, 60, 30);

        beverageFrame.add(quantity);
        beverageFrame.add(quantitySpinner);
	        
        // 新增取消按鈕
        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(30, 120, 100, 30);
        beverageFrame.add(cancelButton);

        // 新增確定按鈕
        JButton confirmButton = new JButton("確定");
        confirmButton.setBounds(150, 120, 100, 30);
        beverageFrame.add(confirmButton);
	        
        // 設定取消按鈕的操作
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	beverageFrame.dispose(); // 關閉視窗
            }
        });        
	    confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             
                   	int selectedQuantity = (int) quantitySpinner.getValue();                   
                    // 建立提示訊息字串
                    String message = "您選擇的項目:\n";
                    message += "起司球\n";
                    message += "數量: " + selectedQuantity;
                    // 顯示提示訊息視窗
                    JOptionPane.showMessageDialog(beverageFrame, message, "訂單詳情", JOptionPane.INFORMATION_MESSAGE);
                    
                    beverageFrame.dispose();
                    
                    item[i] = "起司球";
                    qty[i] = selectedQuantity;
                    cash[i] = 30*selectedQuantity;
                    items.add(item[i]);
                    qtys.add(qty[i]);
                    cashes.add(cash[i]);
                    i++;
                    cashcash += 30*selectedQuantity;
              	}
        });
        beverageFrame.setVisible(true);
    }
	// 蛋
	public static void egg() {
        JFrame beverageFrame = new JFrame("數量設定");
        beverageFrame.setSize(300, 400);
        beverageFrame.setLayout(null);
        beverageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 關閉飲料設定視窗時不影響主視窗

        JPanel panel = new JPanel();
        beverageFrame.add(panel);
	        
        JLabel quantity;
        quantity = new JLabel("數量");
        quantity.setBounds(20, 50, 100, 30);
	        
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1); // 設定 JSpinner 的最小、最大值和初始值
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setBounds(110, 55, 60, 30);

        beverageFrame.add(quantity);
        beverageFrame.add(quantitySpinner);
	        
        // 新增取消按鈕
        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(30, 120, 100, 30);
        beverageFrame.add(cancelButton);

        // 新增確定按鈕
        JButton confirmButton = new JButton("確定");
        confirmButton.setBounds(150, 120, 100, 30);
        beverageFrame.add(confirmButton);
	        
        // 設定取消按鈕的操作
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	beverageFrame.dispose(); // 關閉視窗
            }
        });        
	    confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             
                   	int selectedQuantity = (int) quantitySpinner.getValue();                   
                    // 建立提示訊息字串
                    String message = "您選擇的項目:\n";
                    message += "荷包蛋\n";
                    message += "數量: " + selectedQuantity;
                    // 顯示提示訊息視窗
                    JOptionPane.showMessageDialog(beverageFrame, message, "訂單詳情", JOptionPane.INFORMATION_MESSAGE);
                    
                    beverageFrame.dispose();
                    
                    item[i] = "荷包蛋";
                    qty[i] = selectedQuantity;
                    cash[i] = 15*selectedQuantity;
                    items.add(item[i]);
                    qtys.add(qty[i]);
                    cashes.add(cash[i]);
                    i++;
                    cashcash += 15*selectedQuantity;
              	}
        });
        beverageFrame.setVisible(true);
    }
	// 薯條
	public static void fried() {
        JFrame beverageFrame = new JFrame("數量設定");
        beverageFrame.setSize(300, 400);
        beverageFrame.setLayout(null);
        beverageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 關閉飲料設定視窗時不影響主視窗

        JPanel panel = new JPanel();
        beverageFrame.add(panel);
	        
        JLabel quantity;
        quantity = new JLabel("數量");
        quantity.setBounds(20, 50, 100, 30);
	        
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1); // 設定 JSpinner 的最小、最大值和初始值
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setBounds(110, 55, 60, 30);

        beverageFrame.add(quantity);
        beverageFrame.add(quantitySpinner);
	        
        // 新增取消按鈕
        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(30, 120, 100, 30);
        beverageFrame.add(cancelButton);

        // 新增確定按鈕
        JButton confirmButton = new JButton("確定");
        confirmButton.setBounds(150, 120, 100, 30);
        beverageFrame.add(confirmButton);
	        
        // 設定取消按鈕的操作
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	beverageFrame.dispose(); // 關閉視窗
            }
        });        
	    confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             
                   	int selectedQuantity = (int) quantitySpinner.getValue();                   
                    // 建立提示訊息字串
                    String message = "您選擇的項目:\n";
                    message += "薯條\n";
                    message += "數量: " + selectedQuantity;
                    // 顯示提示訊息視窗
                    JOptionPane.showMessageDialog(beverageFrame, message, "訂單詳情", JOptionPane.INFORMATION_MESSAGE);
                    
                    beverageFrame.dispose();
                    
                    item[i] = "薯條";
                    qty[i] = selectedQuantity;
                    cash[i] = 25*selectedQuantity;
                    items.add(item[i]);
                    qtys.add(qty[i]);
                    cashes.add(cash[i]);
                    i++;
                    cashcash += 25*selectedQuantity;
              	}
        });
        beverageFrame.setVisible(true);
    }
	// 熱狗
	public static void hotdog() {
        JFrame beverageFrame = new JFrame("數量設定");
        beverageFrame.setSize(300, 400);
        beverageFrame.setLayout(null);
        beverageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 關閉飲料設定視窗時不影響主視窗

        JPanel panel = new JPanel();
        beverageFrame.add(panel);
	        
        JLabel quantity;
        quantity = new JLabel("數量");
        quantity.setBounds(20, 50, 100, 30);
	        
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1); // 設定 JSpinner 的最小、最大值和初始值
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setBounds(110, 55, 60, 30);

        beverageFrame.add(quantity);
        beverageFrame.add(quantitySpinner);
	        
        // 新增取消按鈕
        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(30, 120, 100, 30);
        beverageFrame.add(cancelButton);

        // 新增確定按鈕
        JButton confirmButton = new JButton("確定");
        confirmButton.setBounds(150, 120, 100, 30);
        beverageFrame.add(confirmButton);
	        
        // 設定取消按鈕的操作
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	beverageFrame.dispose(); // 關閉視窗
            }
        });        
	    confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             
                   	int selectedQuantity = (int) quantitySpinner.getValue();                   
                    // 建立提示訊息字串
                    String message = "您選擇的項目:\n";
                    message += "熱狗\n";
                    message += "數量: " + selectedQuantity;
                    // 顯示提示訊息視窗
                    JOptionPane.showMessageDialog(beverageFrame, message, "訂單詳情", JOptionPane.INFORMATION_MESSAGE);
                    
                    beverageFrame.dispose();
                    
                    item[i] = "熱狗";
                    qty[i] = selectedQuantity;
                    cash[i] = 25*selectedQuantity;
                    items.add(item[i]);
                    qtys.add(qty[i]);
                    cashes.add(cash[i]);
                    i++;
                    cashcash += 25*selectedQuantity;
              	}
        });
        beverageFrame.setVisible(true);
    }
	// 雞塊
	public static void chicken() {
        JFrame beverageFrame = new JFrame("數量設定");
        beverageFrame.setSize(300, 400);
        beverageFrame.setLayout(null);
        beverageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 關閉飲料設定視窗時不影響主視窗

        JPanel panel = new JPanel();
        beverageFrame.add(panel);
	        
        JLabel quantity;
        quantity = new JLabel("數量");
        quantity.setBounds(20, 50, 100, 30);
	        
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1); // 設定 JSpinner 的最小、最大值和初始值
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setBounds(110, 55, 60, 30);

        beverageFrame.add(quantity);
        beverageFrame.add(quantitySpinner);
	        
        // 新增取消按鈕
        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(30, 120, 100, 30);
        beverageFrame.add(cancelButton);

        // 新增確定按鈕
        JButton confirmButton = new JButton("確定");
        confirmButton.setBounds(150, 120, 100, 30);
        beverageFrame.add(confirmButton);
	        
        // 設定取消按鈕的操作
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	beverageFrame.dispose(); // 關閉視窗
            }
        });        
	    confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             
                   	int selectedQuantity = (int) quantitySpinner.getValue();                   
                    // 建立提示訊息字串
                    String message = "您選擇的項目:\n";
                    message += "雞塊\n";
                    message += "數量: " + selectedQuantity;
                    // 顯示提示訊息視窗
                    JOptionPane.showMessageDialog(beverageFrame, message, "訂單詳情", JOptionPane.INFORMATION_MESSAGE);
                    
                    beverageFrame.dispose();
                    
                    item[i] = "雞塊";
                    qty[i] = selectedQuantity;
                    cash[i] = 35*selectedQuantity;
                    items.add(item[i]);
                    qtys.add(qty[i]);
                    cashes.add(cash[i]);
                    i++;
                    cashcash += 35*selectedQuantity;
              	}
        });
        beverageFrame.setVisible(true);
    }
	// 檸檬雞柳
	public static void lemonchicken() {
        JFrame beverageFrame = new JFrame("數量設定");
        beverageFrame.setSize(300, 400);
        beverageFrame.setLayout(null);
        beverageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 關閉飲料設定視窗時不影響主視窗

        JPanel panel = new JPanel();
        beverageFrame.add(panel);
	        
        JLabel quantity;
        quantity = new JLabel("數量");
        quantity.setBounds(20, 50, 100, 30);
	        
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1); // 設定 JSpinner 的最小、最大值和初始值
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setBounds(110, 55, 60, 30);

        beverageFrame.add(quantity);
        beverageFrame.add(quantitySpinner);
	        
        // 新增取消按鈕
        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(30, 120, 100, 30);
        beverageFrame.add(cancelButton);

        // 新增確定按鈕
        JButton confirmButton = new JButton("確定");
        confirmButton.setBounds(150, 120, 100, 30);
        beverageFrame.add(confirmButton);
	        
        // 設定取消按鈕的操作
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	beverageFrame.dispose(); // 關閉視窗
            }
        });        
	    confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             
                   	int selectedQuantity = (int) quantitySpinner.getValue();                   
                    // 建立提示訊息字串
                    String message = "您選擇的項目:\n";
                    message += "檸檬雞柳\n";
                    message += "數量: " + selectedQuantity;
                    // 顯示提示訊息視窗
                    JOptionPane.showMessageDialog(beverageFrame, message, "訂單詳情", JOptionPane.INFORMATION_MESSAGE);
                    
                    beverageFrame.dispose();
                    
                    item[i] = "檸檬雞柳";
                    qty[i] = selectedQuantity;
                    cash[i] = 40*selectedQuantity;
                    items.add(item[i]);
                    qtys.add(qty[i]);
                    cashes.add(cash[i]);
                    i++;
                    cashcash += 40*selectedQuantity;
              	}
        });
        beverageFrame.setVisible(true);
    }
	// 薯餅
	public static void friedcookie() {
        JFrame beverageFrame = new JFrame("數量設定");
        beverageFrame.setSize(300, 400);
        beverageFrame.setLayout(null);
        beverageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 關閉飲料設定視窗時不影響主視窗

        JPanel panel = new JPanel();
        beverageFrame.add(panel);
	        
        JLabel quantity;
        quantity = new JLabel("數量");
        quantity.setBounds(20, 50, 100, 30);
	        
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1); // 設定 JSpinner 的最小、最大值和初始值
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setBounds(110, 55, 60, 30);

        beverageFrame.add(quantity);
        beverageFrame.add(quantitySpinner);
	        
        // 新增取消按鈕
        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(30, 120, 100, 30);
        beverageFrame.add(cancelButton);

        // 新增確定按鈕
        JButton confirmButton = new JButton("確定");
        confirmButton.setBounds(150, 120, 100, 30);
        beverageFrame.add(confirmButton);
	        
        // 設定取消按鈕的操作
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	beverageFrame.dispose(); // 關閉視窗
            }
        });        
	    confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             
                   	int selectedQuantity = (int) quantitySpinner.getValue();                   
                    // 建立提示訊息字串
                    String message = "您選擇的項目:\n";
                    message += "薯餅\n";
                    message += "數量: " + selectedQuantity;
                    // 顯示提示訊息視窗
                    JOptionPane.showMessageDialog(beverageFrame, message, "訂單詳情", JOptionPane.INFORMATION_MESSAGE);
                    
                    beverageFrame.dispose();
                    
                    item[i] = "薯餅";
                    qty[i] = selectedQuantity;
                    cash[i] = 25*selectedQuantity;
                    items.add(item[i]);
                    qtys.add(qty[i]);
                    cashes.add(cash[i]);
                    i++;
                    cashcash += 25*selectedQuantity;
              	}
        });
        beverageFrame.setVisible(true);
    }
	// 法國吐司
	public static void franchtoast() {
        JFrame beverageFrame = new JFrame("數量設定");
        beverageFrame.setSize(300, 400);
        beverageFrame.setLayout(null);
        beverageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 關閉飲料設定視窗時不影響主視窗

        JPanel panel = new JPanel();
        beverageFrame.add(panel);
	        
        JLabel quantity;
        quantity = new JLabel("數量");
        quantity.setBounds(20, 50, 100, 30);
	        
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1); // 設定 JSpinner 的最小、最大值和初始值
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setBounds(110, 55, 60, 30);

        beverageFrame.add(quantity);
        beverageFrame.add(quantitySpinner);
	        
        // 新增取消按鈕
        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(30, 120, 100, 30);
        beverageFrame.add(cancelButton);

        // 新增確定按鈕
        JButton confirmButton = new JButton("確定");
        confirmButton.setBounds(150, 120, 100, 30);
        beverageFrame.add(confirmButton);
	        
        // 設定取消按鈕的操作
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	beverageFrame.dispose(); // 關閉視窗
            }
        });        
	    confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             
                   	int selectedQuantity = (int) quantitySpinner.getValue();                   
                    // 建立提示訊息字串
                    String message = "您選擇的項目:\n";
                    message += "法國吐司\n";
                    message += "數量: " + selectedQuantity;
                    // 顯示提示訊息視窗
                    JOptionPane.showMessageDialog(beverageFrame, message, "訂單詳情", JOptionPane.INFORMATION_MESSAGE);
                    
                    beverageFrame.dispose();
                    
                    item[i] = "法國吐司";
                    qty[i] = selectedQuantity;
                    cash[i] = 40*selectedQuantity;
                    items.add(item[i]);
                    qtys.add(qty[i]);
                    cashes.add(cash[i]);
                    i++;
                    cashcash += 40*selectedQuantity;
              	}
        });
        beverageFrame.setVisible(true);
    }
	// 歐姆蛋
	public static void omegg() {
        JFrame beverageFrame = new JFrame("數量設定");
        beverageFrame.setSize(300, 400);
        beverageFrame.setLayout(null);
        beverageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 關閉飲料設定視窗時不影響主視窗

        JPanel panel = new JPanel();
        beverageFrame.add(panel);
	        
        JLabel quantity;
        quantity = new JLabel("數量");
        quantity.setBounds(20, 50, 100, 30);
	        
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1); // 設定 JSpinner 的最小、最大值和初始值
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setBounds(110, 55, 60, 30);

        beverageFrame.add(quantity);
        beverageFrame.add(quantitySpinner);
	        
        // 新增取消按鈕
        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(30, 120, 100, 30);
        beverageFrame.add(cancelButton);

        // 新增確定按鈕
        JButton confirmButton = new JButton("確定");
        confirmButton.setBounds(150, 120, 100, 30);
        beverageFrame.add(confirmButton);
	        
        // 設定取消按鈕的操作
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	beverageFrame.dispose(); // 關閉視窗
            }
        });        
	    confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             
                   	int selectedQuantity = (int) quantitySpinner.getValue();                   
                    // 建立提示訊息字串
                    String message = "您選擇的項目:\n";
                    message += "歐姆蛋\n";
                    message += "數量: " + selectedQuantity;
                    // 顯示提示訊息視窗
                    JOptionPane.showMessageDialog(beverageFrame, message, "訂單詳情", JOptionPane.INFORMATION_MESSAGE);
                    
                    beverageFrame.dispose();
                    
                    item[i] = "歐姆蛋";
                    qty[i] = selectedQuantity;
                    cash[i] = 40*selectedQuantity;
                    items.add(item[i]);
                    qtys.add(qty[i]);
                    cashes.add(cash[i]);
                    i++;
                    cashcash += 40*selectedQuantity;
              	}
        });
        beverageFrame.setVisible(true);
    }
	// 火腿
	public static void ham() {
        JFrame beverageFrame = new JFrame("數量設定");
        beverageFrame.setSize(300, 400);
        beverageFrame.setLayout(null);
        beverageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 關閉飲料設定視窗時不影響主視窗

        JPanel panel = new JPanel();
        beverageFrame.add(panel);
	        
        JLabel quantity;
        quantity = new JLabel("數量");
        quantity.setBounds(20, 50, 100, 30);
	        
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1); // 設定 JSpinner 的最小、最大值和初始值
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setBounds(110, 55, 60, 30);

        beverageFrame.add(quantity);
        beverageFrame.add(quantitySpinner);
	        
        // 新增取消按鈕
        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(30, 120, 100, 30);
        beverageFrame.add(cancelButton);

        // 新增確定按鈕
        JButton confirmButton = new JButton("確定");
        confirmButton.setBounds(150, 120, 100, 30);
        beverageFrame.add(confirmButton);
	        
        // 設定取消按鈕的操作
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	beverageFrame.dispose(); // 關閉視窗
            }
        });        
	    confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             
                   	int selectedQuantity = (int) quantitySpinner.getValue();                   
                    // 建立提示訊息字串
                    String message = "您選擇的項目:\n";
                    message += "火腿\n";
                    message += "數量: " + selectedQuantity;
                    // 顯示提示訊息視窗
                    JOptionPane.showMessageDialog(beverageFrame, message, "訂單詳情", JOptionPane.INFORMATION_MESSAGE);
                    
                    beverageFrame.dispose();
                    
                    item[i] = "火腿";
                    qty[i] = selectedQuantity;
                    cash[i] = 25*selectedQuantity;
                    items.add(item[i]);
                    qtys.add(qty[i]);
                    cashes.add(cash[i]);
                    i++;
                    cashcash += 25*selectedQuantity;
              	}
        });
        beverageFrame.setVisible(true);
    }
	// 烏龍麵
	public static void woolong() {
        JFrame beverageFrame = new JFrame("數量設定");
        beverageFrame.setSize(300, 400);
        beverageFrame.setLayout(null);
        beverageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 關閉飲料設定視窗時不影響主視窗

        JPanel panel = new JPanel();
        beverageFrame.add(panel);
	        
        JLabel quantity;
        quantity = new JLabel("數量");
        quantity.setBounds(20, 50, 100, 30);
	        
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1); // 設定 JSpinner 的最小、最大值和初始值
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setBounds(110, 55, 60, 30);

        beverageFrame.add(quantity);
        beverageFrame.add(quantitySpinner);
	        
        // 新增取消按鈕
        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(30, 120, 100, 30);
        beverageFrame.add(cancelButton);

        // 新增確定按鈕
        JButton confirmButton = new JButton("確定");
        confirmButton.setBounds(150, 120, 100, 30);
        beverageFrame.add(confirmButton);
	        
        // 設定取消按鈕的操作
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	beverageFrame.dispose(); // 關閉視窗
            }
        });        
	    confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             
                   	int selectedQuantity = (int) quantitySpinner.getValue();                   
                    // 建立提示訊息字串
                    String message = "您選擇的項目:\n";
                    message += "豬排烏龍麵\n";
                    message += "數量: " + selectedQuantity;
                    // 顯示提示訊息視窗
                    JOptionPane.showMessageDialog(beverageFrame, message, "訂單詳情", JOptionPane.INFORMATION_MESSAGE);
                    
                    beverageFrame.dispose();
                    
                    item[i] = "豬排烏龍麵";
                    qty[i] = selectedQuantity;
                    cash[i] = 85*selectedQuantity;
                    items.add(item[i]);
                    qtys.add(qty[i]);
                    cashes.add(cash[i]);
                    i++;
                    cashcash += 85*selectedQuantity;
              	}
        });
        beverageFrame.setVisible(true);
    }
	// 鐵板麵
	public static void steelnoodle() {
        JFrame beverageFrame = new JFrame("選擇口味");
        beverageFrame.setSize(300, 400);
        beverageFrame.setLayout(null);
        beverageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 關閉飲料設定視窗時不影響主視窗

        JPanel panel = new JPanel();
        beverageFrame.add(panel);
        
        JLabel souce, quantity;
        souce = new JLabel("口味");
        quantity = new JLabel("數量");

        JRadioButton mushroom = new JRadioButton("蘑菇");
        JRadioButton pepper = new JRadioButton("黑胡椒");
        JRadioButton comprehensive = new JRadioButton("綜合");
        ButtonGroup souceGroup = new ButtonGroup();
        souceGroup.add(mushroom);
        souceGroup.add(pepper);
        souceGroup.add(comprehensive);

        souce.setBounds(20, 50, 100, 30);
        mushroom.setBounds(70, 50, 70, 30);
        pepper.setBounds(140, 50, 70, 30);
        comprehensive.setBounds(220, 50, 70, 30);
	        
        quantity.setBounds(20, 110, 100, 30);
	        
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1); // 設定 JSpinner 的最小、最大值和初始值
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setBounds(110, 115, 60, 30);
	        
        beverageFrame.add(souce);
        beverageFrame.add(mushroom);
        beverageFrame.add(pepper);
        beverageFrame.add(comprehensive);
        beverageFrame.add(quantity);
        beverageFrame.add(quantitySpinner);
	        
        // 新增取消按鈕
        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(30, 180, 100, 30);
        beverageFrame.add(cancelButton);

        // 新增確定按鈕
        JButton confirmButton = new JButton("確定");
        confirmButton.setBounds(150, 180, 100, 30);
        beverageFrame.add(confirmButton);
	        
        // 設定取消按鈕的操作
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	beverageFrame.dispose(); // 關閉視窗
            }
        });        
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            	if (!mushroom.isSelected() && !pepper.isSelected() && !comprehensive.isSelected()) {
                    JOptionPane.showMessageDialog(beverageFrame, "請選擇口味", "提示", JOptionPane.WARNING_MESSAGE);
                }
            	else {
            		String selectedsouce = "";
                    if (mushroom.isSelected()) {
                    	selectedsouce = "蘑菇";
                    } else if (pepper.isSelected()) {
                    	selectedsouce = "黑胡椒";
                    } else if (comprehensive.isSelected()) {
                    	selectedsouce = "綜合";
                    }
                  
                    int selectedQuantity = (int) quantitySpinner.getValue();                   
                    // 建立提示訊息字串
                    String message = "您選擇的項目:\n";
                    message += "鐵板麵\n";
                    message += "口味: " + selectedsouce + "\n";
                    message += "數量: " + selectedQuantity;
                    // 顯示提示訊息視窗
                    JOptionPane.showMessageDialog(beverageFrame, message, "訂單詳情", JOptionPane.INFORMATION_MESSAGE);
                    
                    beverageFrame.dispose();
                    
                    if (mushroom.isSelected()) {
                        item[i] = "蘑菇鐵板麵";
                        qty[i] = selectedQuantity;
                        cash[i] = 50*selectedQuantity;
                        items.add(item[i]);
                        qtys.add(qty[i]);
                        cashes.add(cash[i]);
                        i++;
                        cashcash += 50*selectedQuantity;
                    }
                    if (pepper.isSelected()) {
                        item[i] = "黑胡椒鐵板麵";
                        qty[i] = selectedQuantity;
                        cash[i] = 50*selectedQuantity;
                        items.add(item[i]);
                        qtys.add(qty[i]);
                        cashes.add(cash[i]);
                        i++;
                        cashcash += 50*selectedQuantity;
                    }
                    if (comprehensive.isSelected()) {
                        item[i] = "綜合鐵板麵";
                        qty[i] = selectedQuantity;
                        cash[i] = 50*selectedQuantity;
                        items.add(item[i]);
                        qtys.add(qty[i]);
                        cashes.add(cash[i]);
                        i++;
                        cashcash += 50*selectedQuantity;
                    }
                }
            }
        });
        beverageFrame.setVisible(true);
    }		
	// 飯、麵
	// 三杯雞
	public static void threeglass() {
        JFrame beverageFrame = new JFrame("選擇口味");
        beverageFrame.setSize(300, 400);
        beverageFrame.setLayout(null);
        beverageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 關閉飲料設定視窗時不影響主視窗

        JPanel panel = new JPanel();
        beverageFrame.add(panel);
	        
        JLabel souce, quantity;
        souce = new JLabel("主餐");
        quantity = new JLabel("數量");

        JRadioButton rice = new JRadioButton("燉飯");
        JRadioButton noodle = new JRadioButton("義大利麵");
        ButtonGroup souceGroup = new ButtonGroup();
        souceGroup.add(rice);
        souceGroup.add(noodle);

        souce.setBounds(20, 50, 100, 30);
        rice.setBounds(70, 50, 70, 30);
        noodle.setBounds(140, 50, 110, 30);
	        
        quantity.setBounds(20, 110, 100, 30);
	        
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1); // 設定 JSpinner 的最小、最大值和初始值
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setBounds(110, 115, 60, 30);
	        
        beverageFrame.add(souce);
        beverageFrame.add(rice);
        beverageFrame.add(noodle);
        beverageFrame.add(quantity);
        beverageFrame.add(quantitySpinner);
	        
        // 新增取消按鈕
        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(30, 180, 100, 30);
        beverageFrame.add(cancelButton);

        // 新增確定按鈕
        JButton confirmButton = new JButton("確定");
        confirmButton.setBounds(150, 180, 100, 30);
        beverageFrame.add(confirmButton);
	        
        // 設定取消按鈕的操作
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	beverageFrame.dispose(); // 關閉視窗
            }
        });        
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            	if (!rice.isSelected() && !noodle.isSelected()) {
                    JOptionPane.showMessageDialog(beverageFrame, "請選擇主餐", "提示", JOptionPane.WARNING_MESSAGE);
                }
            	else {
            		String selectedsouce = "";
                    if (rice.isSelected()) {
                    	selectedsouce = "燉飯";
                    } else if (noodle.isSelected()) {
                    	selectedsouce = "義大利麵";
                    }
                  
                    int selectedQuantity = (int) quantitySpinner.getValue();                   
                    // 建立提示訊息字串
                    String message = "您選擇的項目:\n";
                    message += "三杯雞\n";
                    message += "主餐: " + selectedsouce + "\n";
                    message += "數量: " + selectedQuantity;
                    // 顯示提示訊息視窗
                    JOptionPane.showMessageDialog(beverageFrame, message, "訂單詳情", JOptionPane.INFORMATION_MESSAGE);
                    
                    beverageFrame.dispose();
                    
                    if (rice.isSelected()) {
                        item[i] = "三杯雞燉飯";
                        qty[i] = selectedQuantity;
                        cash[i] = 80*selectedQuantity;
                        items.add(item[i]);
                        qtys.add(qty[i]);
                        cashes.add(cash[i]);
                        i++;
                        cashcash += 80*selectedQuantity;
                    }
                    if (noodle.isSelected()) {
                        item[i] = "三杯雞義大利麵";
                        qty[i] = selectedQuantity;
                        cash[i] = 80*selectedQuantity;
                        items.add(item[i]);
                        qtys.add(qty[i]);
                        cashes.add(cash[i]);
                        i++;
                        cashcash += 80*selectedQuantity;
                    }
                }
            }
        });
        beverageFrame.setVisible(true);
	    }
	// 飯、麵
	// 宮保雞
	public static void palace() {
        JFrame beverageFrame = new JFrame("選擇口味");
        beverageFrame.setSize(300, 400);
        beverageFrame.setLayout(null);
        beverageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 關閉飲料設定視窗時不影響主視窗

        JPanel panel = new JPanel();
        beverageFrame.add(panel);
	        
        JLabel souce, quantity;
        souce = new JLabel("主餐");
        quantity = new JLabel("數量");

        JRadioButton rice = new JRadioButton("燉飯");
        JRadioButton noodle = new JRadioButton("義大利麵");
        ButtonGroup souceGroup = new ButtonGroup();
        souceGroup.add(rice);
        souceGroup.add(noodle);

        souce.setBounds(20, 50, 100, 30);
        rice.setBounds(70, 50, 70, 30);
        noodle.setBounds(140, 50, 110, 30);
	        
        quantity.setBounds(20, 110, 100, 30);
	        
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1); // 設定 JSpinner 的最小、最大值和初始值
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setBounds(110, 115, 60, 30);
	        
        beverageFrame.add(souce);
        beverageFrame.add(rice);
        beverageFrame.add(noodle);
        beverageFrame.add(quantity);
        beverageFrame.add(quantitySpinner);
	        
        // 新增取消按鈕
        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(30, 180, 100, 30);
        beverageFrame.add(cancelButton);

        // 新增確定按鈕
        JButton confirmButton = new JButton("確定");
        confirmButton.setBounds(150, 180, 100, 30);
        beverageFrame.add(confirmButton);
	        
        // 設定取消按鈕的操作
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	beverageFrame.dispose(); // 關閉視窗
            }
        });        
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            	if (!rice.isSelected() && !noodle.isSelected()) {
                    JOptionPane.showMessageDialog(beverageFrame, "請選擇主餐", "提示", JOptionPane.WARNING_MESSAGE);
                }
            	else {
            		String selectedsouce = "";
                    if (rice.isSelected()) {
                    	selectedsouce = "燉飯";
                    } else if (noodle.isSelected()) {
                    	selectedsouce = "義大利麵";
                    }
                  
                    int selectedQuantity = (int) quantitySpinner.getValue();                   
                    // 建立提示訊息字串
                    String message = "您選擇的項目:\n";
                    message += "宮保雞\n";
                    message += "主餐: " + selectedsouce + "\n";
                    message += "數量: " + selectedQuantity;
                    // 顯示提示訊息視窗
                    JOptionPane.showMessageDialog(beverageFrame, message, "訂單詳情", JOptionPane.INFORMATION_MESSAGE);
                    
                    beverageFrame.dispose();
                    
                    if (rice.isSelected()) {
                        item[i] = "宮保雞燉飯";
                        qty[i] = selectedQuantity;
                        cash[i] = 80*selectedQuantity;
                        items.add(item[i]);
                        qtys.add(qty[i]);
                        cashes.add(cash[i]);
                        i++;
                        cashcash += 80*selectedQuantity;
                    }
                    if (noodle.isSelected()) {
                        item[i] = "宮保雞義大利麵";
                        qty[i] = selectedQuantity;
                        cash[i] = 80*selectedQuantity;
                        items.add(item[i]);
                        qtys.add(qty[i]);
                        cashes.add(cash[i]);
                        i++;
                        cashcash += 80*selectedQuantity;
                    }
                }
            }
        });
        beverageFrame.setVisible(true);
	    }
	// 飯、麵
	// 打拋豬
	public static void hitpig() {
        JFrame beverageFrame = new JFrame("選擇口味");
        beverageFrame.setSize(300, 400);
        beverageFrame.setLayout(null);
        beverageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 關閉飲料設定視窗時不影響主視窗

        JPanel panel = new JPanel();
        beverageFrame.add(panel);
	        
        JLabel souce, quantity;
        souce = new JLabel("主餐");
        quantity = new JLabel("數量");

        JRadioButton rice = new JRadioButton("燉飯");
        JRadioButton noodle = new JRadioButton("義大利麵");
        ButtonGroup souceGroup = new ButtonGroup();
        souceGroup.add(rice);
        souceGroup.add(noodle);

        souce.setBounds(20, 50, 100, 30);
        rice.setBounds(70, 50, 70, 30);
        noodle.setBounds(140, 50, 110, 30);
	        
        quantity.setBounds(20, 110, 100, 30);
	        
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1); // 設定 JSpinner 的最小、最大值和初始值
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setBounds(110, 115, 60, 30);
	        
        beverageFrame.add(souce);
        beverageFrame.add(rice);
        beverageFrame.add(noodle);
        beverageFrame.add(quantity);
        beverageFrame.add(quantitySpinner);
	        
        // 新增取消按鈕
        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(30, 180, 100, 30);
        beverageFrame.add(cancelButton);

        // 新增確定按鈕
        JButton confirmButton = new JButton("確定");
        confirmButton.setBounds(150, 180, 100, 30);
        beverageFrame.add(confirmButton);
	        
        // 設定取消按鈕的操作
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	beverageFrame.dispose(); // 關閉視窗
            }
        });        
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            	if (!rice.isSelected() && !noodle.isSelected()) {
                    JOptionPane.showMessageDialog(beverageFrame, "請選擇主餐", "提示", JOptionPane.WARNING_MESSAGE);
                }
            	else {
            		String selectedsouce = "";
                    if (rice.isSelected()) {
                    	selectedsouce = "燉飯";
                    } else if (noodle.isSelected()) {
                    	selectedsouce = "義大利麵";
                    }
                  
                    int selectedQuantity = (int) quantitySpinner.getValue();                   
                    // 建立提示訊息字串
                    String message = "您選擇的項目:\n";
                    message += "打拋豬\n";
                    message += "主餐: " + selectedsouce + "\n";
                    message += "數量: " + selectedQuantity;
                    // 顯示提示訊息視窗
                    JOptionPane.showMessageDialog(beverageFrame, message, "訂單詳情", JOptionPane.INFORMATION_MESSAGE);
                    
                    beverageFrame.dispose();
                    
                    if (rice.isSelected()) {
                        item[i] = "打拋豬燉飯";
                        qty[i] = selectedQuantity;
                        cash[i] = 80*selectedQuantity;
                        items.add(item[i]);
                        qtys.add(qty[i]);
                        cashes.add(cash[i]);
                        i++;
                        cashcash += 80*selectedQuantity;
                    }
                    if (noodle.isSelected()) {
                        item[i] = "打拋豬義大利麵";
                        qty[i] = selectedQuantity;
                        cash[i] = 80*selectedQuantity;
                        items.add(item[i]);
                        qtys.add(qty[i]);
                        cashes.add(cash[i]);
                        i++;
                        cashcash += 80*selectedQuantity;
                    }
                }
            }
        });
        beverageFrame.setVisible(true);
	    }
	// 飯、麵
	// 麻油雞
	public static void oilchicken() {
        JFrame beverageFrame = new JFrame("選擇口味");
        beverageFrame.setSize(300, 400);
        beverageFrame.setLayout(null);
        beverageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 關閉飲料設定視窗時不影響主視窗

        JPanel panel = new JPanel();
        beverageFrame.add(panel);
	        
        JLabel souce, quantity;
        souce = new JLabel("主餐");
        quantity = new JLabel("數量");

        JRadioButton rice = new JRadioButton("燉飯");
        JRadioButton noodle = new JRadioButton("義大利麵");
        ButtonGroup souceGroup = new ButtonGroup();
        souceGroup.add(rice);
        souceGroup.add(noodle);

        souce.setBounds(20, 50, 100, 30);
        rice.setBounds(70, 50, 70, 30);
        noodle.setBounds(140, 50, 110, 30);
	        
        quantity.setBounds(20, 110, 100, 30);
	        
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1); // 設定 JSpinner 的最小、最大值和初始值
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setBounds(110, 115, 60, 30);
	        
        beverageFrame.add(souce);
        beverageFrame.add(rice);
        beverageFrame.add(noodle);
        beverageFrame.add(quantity);
        beverageFrame.add(quantitySpinner);
	        
        // 新增取消按鈕
        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(30, 180, 100, 30);
        beverageFrame.add(cancelButton);

        // 新增確定按鈕
        JButton confirmButton = new JButton("確定");
        confirmButton.setBounds(150, 180, 100, 30);
        beverageFrame.add(confirmButton);
	        
        // 設定取消按鈕的操作
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	beverageFrame.dispose(); // 關閉視窗
            }
        });        
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            	if (!rice.isSelected() && !noodle.isSelected()) {
                    JOptionPane.showMessageDialog(beverageFrame, "請選擇主餐", "提示", JOptionPane.WARNING_MESSAGE);
                }
            	else {
            		String selectedsouce = "";
                    if (rice.isSelected()) {
                    	selectedsouce = "燉飯";
                    } else if (noodle.isSelected()) {
                    	selectedsouce = "義大利麵";
                    }
                  
                    int selectedQuantity = (int) quantitySpinner.getValue();                   
                    // 建立提示訊息字串
                    String message = "您選擇的項目:\n";
                    message += "麻油雞\n";
                    message += "主餐: " + selectedsouce + "\n";
                    message += "數量: " + selectedQuantity;
                    // 顯示提示訊息視窗
                    JOptionPane.showMessageDialog(beverageFrame, message, "訂單詳情", JOptionPane.INFORMATION_MESSAGE);
                    
                    beverageFrame.dispose();
                    
                    if (rice.isSelected()) {
                        item[i] = "麻油雞燉飯";
                        qty[i] = selectedQuantity;
                        cash[i] = 85*selectedQuantity;
                        items.add(item[i]);
                        qtys.add(qty[i]);
                        cashes.add(cash[i]);
                        i++;
                        cashcash += 85*selectedQuantity;
                    }
                    if (noodle.isSelected()) {
                        item[i] = "麻油雞義大利麵";
                        qty[i] = selectedQuantity;
                        cash[i] = 85*selectedQuantity;
                        items.add(item[i]);
                        qtys.add(qty[i]);
                        cashes.add(cash[i]);
                        i++;
                        cashcash += 85*selectedQuantity;
                    }
                }
            }
        });
        beverageFrame.setVisible(true);
	    }
	// 飯、麵
	// 塔香肉醬
	public static void meatsauce() {
        JFrame beverageFrame = new JFrame("選擇口味");
        beverageFrame.setSize(300, 400);
        beverageFrame.setLayout(null);
        beverageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 關閉飲料設定視窗時不影響主視窗

        JPanel panel = new JPanel();
        beverageFrame.add(panel);
	        
        JLabel souce, quantity;
        souce = new JLabel("主餐");
        quantity = new JLabel("數量");

        JRadioButton rice = new JRadioButton("燉飯");
        JRadioButton noodle = new JRadioButton("義大利麵");
        ButtonGroup souceGroup = new ButtonGroup();
        souceGroup.add(rice);
        souceGroup.add(noodle);

        souce.setBounds(20, 50, 100, 30);
        rice.setBounds(70, 50, 70, 30);
        noodle.setBounds(140, 50, 110, 30);
	        
        quantity.setBounds(20, 110, 100, 30);
	        
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1); // 設定 JSpinner 的最小、最大值和初始值
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setBounds(110, 115, 60, 30);
	        
        beverageFrame.add(souce);
        beverageFrame.add(rice);
        beverageFrame.add(noodle);
        beverageFrame.add(quantity);
        beverageFrame.add(quantitySpinner);
	        
        // 新增取消按鈕
        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(30, 180, 100, 30);
        beverageFrame.add(cancelButton);

        // 新增確定按鈕
        JButton confirmButton = new JButton("確定");
        confirmButton.setBounds(150, 180, 100, 30);
        beverageFrame.add(confirmButton);
	        
        // 設定取消按鈕的操作
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	beverageFrame.dispose(); // 關閉視窗
            }
        });        
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            	if (!rice.isSelected() && !noodle.isSelected()) {
                    JOptionPane.showMessageDialog(beverageFrame, "請選擇主餐", "提示", JOptionPane.WARNING_MESSAGE);
                }
            	else {
            		String selectedsouce = "";
                    if (rice.isSelected()) {
                    	selectedsouce = "燉飯";
                    } else if (noodle.isSelected()) {
                    	selectedsouce = "義大利麵";
                    }
                  
                    int selectedQuantity = (int) quantitySpinner.getValue();                   
                    // 建立提示訊息字串
                    String message = "您選擇的項目:\n";
                    message += "塔香肉醬\n";
                    message += "主餐: " + selectedsouce + "\n";
                    message += "數量: " + selectedQuantity;
                    // 顯示提示訊息視窗
                    JOptionPane.showMessageDialog(beverageFrame, message, "訂單詳情", JOptionPane.INFORMATION_MESSAGE);
                    
                    beverageFrame.dispose();
                    
                    if (rice.isSelected()) {
                        item[i] = "塔香肉醬燉飯";
                        qty[i] = selectedQuantity;
                        cash[i] = 85*selectedQuantity;
                        items.add(item[i]);
                        qtys.add(qty[i]);
                        cashes.add(cash[i]);
                        i++;
                        cashcash += 85*selectedQuantity;
                    }
                    if (noodle.isSelected()) {
                        item[i] = "塔香肉醬義大利麵";
                        qty[i] = selectedQuantity;
                        cash[i] = 85*selectedQuantity;
                        items.add(item[i]);
                        qtys.add(qty[i]);
                        cashes.add(cash[i]);
                        i++;
                        cashcash += 85*selectedQuantity;
                    }
                }
            }
        });
        beverageFrame.setVisible(true);
	    }
	// 飯、麵
	// 咖哩雞
	public static void cali() {
        JFrame beverageFrame = new JFrame("選擇口味");
        beverageFrame.setSize(300, 400);
        beverageFrame.setLayout(null);
        beverageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 關閉飲料設定視窗時不影響主視窗

        JPanel panel = new JPanel();
        beverageFrame.add(panel);
	        
        JLabel souce, quantity;
        souce = new JLabel("主餐");
        quantity = new JLabel("數量");

        JRadioButton rice = new JRadioButton("燉飯");
        JRadioButton noodle = new JRadioButton("義大利麵");
        ButtonGroup souceGroup = new ButtonGroup();
        souceGroup.add(rice);
        souceGroup.add(noodle);

        souce.setBounds(20, 50, 100, 30);
        rice.setBounds(70, 50, 70, 30);
        noodle.setBounds(140, 50, 110, 30);
	        
        quantity.setBounds(20, 110, 100, 30);
	        
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1); // 設定 JSpinner 的最小、最大值和初始值
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setBounds(110, 115, 60, 30);
	        
        beverageFrame.add(souce);
        beverageFrame.add(rice);
        beverageFrame.add(noodle);
        beverageFrame.add(quantity);
        beverageFrame.add(quantitySpinner);
	        
        // 新增取消按鈕
        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(30, 180, 100, 30);
        beverageFrame.add(cancelButton);

        // 新增確定按鈕
        JButton confirmButton = new JButton("確定");
        confirmButton.setBounds(150, 180, 100, 30);
        beverageFrame.add(confirmButton);
	        
        // 設定取消按鈕的操作
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	beverageFrame.dispose(); // 關閉視窗
            }
        });        
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            	if (!rice.isSelected() && !noodle.isSelected()) {
                    JOptionPane.showMessageDialog(beverageFrame, "請選擇主餐", "提示", JOptionPane.WARNING_MESSAGE);
                }
            	else {
            		String selectedsouce = "";
                    if (rice.isSelected()) {
                    	selectedsouce = "燉飯";
                    } else if (noodle.isSelected()) {
                    	selectedsouce = "義大利麵";
                    }
                  
                    int selectedQuantity = (int) quantitySpinner.getValue();                   
                    // 建立提示訊息字串
                    String message = "您選擇的項目:\n";
                    message += "咖哩雞\n";
                    message += "主餐: " + selectedsouce + "\n";
                    message += "數量: " + selectedQuantity;
                    // 顯示提示訊息視窗
                    JOptionPane.showMessageDialog(beverageFrame, message, "訂單詳情", JOptionPane.INFORMATION_MESSAGE);
                    
                    beverageFrame.dispose();
                    
                    if (rice.isSelected()) {
                        item[i] = "咖哩雞燉飯";
                        qty[i] = selectedQuantity;
                        cash[i] = 85*selectedQuantity;
                        items.add(item[i]);
                        qtys.add(qty[i]);
                        cashes.add(cash[i]);
                        i++;
                        cashcash += 85*selectedQuantity;
                    }
                    if (noodle.isSelected()) {
                        item[i] = "咖哩雞義大利麵";
                        qty[i] = selectedQuantity;
                        cash[i] = 85*selectedQuantity;
                        items.add(item[i]);
                        qtys.add(qty[i]);
                        cashes.add(cash[i]);
                        i++;
                        cashcash += 85*selectedQuantity;
                    }
                }
            }
        });
        beverageFrame.setVisible(true);
	    }

	//主頁面
    public static void main(String[] args) {
        JFrame frame = new JFrame("食光茶旅");
        frame.setSize(400, 600);
//        // 獲得JFrame的內容面板
//        Container contentPane = frame.getContentPane();
//        // 設置背景顏色（這裡使用藍色作為示例）
//        contentPane.setOpaque(true);
//        contentPane.setBackground(Color.GREEN);
//        frame.repaint();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //cardlayout
        CardLayout cardlayout = new CardLayout();
        //cardpanel
        JPanel cardpanel = new JPanel(cardlayout);
        //所有標題頁面
        JPanel homepanel = new JPanel();
        homepanel.setLayout(new GridLayout(4, 1));
        JPanel orderpanel1 = new JPanel();
        orderpanel1.setLayout(new BorderLayout()); // 使用BorderLayout
        JPanel orderpanel2 = new JPanel();
        orderpanel2.setLayout(new BorderLayout()); // 使用BorderLayout
        JPanel orderpanel3 = new JPanel();
        orderpanel3.setLayout(new BorderLayout()); // 使用BorderLayout
        JPanel orderpanel4 = new JPanel();
        orderpanel4.setLayout(new BorderLayout()); // 使用BorderLayout
        JPanel orderpanel5 = new JPanel();
        orderpanel5.setLayout(new BorderLayout()); // 使用BorderLayout
        
        // homepanel
        JPanel home1 = new JPanel();
        JPanel home2 = new JPanel();
        JPanel home3 = new JPanel();
        JPanel home4 = new JPanel();
        JLabel text1 = new JLabel("歡迎光臨");
        JLabel text1_2 = new JLabel("食光茶旅");
        text1.setFont(new Font("新細明體",Font.PLAIN, 28));
        text1_2.setFont(new Font("標楷體",Font.BOLD | Font.ITALIC, 36));
        text1_2.setForeground(Color.blue);
        //ImageIcon icon = new ImageIcon("pic\\cat.png");
        //JLabel iconLabel = new JLabel(icon);
        JLabel text2 = new JLabel("請輸入桌號");
        text2.setFont(new Font("",Font.BOLD, 20));
        //String[] tablenumber = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        String tablenumber [] = new String [12];

        for (int i = 0; i < 12; i++) {
        	tablenumber[i] = Integer.toString(i + 1);
        }
        JComboBox <String> text3 = new JComboBox<>(tablenumber);
        //String selectedNumber = (String) text3.getSelectedItem();
        ImageIcon entericon = new ImageIcon("pic\\enter.png");
        JButton text4 = new JButton(entericon);
        home1.add(text1);
        home1.add(text1_2);
        //home1.add(iconLabel);
        home2.add(text2);
        home3.add(text3);
        home4.add(text4);
        
        text4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	//帶入桌號到場外的selectedNumber字串
                selectedNumber = selectedNumber + (String) text3.getSelectedItem();
                cardlayout.show(cardpanel, "order1");
                
            }
        });
        // orderpanel1
        JPanel orderbutton1 = new JPanel();
        JPanel ordermenu1 = new JPanel();
        ordermenu1.setLayout(new GridLayout(5, 2)); // You can adjust the number of rows and columns   
        // homepanel加到主頁
        homepanel.add(home1);
        homepanel.add(home2);
        homepanel.add(home3);
        homepanel.add(home4);
        // orderpanel1
        JButton or1_1, or1_2, or1_3, or1_4, or1_5;
        or1_1 = new JButton("飲料");
        or1_2 = new JButton("中式餐點");
        or1_3 = new JButton("點心");
        or1_4 = new JButton("飯、麵");
        or1_5 = new JButton("食光套餐");
        // Add other buttons and ActionListeners similarly
        orderbutton1.add(or1_1);
        orderbutton1.add(or1_2);
        orderbutton1.add(or1_3);
        orderbutton1.add(or1_4);
        orderbutton1.add(or1_5);
        JButton vegetable1_1, vegetable1_2, vegetable1_3, vegetable1_4, vegetable1_5, vegetable1_6, vegetable1_7, vegetable1_8, vegetable1_9, vegetable1_10;
        vegetable1_1 = new JButton("綠茶 $20");
        vegetable1_2 = new JButton("紅茶 $20");
        vegetable1_3 = new JButton("奶茶 $25");
        vegetable1_4 = new JButton("豆漿 $25");
        vegetable1_5 = new JButton("鮮奶茶 $35");
        vegetable1_6 = new JButton("可可牛奶 $35");
        vegetable1_7 = new JButton("薰衣草奶茶 $40");
        vegetable1_8 = new JButton("山藥薏仁 $40");
        vegetable1_9 = new JButton("柳橙汁 $30");
        vegetable1_10 = new JButton("可樂 $25");
        // Add ActionListener to handle button clicks for vegetable buttons
        ordermenu1.add(vegetable1_1);
        ordermenu1.add(vegetable1_2);
        ordermenu1.add(vegetable1_3);
        ordermenu1.add(vegetable1_4);
        ordermenu1.add(vegetable1_5);
        ordermenu1.add(vegetable1_6);
        ordermenu1.add(vegetable1_7);
        ordermenu1.add(vegetable1_8);
        ordermenu1.add(vegetable1_9);
        ordermenu1.add(vegetable1_10);
        
        // 購物車按鈕
        JPanel checkpanel1 = new JPanel();
        JButton check1 = new JButton("進入購物車");
        checkpanel1.add(check1);
        frame.add(checkpanel1);
        orderpanel1.add(checkpanel1, BorderLayout.SOUTH);
        
        // 添加orderpanel1排版
        orderpanel1.add(orderbutton1, BorderLayout.NORTH);
        orderpanel1.add(ordermenu1, BorderLayout.CENTER);
        // orderpanel1所有按鈕設置傾聽
        or1_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	cardlayout.show(cardpanel, "order1");
            }
        });       
        or1_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	cardlayout.show(cardpanel, "order2");
            }
        }); 
        or1_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	cardlayout.show(cardpanel, "order3");
            }
        });
        or1_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	cardlayout.show(cardpanel, "order4");
            }
        });
        or1_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	cardlayout.show(cardpanel, "order5");
            }
        });          
        vegetable1_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	greentea(); // 打開飲料設定視窗
            }
        });
        vegetable1_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	redtea(); // 打開飲料設定視窗
            }
        });
        vegetable1_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	milktea(); // 打開飲料設定視窗
            }
        });
        vegetable1_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	soymilk(); // 打開飲料設定視窗
            }
        });
        vegetable1_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	milk(); // 打開飲料設定視窗
            }
        });
        vegetable1_6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	cocomilk(); // 打開飲料設定視窗
            }
        });
        vegetable1_7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	lavender(); // 打開飲料設定視窗
            }
        });
        vegetable1_8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	barley(); // 打開飲料設定視窗
            }
        });
        vegetable1_9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	orange(); // 打開飲料設定視窗
            }
        });
        vegetable1_10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	cola(); // 打開飲料設定視窗
            }
        });
        check1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	checkoutbox(); // 購物車
            }
        });

        // orderpanel2
        JPanel orderbutton2 = new JPanel();
        JPanel ordermenu2 = new JPanel();
        ordermenu2.setLayout(new GridLayout(5, 2)); // You can adjust the number of rows and columns
        
        JButton or2_1, or2_2, or2_3, or2_4, or2_5;
        or2_1 = new JButton("飲料");
        or2_2 = new JButton("中式餐點");
        or2_3 = new JButton("點心");
        or2_4 = new JButton("飯、麵");
        or2_5 = new JButton("食光套餐");
        // Add other buttons and ActionListeners similarly
        orderbutton2.add(or2_1);
        orderbutton2.add(or2_2);
        orderbutton2.add(or2_3);
        orderbutton2.add(or2_4);
        orderbutton2.add(or2_5);

        JButton vegetable2_1, vegetable2_2, vegetable2_3, vegetable2_4, vegetable2_5, vegetable2_6, vegetable2_7, vegetable2_8, vegetable2_9, vegetable2_10;
        vegetable2_1 = new JButton("蛋餅 $25");
        vegetable2_2 = new JButton("蔥抓餅 $30");
        vegetable2_3 = new JButton("蒸餃 $40");
        vegetable2_4 = new JButton("小籠包 $60");
        vegetable2_5 = new JButton("食光粥 $50");
        vegetable2_6 = new JButton("蘿蔔糕 $30");
        vegetable2_7 = new JButton("米漢堡 $50");
        vegetable2_8 = new JButton("");
        vegetable2_9 = new JButton("");
        vegetable2_10 = new JButton("");
        // Add ActionListener to handle button clicks for vegetable buttons
        ordermenu2.add(vegetable2_1);
        ordermenu2.add(vegetable2_2);
        ordermenu2.add(vegetable2_3);
        ordermenu2.add(vegetable2_4);
        ordermenu2.add(vegetable2_5);
        ordermenu2.add(vegetable2_6);
        ordermenu2.add(vegetable2_7);
        ordermenu2.add(vegetable2_8);
        ordermenu2.add(vegetable2_9);
        ordermenu2.add(vegetable2_10);
        // 購物車按鈕
        JPanel checkpanel2 = new JPanel();
        JButton check2 = new JButton("進入購物車");
        checkpanel2.add(check2);
        frame.add(checkpanel2);
        orderpanel2.add(checkpanel2, BorderLayout.SOUTH);
        // 添加orderpanel2排版
        orderpanel2.add(orderbutton2, BorderLayout.NORTH);
        orderpanel2.add(ordermenu2, BorderLayout.CENTER);
        // orderpanel2所有按鈕設置傾聽
        or2_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	cardlayout.show(cardpanel, "order1");
            }
        });       
        or2_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	cardlayout.show(cardpanel, "order2");
            }
        });
        or2_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	cardlayout.show(cardpanel, "order3");
            }
        });
        or2_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	cardlayout.show(cardpanel, "order4");
            }
        });
        or2_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	cardlayout.show(cardpanel, "order5");
            }
        });      
        vegetable2_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	eggcookie();
            }
        });  
        vegetable2_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	onioncookie();
            }
        });
        vegetable2_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	dumpling();
            }
        });
        vegetable2_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	bun();
            }
        });
        vegetable2_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	porridge();
            }
        });
        vegetable2_6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	radish();
            }
        });
        vegetable2_7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ricehumber();
            }
        });
        check2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	checkoutbox(); // 購物車
            }
        });
        
        // orderpanel3
        JPanel orderbutton3 = new JPanel();
        JPanel ordermenu3 = new JPanel();
        ordermenu3.setLayout(new GridLayout(5, 2)); // You can adjust the number of rows and columns
        
        JButton or3_1, or3_2, or3_3, or3_4, or3_5;
        or3_1 = new JButton("飲料");
        or3_2 = new JButton("中式餐點");
        or3_3 = new JButton("點心");
        or3_4 = new JButton("飯、麵");
        or3_5 = new JButton("食光套餐");
        // Add other buttons and ActionListeners similarly
        orderbutton3.add(or3_1);
        orderbutton3.add(or3_2);
        orderbutton3.add(or3_3);
        orderbutton3.add(or3_4);
        orderbutton3.add(or3_5);

        JButton vegetable3_1, vegetable3_2, vegetable3_3, vegetable3_4, vegetable3_5, vegetable3_6, vegetable3_7, vegetable3_8, vegetable3_9, vegetable3_10;
        vegetable3_1 = new JButton("起司球 $30");
        vegetable3_2 = new JButton("荷包蛋 $15");
        vegetable3_3 = new JButton("薯條 $25");
        vegetable3_4 = new JButton("熱狗 $25");
        vegetable3_5 = new JButton("雞塊 $35");
        vegetable3_6 = new JButton("檸檬雞柳 $40");
        vegetable3_7 = new JButton("薯餅 $25");
        vegetable3_8 = new JButton("法國吐司 $40");
        vegetable3_9 = new JButton("歐姆蛋 $40");
        vegetable3_10 = new JButton("火腿 $25");
        // Add ActionListener to handle button clicks for vegetable buttons
        ordermenu3.add(vegetable3_1);
        ordermenu3.add(vegetable3_2);
        ordermenu3.add(vegetable3_3);
        ordermenu3.add(vegetable3_4);
        ordermenu3.add(vegetable3_5);
        ordermenu3.add(vegetable3_6);
        ordermenu3.add(vegetable3_7);
        ordermenu3.add(vegetable3_8);
        ordermenu3.add(vegetable3_9);
        ordermenu3.add(vegetable3_10);
        // 購物車按鈕
        JPanel checkpanel3 = new JPanel();
        JButton check3 = new JButton("進入購物車");
        checkpanel3.add(check3);
        frame.add(checkpanel3);
        orderpanel3.add(checkpanel3, BorderLayout.SOUTH);
        // 添加orderpanel3排版
        orderpanel3.add(orderbutton3, BorderLayout.NORTH);
        orderpanel3.add(ordermenu3, BorderLayout.CENTER);
        // orderpanel2所有按鈕設置傾聽
        or3_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	cardlayout.show(cardpanel, "order1");
            }
        });       
        or3_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	cardlayout.show(cardpanel, "order2");
            }
        });
        or3_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	cardlayout.show(cardpanel, "order3");
            }
        });
        or3_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	cardlayout.show(cardpanel, "order4");
            }
        });
        or3_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	cardlayout.show(cardpanel, "order5");
            }
        });
        vegetable3_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	cheeseball();
            }
        });  
        vegetable3_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	egg();
            }
        });
        vegetable3_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	fried();
            }
        });
        vegetable3_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	hotdog();
            }
        });
        vegetable3_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	chicken();
            }
        });
        vegetable3_6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	lemonchicken();
            }
        });
        vegetable3_7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	friedcookie();
            }
        });
        vegetable3_8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	franchtoast();
            }
        });
        vegetable3_9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	omegg();
            }
        });
        vegetable3_10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ham();
            }
        });
        check3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	checkoutbox(); // 購物車
            }
        });
        
        // orderpanel4
        JPanel orderbutton4 = new JPanel();
        JPanel ordermenu4 = new JPanel();
        ordermenu4.setLayout(new GridLayout(5, 2)); // You can adjust the number of rows and columns
        
        JButton or4_1, or4_2, or4_3, or4_4, or4_5;
        or4_1 = new JButton("飲料");
        or4_2 = new JButton("中式餐點");
        or4_3 = new JButton("點心");
        or4_4 = new JButton("飯、麵");
        or4_5 = new JButton("食光套餐");
        // Add other buttons and ActionListeners similarly
        orderbutton4.add(or4_1);
        orderbutton4.add(or4_2);
        orderbutton4.add(or4_3);
        orderbutton4.add(or4_4);
        orderbutton4.add(or4_5);

        JButton vegetable4_1, vegetable4_2, vegetable4_3, vegetable4_4, vegetable4_5, vegetable4_6, vegetable4_7, vegetable4_8, vegetable4_9, vegetable4_10;
        vegetable4_1 = new JButton("豬排烏龍麵 $85");
        vegetable4_2 = new JButton("鐵板麵 $50");
        vegetable4_3 = new JButton("三杯雞 $80");
        vegetable4_4 = new JButton("宮保雞 $80");
        vegetable4_5 = new JButton("打拋豬 $80");
        vegetable4_6 = new JButton("麻油雞 $85");
        vegetable4_7 = new JButton("塔香肉醬 $85");
        vegetable4_8 = new JButton("咖哩雞 $85");
        vegetable4_9 = new JButton("");
        vegetable4_10 = new JButton("");
        // Add ActionListener to handle button clicks for vegetable buttons
        ordermenu4.add(vegetable4_1);
        ordermenu4.add(vegetable4_2);
        ordermenu4.add(vegetable4_3);
        ordermenu4.add(vegetable4_4);
        ordermenu4.add(vegetable4_5);
        ordermenu4.add(vegetable4_6);
        ordermenu4.add(vegetable4_7);
        ordermenu4.add(vegetable4_8);
        ordermenu4.add(vegetable4_9);
        ordermenu4.add(vegetable4_10);
        // 購物車按鈕
        JPanel checkpanel4 = new JPanel();
        JButton check4 = new JButton("進入購物車");
        checkpanel4.add(check4);
        frame.add(checkpanel4);
        orderpanel4.add(checkpanel4, BorderLayout.SOUTH);
        // 添加orderpanel4排版
        orderpanel4.add(orderbutton4, BorderLayout.NORTH);
        orderpanel4.add(ordermenu4, BorderLayout.CENTER);
        // orderpanel4所有按鈕設置傾聽
        or4_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	cardlayout.show(cardpanel, "order1");
            }
        });       
        or4_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	cardlayout.show(cardpanel, "order2");
            }
        });
        or4_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	cardlayout.show(cardpanel, "order3");
            }
        });
        or4_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	cardlayout.show(cardpanel, "order4");
            }
        });
        or4_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	cardlayout.show(cardpanel, "order5");
            }
        }); 
        vegetable4_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	woolong();
            }
        }); 
        vegetable4_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	steelnoodle();
            }
        }); 
        vegetable4_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		threeglass();
        	}
        });
        vegetable4_4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		palace();
        	}
        });
        vegetable4_5.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		hitpig();
        	}
        });
        vegetable4_6.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		oilchicken();
        	}
        });
        vegetable4_7.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		meatsauce();
        	}
        });
        vegetable4_8.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		cali();
        	}
        });
        check4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	checkoutbox(); // 購物車
            }
        });
        
        // orderpanel5
        JPanel orderbutton5 = new JPanel();
        JPanel ordermenu5 = new JPanel();
        ordermenu5.setLayout(new GridLayout(5, 2)); // You can adjust the number of rows and columns
        
        JButton or5_1, or5_2, or5_3, or5_4, or5_5;
        or5_1 = new JButton("飲料");
        or5_2 = new JButton("中式餐點");
        or5_3 = new JButton("點心");
        or5_4 = new JButton("飯、麵");
        or5_5 = new JButton("食光套餐");
        // Add other buttons and ActionListeners similarly
        orderbutton5.add(or5_1);
        orderbutton5.add(or5_2);
        orderbutton5.add(or5_3);
        orderbutton5.add(or5_4);
        orderbutton5.add(or5_5);

        JButton vegetable5_1, vegetable5_2, vegetable5_3, vegetable5_4, vegetable5_5, vegetable5_6, vegetable5_7, vegetable5_8, vegetable5_9, vegetable5_10;
        vegetable5_1 = new JButton("兒童時光 $75");
        vegetable5_2 = new JButton("輕食時光 $85");
        vegetable5_3 = new JButton("遇見幸福 $85 ");
        vegetable5_4 = new JButton("法式茶旅 $90");
        vegetable5_5 = new JButton("活力滿滿 $90");
        vegetable5_6 = new JButton("千層蛋燒 $100");
        vegetable5_7 = new JButton("純牛漢堡排 $120");
        vegetable5_8 = new JButton("");
        vegetable5_9 = new JButton("");
        vegetable5_10 = new JButton("");
        // Add ActionListener to handle button clicks for vegetable buttons
        ordermenu5.add(vegetable5_1);
        ordermenu5.add(vegetable5_2);
        ordermenu5.add(vegetable5_3);
        ordermenu5.add(vegetable5_4);
        ordermenu5.add(vegetable5_5);
        ordermenu5.add(vegetable5_6);
        ordermenu5.add(vegetable5_7);
        ordermenu5.add(vegetable5_8);
        ordermenu5.add(vegetable5_9);
        ordermenu5.add(vegetable5_10);
        // 購物車按鈕
        JPanel checkpanel5 = new JPanel();
        JButton check5 = new JButton("進入購物車");
        checkpanel5.add(check5);
        frame.add(checkpanel5);
        orderpanel5.add(checkpanel5, BorderLayout.SOUTH);
        // 添加orderpanel5排版
        orderpanel5.add(orderbutton5, BorderLayout.NORTH);
        orderpanel5.add(ordermenu5, BorderLayout.CENTER);
        // orderpanel5所有按鈕設置傾聽
        or5_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	cardlayout.show(cardpanel, "order1");
            }
        });       
        or5_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	cardlayout.show(cardpanel, "order2");
            }
        });
        or5_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	cardlayout.show(cardpanel, "order3");
            }
        });
        or5_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	cardlayout.show(cardpanel, "order4");
            }
        });
        or5_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	cardlayout.show(cardpanel, "order5");
            }
        });
        vegetable5_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		childtime();
        	}
        });
        vegetable5_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		easytime();
        	}
        });
        vegetable5_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		meetlove();
        	}
        });
        vegetable5_4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		francetea();
        	}
        });
        vegetable5_5.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		powerfull();
        	}
        });
        vegetable5_6.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		thousandegg();
        	}
        });
        vegetable5_7.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		beefhamber();
        	}
        });
        check5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	checkoutbox(); // 購物車
            }
        });
        
        // 將所有頁面加到cardpanel
        cardpanel.add(homepanel, "home");
        cardpanel.add(orderpanel1, "order1");
        cardpanel.add(orderpanel2, "order2");
        cardpanel.add(orderpanel3, "order3");
        cardpanel.add(orderpanel4, "order4");
        cardpanel.add(orderpanel5, "order5");
        frame.add(cardpanel);

        // 一開始顯示主頁面
        cardlayout.show(cardpanel, "home");

        frame.setVisible(true);
    }
    
    //購物車
    public static void checkoutbox() {
    	
    	JFrame frame = new JFrame("購物車");
    	JPanel panelup = new JPanel();
    	JLabel label = new JLabel("購物車");
    	label.setFont(new Font("新細明體", Font.PLAIN, 24));
    	frame.add(panelup, BorderLayout.NORTH);
    	panelup.add(label);
    	frame.setSize(300,400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
   	
    	//JPanel panelcenter = new JPanel();
        DefaultListModel <String> lists = new DefaultListModel<>();
        JList <String> list = new JList<>(lists);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        for (int i = 0; i<items.size(); i++) {
        	if (items.get(i) != null) {
        		lists.addElement(items.get(i)+"數量"+qtys.get(i)+"份          \t金額："+cashes.get(i));
        	}
        }

        frame.add(list, BorderLayout.CENTER);
              
        JPanel paneldown = new JPanel();
        JButton delete = new JButton("刪除項目");
        JButton enter = new JButton("結帳");
        paneldown.add(delete);
        paneldown.add(enter);
        frame.add(paneldown, BorderLayout.SOUTH);
        
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	int selectedIndex = list.getSelectedIndex();
                if (selectedIndex != -1) {
                	           
                	lists.remove(selectedIndex);
                    items.remove(selectedIndex);
                    qtys.remove(selectedIndex);
                    cashes.remove(selectedIndex);
                    updateTotal();

                }
            }
        });

        enter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
                StringBuilder message = new StringBuilder("嗨，"+selectedNumber+"號桌！\n"+"選擇的商品：\n");
                
                for (int i = 0; i <items.size(); i++) {
                	if (items.get(i) != null) {
                		message.append(items.get(i)).append(qtys.get(i)+"份").append("\n");
                	}
                }

                message.append("金額：").append(cashcash);
                JOptionPane.showMessageDialog(null, message.toString(), "商品清單", JOptionPane.INFORMATION_MESSAGE);

                putindatabase();
//                final String USER = "JOJOJ";
//                final String PASS = "22402125";
//                
//                Connection conn = null;
//                Statement stmt = null;
//                
//                conn = DriverManager.getConnection(DB_URL,USER,PASS);
//                
//                String sql = "INSERT INTO menu1 (id, item, qty, cash) VALUES ("1", "可樂",   2 , 100)";
//                stmt = conn.createStatement();
//                stmt.executeUpdate(sql);
//                
//                System.out.println("点餐数据新增完成！");
                
//	            try {
//	            	
//	                //Class.forName(JDBC_DRIVER);
//                               
//	                Connection conn = null;
//	                Statement stmt = null;
//	                
//	                final String USER = "JOJOJ";
//	                final String PASS = "22402125";
//	                
//	                conn = DriverManager.getConnection(DB_URL,USER,PASS);
//	                
//	                String sql = "INSERT INTO menu1 (id, item, qty, total_cash) VALUES (" + selectedNumber + ", '" + items.get(0) + "', " + qtys.get(0) + ", " + cashcash + ")";
//	                //String sql = "INSERT INTO menu1 (id, item, qty, total_cash) VALUES (" 12 ", '可樂', " 2 ", " 35 ")";
//	                
//	                stmt = conn.createStatement();
//	                stmt.execute(sql);
//	                
//	                conn.close();
//	                System.out.println("点餐数据新增完成！");
//	                
//	            } catch (SQLException ex) {
//	                ex.printStackTrace();
//	                JOptionPane.showMessageDialog(null, "点餐数据新增失败！", "错误", JOptionPane.ERROR_MESSAGE);
//	            }          
                
	        }
        });
    
    	
    	frame.setVisible(true);
    }
    
    // 當修改購物車後更新總價方法
    public static void updateTotal() {
        cashcash = 0;
        for (int i = 0; i < items.size(); i++) {
            cashcash += qtys.get(i) * (cashes.get(i)/qtys.get(i));
        }
    } 
    static void putindatabase () {
    	Connection conn = null;
        //Statement stmt = null;
        PreparedStatement pstmt = null;
        
        try {
            // 載入MySQL驅動程序
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 連接到資料庫
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testdb3?serverTimezone=UTC", "root", "22402125");

            //stmt = conn.createStatement();
            //String sql = "INSERT INTO menu1 (id, item, qty, cash) VALUES (selectedNumber, items, qtys, cashcash)";
//            String sql = "CREATE TABLE IF NOT EXISTS menu2 " +
//                    "(table int(10), " +
//                    "item VARCHAR(20), " +
//                    "qty int(10), " +
//                    "cash int(10))";
            
            for (int i = 0; i < items.size(); i++) {
            
	            String sql = "INSERT INTO menu1 (id, item, qty, cash) VALUES (?, ?, ?, ?)";
	            pstmt = conn.prepareStatement(sql);
	            
	            // 設定參數值
	            pstmt.setString(1, selectedNumber);
	            pstmt.setString(2, items.get(i));
	            pstmt.setInt(3, qtys.get(i));
	            pstmt.setInt(4, cashes.get(i));
	            
	            // 執行INSERT
	            pstmt.executeUpdate();	            
            }

            System.out.println("資料創建成功!!");

        }
        catch (ClassNotFoundException | SQLException e) {
            System.err.println("資料庫連接失敗..." + e);
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("資料庫連線結束");
        }
    }
}