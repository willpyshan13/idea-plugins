package com.will.mvvmplug.menu;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ToolsDialog extends JDialog {
    private JTextField editDecimalism;
    private JTextField editHex;
    private JTextField editColorPercent;
    private JTextField editColorHex;
    private JPanel rootPanel;


    ToolsDialog() {
        setContentPane(rootPanel);
        setModal(true);
        editDecimalism.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                auto2Hex();
            }
        });
        editHex.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                auto2Dec();
            }
        });
        editColorPercent.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                auto2ColorHex();
            }
        });
        editColorHex.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                auto2ColoeDec();
            }
        });
    }

    private void auto2Hex() {
        String decStr = editDecimalism.getText();
        try {
            Integer decInt = Integer.valueOf(decStr);
            String strHex = Integer.toHexString(decInt);
            editHex.setText(strHex);
        } catch (Exception e) {

        }
    }

    private void auto2Dec() {
        String hexStr = editHex.getText();
        try {
            Integer hex = Integer.parseInt(hexStr, 16);
            editDecimalism.setText(hex + "");
        } catch (Exception e) {

        }
    }

    private void auto2ColorHex() {
        String decStr = editColorPercent.getText();
        try {
            Integer decInt = Integer.valueOf(decStr);
            float percent = (float) (decInt * 256 / 100);
            String strHex = Integer.toHexString((int) percent);
            editColorHex.setText(strHex);
        } catch (Exception e) {

        }
    }

    private void auto2ColoeDec() {
        String hexStr = editColorHex.getText();
        try {
            Integer hex = Integer.parseInt(hexStr, 16);
            float target = (int) (hex * 100 / 256F);
            editColorPercent.setText(target + "");
        } catch (Exception e) {

        }
    }
}
