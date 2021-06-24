package com.will.mvvmplug.menu;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.wm.WindowManager;

/**
 * Description: AndroidMvpAction需要在“plugin.xml”注册
 *
 * @author : djs
 * Date: 2019/5/28.
 */
public class ToolsFindFileAction extends AnAction {
    final String title = "find file";


    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {

        FindFileDialog findViewDialog = new FindFileDialog(anActionEvent);
        findViewDialog.setTitle(title);
        findViewDialog.pack();
        findViewDialog.setLocationRelativeTo(WindowManager.getInstance().getFrame(anActionEvent.getProject()));
        findViewDialog.setVisible(true);

    }
}
