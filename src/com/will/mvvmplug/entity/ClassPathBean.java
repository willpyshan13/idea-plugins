package com.will.mvvmplug.entity;


import com.will.mvvmplug.utils.PathUtil;

public class ClassPathBean {

    /**
     * view model 路径
     */
    public String viewModelPath;
    /**
     * viewModel item
     */
    public String viewModelItemPath;
    /**
     * activity
     */
    public String activityPath;
    /**
     * fragment
     */
    public String fragmentPath;
    /**
     * dialog path
     */
    public String dialogPath;
    /**
     * 数据仓
     */
    public String repositoryPath;
    /**
     * 契约
     */
    public String contractPath;
    public String mainPath;
    /**
     * 布局
     */
    public String layoutPath;

    public String rootPath;
    /**
     * http 服务路径
     */
    public String servicePath;

    ClassPathBean(ClassNameBean bean, String rootPath) {
        this.rootPath = rootPath;
        viewModelPath = findAndMkDir(rootPath, "ui/viewmodel");
        viewModelItemPath = findAndMkDir(rootPath, "ui/viewmodel");
        if (bean.isActivity || bean.isRecycleActiivty) {
            activityPath = findAndMkDir(rootPath, "ui/activity");
        }
        if (bean.isFragment) {
            fragmentPath = findAndMkDir(rootPath, "ui/fragment");
        }
        if (bean.isFragmentDialog) {
            dialogPath = findAndMkDir(rootPath, "ui/fragment");
        }

        repositoryPath = findAndMkDir(rootPath, "repository");
        if (bean.isNeedContract) {
            contractPath = findAndMkDir(rootPath, "repository/contract");
        }
        mainPath = findAndMkDir(rootPath, "main");
        layoutPath = findAndMkDir(mainPath, "res/layout");

        servicePath = findAndMkDir(rootPath, "service");
    }

    private String findAndMkDir(String rootPath, String dir) {
        return PathUtil.findAndMkDir(rootPath, dir);
    }

}
