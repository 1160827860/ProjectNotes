package com.lzy.projectnotes.util;

import b.a.n.T;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.intellij.util.xml.ConvertContext;
import com.intellij.util.xmlb.Converter;
import com.lzy.projectnotes.data.ProjectData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ListConverter extends Converter {
    @Nullable
    @Override
    public List<ProjectData> fromString(@NotNull String s) {
        return  JSON.parseObject(s,new TypeReference<List<ProjectData>>(){});
    }
    @Nullable
    @Override
    public String toString(@NotNull Object o) {
        return JSON.toJSONString(o);
    }
}
