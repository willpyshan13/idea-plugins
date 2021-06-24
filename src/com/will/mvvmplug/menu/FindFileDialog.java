package com.will.mvvmplug.menu;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.will.mvvmplug.filesize.FileLogic;
import com.will.mvvmplug.utils.NumberDocument;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindFileDialog extends JDialog {
    private JTextField editMaxValue;
    private JButton okButton;
    private JButton cancelButton;
    private JPanel rootPanel;
    private JTextArea textAreaFile;
    private JTextArea textAreaSame;
    String path;
    String projectName;

    FindFileDialog(final AnActionEvent anActionEvent) {
        setContentPane(rootPanel);
        setModal(true);

        path = anActionEvent.getProject().getBasePath();
        projectName = anActionEvent.getProject().getName();
        initView();
    }

    void initView() {
        editMaxValue.setText(1 + "");
        editMaxValue.setDocument(new NumberDocument());
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                FileLogic fileLogic = new FileLogic(Integer.valueOf(editMaxValue.getText()) * 1000);
                FileLogic.FilterData filterData = fileLogic.printFile(path, projectName);
                //先把路径斜杠换一边不然replace 不成功
                textAreaFile.setText(filterData.fileStrBuilder.toString());
                textAreaSame.setText(filterData.sameStrBuilder.toString());
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
