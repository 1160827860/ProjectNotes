package com.lzy.projectnotes.ui;

import com.alibaba.fastjson.JSON;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.lzy.projectnotes.Configuration;
import com.lzy.projectnotes.data.ProjectData;
import com.lzy.projectnotes.data.ProjectDataCenter;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.intellij.openapi.components.PersistentStateComponent;
import com.lzy.projectnotes.util.DataConvert;

public class AddNoteWindow    {
    private JButton addProjectButton;
    private JTextArea projectDescrible;
    private JTextField projectPath;
    private JTextField projectName;
    public JPanel AddProjectWindows;
    private JPanel top;
    private JPanel down;
    private JLabel projectJlabel;
    private JLabel projectNameLabel;
    private JLabel projectPathLabel;
    private Configuration configuration = ServiceManager.getService(Configuration.class);
    JFrame frame = new JFrame("添加项目信息");

    private void init(Project project, ToolWindow toolWindow){
        projectName.setText(project.getName());
        projectPath.setText(project.getBasePath());
    }
    protected AddNoteWindow(Project project, ToolWindow toolWindow) {
        init(project,toolWindow);
        addProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProjectData newProject = new ProjectData(projectName.getText(),projectPath.getText(),projectDescrible.getText());
                ProjectDataCenter.DATA_LIST.add(newProject);
                ProjectDataCenter.TABLE_MODEL.addRow(DataConvert.convert(newProject,ProjectDataCenter.DATA_LIST.size()));
                configuration.data = ProjectDataCenter.DATA_LIST;
                configuration.loadState(configuration);
                frame.dispose();
            }
        });
    }

    public void show() {
        frame.setContentPane(AddProjectWindows);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setSize(600,500);
        frame.setPreferredSize(new Dimension(500,500));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
