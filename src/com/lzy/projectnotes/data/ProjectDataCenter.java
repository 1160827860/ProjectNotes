package com.lzy.projectnotes.data;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.intellij.ide.util.PropertiesComponent;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lzy
 * @date 2020/4/22
 */
public class ProjectDataCenter {

    //一般不改变，查询更多
    public static List<ProjectData> DATA_LIST = new ArrayList<>();
    public static  String[] TABLE_HEAD = {"工程名","描述","项目路径"};
    public static DefaultTableModel TABLE_MODEL = new DefaultTableModel(null,TABLE_HEAD);


}
