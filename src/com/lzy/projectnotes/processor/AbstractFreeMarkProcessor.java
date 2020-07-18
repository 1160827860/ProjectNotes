package com.lzy.projectnotes.processor;



import java.io.Writer;

public abstract class AbstractFreeMarkProcessor implements Processor{
        protected abstract Object getModel();
//        protected abstract Template
        protected abstract Writer getWriter();


}
