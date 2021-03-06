package com.will.mvvmplug.menu;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.will.mvvmplug.Const;
import com.will.mvvmplug.entity.ClassNameBean;
import com.will.mvvmplug.utils.EditNameUtil;
import com.will.mvvmplug.utils.MatchToolUtil;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MvvmDialogFragmentDialog extends JDialog {
    private JTextField editClassName;
    private JTextField editActivityName;
    private JTextField editBindingName;
    private JButton okButton;
    private JButton cancelButton;
    private JPanel rootPanel;
    private JTextField editLayoutName;
    private JCheckBox checkBoxModuleAuto;
    private JLabel labelModuleName;
    private JTextField editViewModelName;
    private JTextField editRepositoryName;
    private JTextField editComment;
    private JTextField editAuthor;

    CreateMvvmFile mvvmFile = new CreateMvvmFile();

    public MvvmDialogFragmentDialog(final AnActionEvent anActionEvent) {
        setContentPane(rootPanel);
        setModal(true);
        mvvmFile.actionPerformed(anActionEvent);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        editClassName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                autoFill();
            }
        });

        editAuthor.setText(PropertiesComponent.getInstance().getValue(Const.KEY_AUTHOR));
        editAuthor.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyPressed(e);
                PropertiesComponent.getInstance().setValue(Const.KEY_AUTHOR, editAuthor.getText());
            }
        });

        initView();

        checkBoxModuleAuto.setSelected(PropertiesComponent.getInstance().getBoolean(Const.KEY_IS_MODULE, true));
        checkBoxModuleAuto.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                PropertiesComponent.getInstance().setValue(Const.KEY_IS_MODULE, checkBoxModuleAuto.isSelected());
                autoFill();
            }
        });

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createMvvmFile(anActionEvent);
                dispose();
            }
        });

        editLayoutName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                autoBindingLayout();
            }
        });
    }

    private void createMvvmFile(AnActionEvent anActionEvent) {

        ClassNameBean names = new ClassNameBean();
        names.isFragmentDialog = true;
        names.className = editClassName.getText();
        names.layoutName = editLayoutName.getText();
        names.contentName = editActivityName.getText();
        names.repositoryName = editRepositoryName.getText();
        names.vmName = editViewModelName.getText();
        names.createOtherParams(anActionEvent);
        names.comment = editComment.getText();
        names.author = editAuthor.getText();
        names.isNeedContract = false;
        names.moduleName = labelModuleName.getText();
        mvvmFile.createClassFile(names);
    }

    private void initView() {
        editClassName.setText("SubDemo");
        autoFill();
    }

    /**
     * ????????????
     */
    private void autoFill() {

        String className = editClassName.getText();

        //?????????????????????????????????????????????
        String moduleName = "";

        if (checkBoxModuleAuto.isSelected()) {
            String lineClassName = MatchToolUtil.humpToLineWithFirstChar(className);
            if (lineClassName.contains(Const.UNDER_LINE)) {
                moduleName = lineClassName.split(Const.UNDER_LINE)[0];
                className = className.replace(moduleName, "");
            }
            labelModuleName.setVisible(true);
            labelModuleName.setText(moduleName);
        } else {
            labelModuleName.setText("");
            labelModuleName.setVisible(false);
        }

        String activityName = moduleName + className + "DialogFragment";
        String layoutName = (moduleName + "_Dialog_" + MatchToolUtil.humpToLine2(className) + ".xml").toLowerCase();
        if (layoutName.startsWith(Const.UNDER_LINE)) {
            layoutName = layoutName.substring(1);
        }


        String repositoryName = moduleName + className + "Repository";
        //ViewModel??????
        String vmName = moduleName + className + "ViewModel";

        editActivityName.setText(activityName);
        editBindingName.setText(activityName);
        editLayoutName.setText(layoutName);
        editBindingName.setText(EditNameUtil.autoBindingName(layoutName));
        editRepositoryName.setText(repositoryName);
        editViewModelName.setText(vmName);
    }

    private void autoBindingLayout() {
        String layoutName = editLayoutName.getText();
        String bindingName = EditNameUtil.autoBindingName(layoutName);
        editBindingName.setText(bindingName);
    }
}