package com.lzy.projectnotes.ui;

import com.alibaba.fastjson.JSON;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ToolWindow;
import com.lzy.projectnotes.Configuration;
import com.lzy.projectnotes.data.*;
import com.lzy.projectnotes.util.DataConvert;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class NoteListWindow {
    private JButton exportButton;
    private JButton closeButton;
    private   JTable record;
    private JTextField fileNameText;
    private JButton addButton;
    private JRadioButton englishButton;
    private JPanel contentPanel;
    private JRadioButton chineseButton;
    private JLabel name;
    private JLabel language;
    private JScrollPane projectList;
    private JPanel DataContent;
    private JButton delectButton;
    private JButton openButton;
    private JButton F5Button;
    private Configuration configuration;
    public void init(){
        configuration = ServiceManager.getService(Configuration.class);
        ProjectDataCenter.DATA_LIST = configuration.data;
        if(ProjectDataCenter.DATA_LIST.size() > 0){
            ProjectDataCenter.TABLE_MODEL = new DefaultTableModel(DataConvert.convertDataList(ProjectDataCenter.DATA_LIST),ProjectDataCenter.TABLE_HEAD);
        }
        String language = configuration.language;
        setLanguage(language);
        record.setModel(ProjectDataCenter.TABLE_MODEL);
        record.setEnabled(true);
    }
    private void setLanguage(String language){
        if(language == null){
            englishButton.setSelected(true);
        }else {
            if(language.equals("EN")){
                englishButton.setSelected(true);
                changeLanguageToEnglish();
            }else {
                chineseButton.setSelected(true);
                changeLanguageToChinese();
            }
        }
    }
    public NoteListWindow(Project project, ToolWindow toolWindow) {
        init();
        englishButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                configuration = ServiceManager.getService(Configuration.class);
             if(configuration == null){
                 configuration  = ServiceManager.getService(Configuration.class);
             }
             configuration.language = "EN";
             changeLanguageToEnglish();
             configuration.loadState(configuration);

            }
        });
        chineseButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                configuration = ServiceManager.getService(Configuration.class);
                if(configuration == null){
                    configuration  = ServiceManager.getService(Configuration.class);
                }
                configuration.language = "CN";
                configuration.loadState(configuration);
                changeLanguageToEnglish();

            }
        });

        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String fileName = fileNameText.getText();
                if(fileName == null){

                }
                VirtualFile virtualFile = FileChooser.chooseFile(FileChooserDescriptorFactory.createSingleFolderDescriptor(),project,project.getBaseDir());
                if(virtualFile != null){
                    String path = virtualFile.getPath();
                    String filePath = path+ File.separator+fileName+".md";

                }
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddNoteWindow addNoteWindow = new AddNoteWindow(project,toolWindow);
                addNoteWindow.show();
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toolWindow.hide(null);
            }
        });

        delectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                configuration = ServiceManager.getService(Configuration.class);
                int[] selRowIndexs = record.getSelectedRows();
                int row = selRowIndexs.length;
                if(row < 1){
                    JOptionPane.showMessageDialog(null,"请选择需要删除的记录","警告",JOptionPane.ERROR_MESSAGE);
                }else {
                    int index = selRowIndexs[0];
                    for (int i = 0; i < selRowIndexs.length; i++) {
                        ProjectDataCenter.TABLE_MODEL.removeRow(index);
                        ProjectDataCenter.DATA_LIST.remove(index);
                    }
                    configuration.data = ProjectDataCenter.DATA_LIST;
                    configuration.loadState(configuration);
                }
            }
        });
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selRowIndex = record.getSelectedRow();
                if(selRowIndex < 0){
                    JOptionPane.showMessageDialog(null,"未选择工程","警告",JOptionPane.ERROR_MESSAGE);
                }
                String projectPath = ProjectDataCenter.DATA_LIST.get(selRowIndex).getProjectPath();
                Runtime runtime = Runtime.getRuntime();
                if(projectPath == null){
                    JOptionPane.showMessageDialog(null,"工程路径为空","警告",JOptionPane.ERROR_MESSAGE);
                }
                try {
                    Process p = runtime.exec("idea64.exe "+projectPath);
                }catch (Exception f){
                    JOptionPane.showMessageDialog(null,"找不到文件目录","警告",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        F5Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProjectDataCenter.DATA_LIST = configuration.data;
                record.setModel(ProjectDataCenter.TABLE_MODEL);
                record.setEnabled(true);
            }
        });
    }


    public void changeLanguageToEnglish(){
        name.setText(EnglishMenu.NAME);
        language.setText(EnglishMenu.LANGUAGE);
        exportButton.setText(EnglishMenu.EXPORT_BUTTON);
        closeButton.setText(EnglishMenu.CLOSE_BUTTON);
        addButton.setText(EnglishMenu.ADD_BUTTON);
        projectList.setBorder(new TitledBorder(null,EnglishMenu.PROJECT_LIST));
        chineseButton.setText(EnglishMenu.CHINESE_BUTTON);
        englishButton.setText(EnglishMenu.ENLISH_BUTTON);
        ProjectDataCenter.TABLE_HEAD = EnglishMenu.TABLE_HEAD;
    }

    public void changeLanguageToChinese(){
        name.setText(ChineseMenu.NAME);
        language.setText(ChineseMenu.LANGUAGE);
        exportButton.setText(ChineseMenu.EXPORT_BUTTON);
        closeButton.setText(ChineseMenu.CLOSE_BUTTON);
        addButton.setText(ChineseMenu.ADD_BUTTON);
        projectList.setBorder(new TitledBorder(null,ChineseMenu.PROJECT_LIST));
        chineseButton.setText(ChineseMenu.CHINESE_BUTTON);
        englishButton.setText(ChineseMenu.ENLISH_BUTTON);
        ProjectDataCenter.TABLE_HEAD = ChineseMenu.TABLE_HEAD;
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

}
