package com.will.mvvmplug.entity;


import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.will.mvvmplug.Const;
import com.will.mvvmplug.utils.FileUtil;
import com.will.mvvmplug.utils.LogUtil;


public class ClassNameBean {

    /**
     * 包名
     */
    public String packageName = "";

    /**
     * 类名
     */
    public String className = "";

    /**
     * 契约名字
     */
    public String contractName = "";

    /**
     * ViewModel名字
     */
    public String vmName = "";

    /**
     * viewModel item d的名字
     */
    public String vmItemName = "";

    /**
     * 数据仓库名字
     */
    public String repositoryName = "";
    /**
     * activity 名字,activty/fragment/dialog
     */
    public String contentName = "";
    /**
     * layout 名字
     */
    public String layoutName = "";

    /**
     * 是否需要契约
     */
    public boolean isNeedContract = false;

    /**
     * 作者
     */
    public String author = "xxx";

    /**
     * 注释
     */
    public String comment = "xxxx";

    /**
     * 是否是activity
     */
    public boolean isActivity = false;

    /**
     * recycle view activity
     */
    public boolean isRecycleActiivty = false;

    /**
     * 是否是fragment
     */
    public boolean isFragment = false;

    /**
     * 是否是dialog
     */
    public boolean isFragmentDialog = false;

    /**
     * 工程名字
     */
    public String projectName = "";

    /**
     * 各个路径名字
     */
    public ClassPathBean pathBean;

    /**
     * 是否是kotlin
     */
    public boolean isKt = true;

    /**
     * 模块名
     */
    public String moduleName = "";
    public String serviceName = "";

    /**
     * 组合参数
     *
     * @param e
     */
    public void createOtherParams(AnActionEvent e) {
        Project project = e.getProject();
        VirtualFile selectGroup = DataKeys.VIRTUAL_FILE.getData(e.getDataContext());
        String path = selectGroup.getPath() + "/";

        packageName = path.replace("/", ".");
        LogUtil.log(packageName);
        projectName = project.getName();
        packageName = packageName.split("src.main.java.")[1];
        if (packageName.endsWith(Const.DOT)) {
            packageName = packageName.substring(0, packageName.length() - 1);
        }

        contractName = className + "Contract";

        FileUtil.traverseFolder(path);
        //首字母大写
        this.className = className.substring(0, 1).toUpperCase() + className.substring(1);
        pathBean = new ClassPathBean(this, path);

        if (!moduleName.isEmpty()) {
            serviceName = moduleName + "Service";
        }
    }
}
