package com.will.mvvmplug.utils;

import com.intellij.openapi.ui.Messages;
import com.will.mvvmplug.Const;

public class EditNameUtil {

    /**
     * 把layoutName -> Binding Name
     *
     * @param layoutName
     * @return
     */
    public static String autoBindingName(String layoutName) {
        //首字母大写
        String bindingName = MatchToolUtil.lineToHump(layoutName.replace(".xml", "")) + "Binding";
        bindingName = MatchToolUtil.upperFirstLatter(bindingName);
        return bindingName;
    }

    public static boolean interruptPath(String path) {
        if (path.contains("src/main/java")) {
            String pathTarget = path.replace("sui","").split("src/main/java")[1];
            if (pathTarget.contains(Const.UI) || pathTarget.contains(Const.REPOSITORY)) {
                Messages.showDialog("当前路径："+path+"，右键new目录不对,请移步到 ui 上一级", "警告", new String[]{}, 0, Messages.getQuestionIcon());
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    public static boolean interruptPathDrawable(String path) {
        if (path.contains("src/main/res/drawable")) {
            return false;
        } else {
            return true;
        }
    }
}
