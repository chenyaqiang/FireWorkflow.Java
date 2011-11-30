package org.fireflow.example.testtooltask.applicationhandler;

import org.fireflow.engine.IProcessInstance;
import org.fireflow.engine.ITaskInstance;
import org.fireflow.engine.impl.TaskInstance;
import org.fireflow.engine.taskinstance.IApplicationHandler;

public class ErrorHandler implements IApplicationHandler{

	public void execute(ITaskInstance arg0) {

		System.out.println("This is ErrorHandler::outputType���ݲ���ȷ����ӡ������Ϣ....");
		IProcessInstance processInstance = ((TaskInstance)arg0).getAliveProcessInstance();
		String outputType = (String)processInstance.getProcessInstanceVariable("outputType");
		System.out.println("�����outputTypeֵ�ǣ�"+outputType);
	}

}
