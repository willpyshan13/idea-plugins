package com.will.mvvmplug.menu;

import com.intellij.openapi.actionSystem.AnActionEvent;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SelectorDialog extends JDialog {
    private JTextField editSelecor;
    private JTextField editPress;
    private JTextField editNormal;

    private JButton okButton;
    private JButton cancelButton;
    private JPanel rootPanel;
    private JTextField editDisbale;
    private JCheckBox chbDisable;
    private JCheckBox chbNormal;
    private JCheckBox chbPress;
    private JCheckBox chbSelector;

    private final static String SELECTOR = "selector.xml";
    private SelectorFileCreateUtil createSelectorFile = new SelectorFileCreateUtil();

    public SelectorDialog(final AnActionEvent anActionEvent) {

        setContentPane(rootPanel);
        setModal(true);

        editSelecor.setText("sub_btn_orange_selector.xml");
        editSelecor.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyPressed(e);
                autoName();
            }
        });
        autoName();
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectorFileCreateUtil.SelectorFileName name = new SelectorFileCreateUtil.SelectorFileName();
                name.selectorName = editSelecor.getText();
                name.selectorNName = editNormal.getText();
                name.selectorPName = editPress.getText();
                if (chbDisable.isSelected()) {
                    name.selectorDisableName = editDisbale.getText();
                } else {
                    name.selectorDisableName = null;
                }
                if (chbNormal.isSelected()) {
                    name.selectorNName = editNormal.getText();
                } else {
                    name.selectorNName = null;
                }
                if (chbPress.isSelected()) {
                    name.selectorPName = editPress.getText();
                } else {
                    name.selectorPName = null;
                }
                if (chbSelector.isSelected()) {
                    name.selectorName = editSelecor.getText();
                } else {
                    name.selectorName = null;
                }
                createSelectorFile.makeSelectorFile(anActionEvent, name);
                dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        chbDisable.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                editDisbale.setEnabled(chbDisable.isSelected());
            }
        });
        chbNormal.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                editNormal.setEnabled(chbNormal.isSelected());
            }
        });
        chbPress.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                editPress.setEnabled(chbPress.isSelected());
            }
        });
        chbSelector.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                editSelecor.setEnabled(chbSelector.isSelected());
            }
        });

        editDisbale.setEnabled(chbDisable.isSelected());
    }


    private void autoName() {

        String selector = editSelecor.getText();
        if (selector.endsWith(SELECTOR)) {
            String p = selector.replace("selector", "p");
            String n = selector.replace("selector", "n");
            String disable = selector.replace("selector", "disable");
            editPress.setText(p);
            editNormal.setText(n);
            editDisbale.setText(disable);
        }

    }
}
