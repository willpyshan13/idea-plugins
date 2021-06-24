package com.will.mvvmplug.menu;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.WindowManager;
import com.will.mvvmplug.utils.EditNameUtil;

public class MvvmDialogFragmentAction extends AnAction {
    final String title = "Create MVVM DialogFragment";


    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {

        VirtualFile selectGroup = DataKeys.VIRTUAL_FILE.getData(anActionEvent.getDataContext());
        if (EditNameUtil.interruptPath(selectGroup.getPath())) {
            return;
        }

        MvvmDialogFragmentDialog findViewDialog = new MvvmDialogFragmentDialog(anActionEvent);
        findViewDialog.setTitle(title);
        findViewDialog.pack();
        findViewDialog.setLocationRelativeTo(WindowManager.getInstance().getFrame(anActionEvent.getProject()));
        findViewDialog.setVisible(true);
    }
}
