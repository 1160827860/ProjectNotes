package com.lzy.projectnotes.util;

import com.lzy.projectnotes.data.ProjectData;

import java.util.List;

public class DataConvert {
    public static Object[] convert(ProjectData projectData,int i){
        Object[] raw = new String[3];
        raw[0] = projectData.getPrjectName();
        raw[1] = projectData.getPrjectDescription();
        raw[2] = projectData.getProjectPath();
        return raw;
    }
    public static Object[][] convertDataList(List<ProjectData> list){
        Object[][] data = new Object[list.size()][3];
        for (int i = 0; i < list.size(); i++) {
            data[i][0] = list.get(i).getPrjectName();
            data[i][1] = list.get(i).getPrjectDescription();
            data[i][2] = list.get(i).getProjectPath();
        }
        return data;
    }
}
