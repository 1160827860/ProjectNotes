package com.lzy.projectnotes;

import com.intellij.openapi.components.*;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.util.xmlb.XmlSerializerUtil;
import com.intellij.util.xmlb.annotations.OptionTag;
import com.lzy.projectnotes.data.ProjectData;
import com.lzy.projectnotes.util.ListConverter;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.LinkedHashMap;
import java.util.List;

@State(
        name = "Configuration",
        storages = {@Storage("$APP_CONFIG$/ProjectNotes-settings.xml")}
)
public class Configuration implements ApplicationComponent, Configurable,PersistentStateComponent<Configuration> {

    public String language;
    @OptionTag(converter = ListConverter.class)
    public List<ProjectData> data;

    @Nullable
    @Override
    public Configuration getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull Configuration configuration) {
        XmlSerializerUtil.copyBean(configuration,this);
    }


    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return null;
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        return null;
    }

    @Override
    public boolean isModified() {
        return false;
    }

    @Override
    public void apply() throws ConfigurationException {

    }

}
