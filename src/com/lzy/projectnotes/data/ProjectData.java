package com.lzy.projectnotes.data;

/**
 * @author lzy
 * @date 2020/4/22
 */
public class ProjectData {
    public  String prjectName;
    public  String projectPath;
    public  String prjectDescription;

    public ProjectData() {
    }

    public ProjectData(String prjectName, String projectPath, String prjectDescription) {
        this.prjectName = prjectName;
        this.projectPath = projectPath;
        this.prjectDescription = prjectDescription;
    }

    public String getPrjectName() {
        return prjectName;
    }

    public void setPrjectName(String prjectName) {
        this.prjectName = prjectName;
    }

    public String getProjectPath() {
        return projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }

    public String getPrjectDescription() {
        return prjectDescription;
    }

    public void setPrjectDescription(String prjectDescription) {
        this.prjectDescription = prjectDescription;
    }
}
