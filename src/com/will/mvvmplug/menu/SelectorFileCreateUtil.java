package com.will.mvvmplug.menu;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.vfs.VirtualFile;
import com.will.mvvmplug.utils.FileUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SelectorFileCreateUtil {

    public void makeSelectorFile(AnActionEvent e, SelectorFileName selectorFileName) {
        VirtualFile selectGroup = DataKeys.VIRTUAL_FILE.getData(e.getDataContext());
        final String path = selectGroup.getPath() + "/";

        if (selectorFileName.selectorName != null && !selectorFileName.selectorName.isEmpty()) {
            dealSelectorFile(selectorFileName, "selector", "common_btn_orange_selector.xml", path, selectorFileName.selectorName);
        }
        if (selectorFileName.selectorNName != null && !selectorFileName.selectorNName.isEmpty()) {
            dealFileContent("selector", "common_btn_orange_n.xml", path, selectorFileName.selectorNName);
        }
        if (selectorFileName.selectorPName != null && !selectorFileName.selectorPName.isEmpty()) {
            dealFileContent("selector", "common_btn_orange_p.xml", path, selectorFileName.selectorPName);
        }
        //是否需要 disable
        if (selectorFileName.selectorDisableName != null && !selectorFileName.selectorDisableName.isEmpty()) {
            dealFileContent("selector", "common_btn_orange_disable.xml", path, selectorFileName.selectorDisableName);
        }

        e.getProject().getBaseDir().refresh(false, true);
    }

    public void dealSelectorFile(SelectorFileName selectorFileName, String inDir, String inFilename, String outFilepath, String outFilename) {

        String content = FileUtil.readFile(inDir, inFilename);

        String target = content.replace("${pressName}", selectorFileName.selectorPName.split(".xml")[0])
                .replace("${normalName}", selectorFileName.selectorNName.split(".xml")[0]);

        if (!StringUtils.isEmpty(selectorFileName.selectorDisableName)) {
            target = target.replace("${disableName}", selectorFileName.selectorDisableName.split(".xml")[0]);
        }

        writeToFile(target, outFilepath, outFilename);

        //是否需要disable
        if (StringUtils.isEmpty(selectorFileName.selectorDisableName)) {
            //不需要disable 这句

            try {
                File file = new File(outFilepath + outFilename);
                List<String> list = FileUtils.readLines(file, "UTF-8");
                for (int i = list.size() - 1; i >= 0; i--) {
                    if (list.get(i).contains("android:state_enabled=\"false\"")) {
                        list.remove(i);
                    }
                }

                FileUtils.writeLines(file, list);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * 替换部分
     *
     * @param content
     * @return
     */
    public String commonReplace(String content) {
        return content;
    }

    private void dealFileContent(String inDir, String inFilename, String outFilepath, String outFilename) {
        String viewModel = FileUtil.readFile(inDir, inFilename);
        writeToFile(viewModel, outFilepath, outFilename);
    }


    private void writeToFile(String content, String filepath, String filename) {
        if (content.isEmpty()) {
            return;
        }
        FileUtil.writetoFile(commonReplace(content), filepath, filename);
    }

    static public class SelectorFileName {
        String selectorName;
        String selectorPName;
        String selectorNName;
        String selectorDisableName = null;
    }

}
