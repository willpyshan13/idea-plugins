package com.will.mvvmplug.menu;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.will.mvvmplug.Const;
import com.will.mvvmplug.entity.ClassNameBean;
import com.will.mvvmplug.entity.FileParamsBean;
import com.will.mvvmplug.utils.DateUtil;
import com.will.mvvmplug.utils.FileUtil;
import com.will.mvvmplug.utils.MatchToolUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CreateMvvmFile {
    private Project project;

    public void actionPerformed(AnActionEvent e) {
        project = e.getProject();
    }

    /**
     * 创建MVVM架构
     */
    public void createClassFile(ClassNameBean names) {
        createActivityFiles(names);
        createFragmentFiles(names);
        createFragmentDialogFiles(names);
        createCommonServiceFile(names);
        createRecycleViewActivity(names);
        refreshRootDir();
    }

    private void createActivityFiles(ClassNameBean names) {
        if (!names.isActivity) {
            return;
        }

        if (names.isNeedContract) {
            dealFileContent(names, "contract", "VVMVVMContract.kt.ftl", names.pathBean.contractPath, names.contractName + ".kt");
            dealFileContent(names, "contract", "VVMVVMBaseModel.kt.ftl", names.pathBean.viewModelPath, names.vmName + ".kt");
            dealFileContent(names, "contract", "VVMVVMRepository.kt.ftl", names.pathBean.repositoryPath, names.repositoryName + ".kt");
        } else {
            dealFileContent(names, "contractno", "VVMVVMBaseModel.kt.ftl", names.pathBean.viewModelPath, names.vmName + ".kt");
            dealFileContent(names, "contractno", "VVMVVMRepository.kt.ftl", names.pathBean.repositoryPath, names.repositoryName + ".kt");
        }

        dealFileContent(names, "activity", "VVMVVMActivity.kt.ftl", names.pathBean.activityPath, names.contentName + ".kt");
        dealFileContent(names, "activity", "activity_main.xml.ftl", names.pathBean.layoutPath, names.layoutName);

        //AndroidManifest.xml
        insertMainFest(names, names.pathBean.mainPath);
    }


    private void createFragmentFiles(ClassNameBean names) {
        if (!names.isFragment) {
            return;
        }
        dealFileContent(names, "contractno", "VVMVVMBaseModel.kt.ftl", names.pathBean.viewModelPath, names.vmName + ".kt");
        dealFileContent(names, "contractno", "VVMVVMRepository.kt.ftl", names.pathBean.repositoryPath, names.repositoryName + ".kt");
        dealFileContent(names, "fragment", "MVVMFragment.kt.ftl", names.pathBean.fragmentPath, names.contentName + ".kt");
        dealFileContent(names, "fragment", "fragment_main.xml.ftl", names.pathBean.layoutPath, names.layoutName);
    }

    private void createFragmentDialogFiles(ClassNameBean names) {
        if (!names.isFragmentDialog) {
            return;
        }
        dealFileContent(names, "dialog", "VVBaseDialogViewModel.kt.ftl", names.pathBean.viewModelPath, names.vmName + ".kt");
        dealFileContent(names, "contractno", "VVMVVMRepository.kt.ftl", names.pathBean.repositoryPath, names.repositoryName + ".kt");
        dealFileContent(names, "dialog", "ReserveInfoPickerDialog.kt.ftl", names.pathBean.dialogPath, names.contentName + ".kt");
        dealFileContent(names, "fragment", "fragment_main.xml.ftl", names.pathBean.layoutPath, names.layoutName);
    }

    /**
     * 创建http service
     *
     * @param names
     */
    private void createCommonServiceFile(ClassNameBean names) {
        File file1 = new File(names.pathBean.rootPath + "/service", names.serviceName + ".kt");
        File file2 = new File(names.pathBean.rootPath + "/service", names.serviceName + ".java");
        if (!file1.exists() && !file2.exists()) {
            dealFileContent(names, "service", "Service.kt.ftl", names.pathBean.servicePath, names.serviceName + ".kt");
        }
    }

    /**
     * recycleView Activity 创建
     *
     * @param names
     */
    private void createRecycleViewActivity(ClassNameBean names) {
        if (!names.isRecycleActiivty) {
            return;
        }
        dealFileContent(names, "activityrecycler", "ListActivity.kt.ftl", names.pathBean.activityPath, names.contentName + ".kt");
        dealFileContent(names, "activityrecycler", "ListViewModel.kt.ftl", names.pathBean.viewModelPath, names.vmName + ".kt");
        dealFileContent(names, "activityrecycler", "ListItemViewModel.kt.ftl", names.pathBean.viewModelItemPath, names.vmItemName + ".kt");
        dealFileContent(names, "contractno", "VVMVVMRepository.kt.ftl", names.pathBean.repositoryPath, names.repositoryName + ".kt");

        if (!Const.LIST_XML_NAME.equals(names.layoutName)) {
            dealFileContent(names, "activity", "activity_main.xml.ftl", names.pathBean.layoutPath, names.layoutName);
        }
        //AndroidManifest.xml
        insertMainFest(names, names.pathBean.mainPath);
    }

    /**
     * 插入AndroidManifest.xml 一行
     *
     * @param mainPath
     */
    private void insertMainFest(ClassNameBean names, String mainPath) {
        if (!(names.isActivity || names.isRecycleActiivty)) {
            return;
        }
        try {
            File file = new File(mainPath + "/AndroidManifest.xml");
            if (!file.exists()) {
                String fileStr = FileUtil.readFile("activity", "AndroidManifest.xml.ftl");
                String targetStr = fileStr.replace("${packageName}", names.packageName)
                        .replace("${activityClass}", names.contentName);
                FileUtils.writeStringToFile(file, targetStr, "UTF-8");
                return;
            }

            List<String> listStr = FileUtils.readLines(file, "UTF-8");
            int size = listStr.size();
            for (int i = 0; i < size; i++) {
                String str = listStr.get(size - 1 - i);
                //找到application 最后一行，在前面插入
                if (str.contains("</application>")) {
                    int index = size - 1 - i;

                    String insertStr = "\t\t<activity android:name=\"${packageName}.ui.activity.${activityClass}\"/>";
                    String targetStr = insertStr.replace("${packageName}", names.packageName)
                            .replace("${activityClass}", names.contentName);
                    listStr.add(index, targetStr);
                    break;
                }
            }
            FileUtils.writeLines(file, "UTF-8", listStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void dealFileContent(FileParamsBean paramsBean) {
        dealFileContent(paramsBean.names, paramsBean.inDir, paramsBean.inFilename, paramsBean.outFilepath, paramsBean.outFilename);
    }

    private void dealFileContent(ClassNameBean names, String inDir, String inFilename, String outFilepath, String outFilename) {
        if (!names.isKt) {
            inFilename = inFilename.replace(".kt.", ".java.");
            outFilename = outFilename.replace(".kt", ".java");
        }

        String viewModel = FileUtil.readFile(inDir, inFilename);
        writetoFile(names, viewModel, outFilepath, outFilename);
    }

    private void refreshRootDir() {
        project.getBaseDir().refresh(false, true);
    }

    private String commonReplace(String content, ClassNameBean names) {
        //注释部分
        String comment = FileUtil.readFile("", "globals.xml.ftl");
        comment = comment.replace("${date}", DateUtil.getCurrentTimeStr())
                .replace("${commentName}", names.comment)
                .replace("${author}", names.author);
        comment = comment.split("</#macro>")[0];

        //各种名字替换部分
        String target = content
                .replace("<@gb.fileHeader />", comment)
                .replace("${packageName}", names.packageName)
                .replace("${viewmodelName}", names.vmName)
                .replace("${repositoryPackageName}", names.repositoryName)
                .replace("${activityClass}", names.contentName)
                .replace("${serviceName}", names.serviceName)
                .replace("${vmItemName}", names.vmItemName)
                .replace("${layoutName}", names.layoutName.replace(".xml", ""));

        //binding 部分
        String binding = names.layoutName.split(".xml")[0];
        target = target.replace("${baseBinding}", MatchToolUtil.lineToHumpFirstChar(binding) + "Binding");

        //契约部分 如果有的话
        target = target.replace("${contractPackageName}", names.contractName);

        return target;
    }

    private void writetoFile(ClassNameBean names, String content, String filepath, String filename) {
        if (content.isEmpty()) {
            return;
        }
        FileUtil.writetoFile(commonReplace(content, names), filepath, filename);
    }
}
