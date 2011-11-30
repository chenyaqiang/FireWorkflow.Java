package org.fireflow.example.testtooltask;

import org.fireflow.engine.EngineException;
import org.fireflow.engine.IProcessInstance;
import org.fireflow.engine.IWorkflowSession;
import org.fireflow.engine.RuntimeContext;
import org.fireflow.example.util.HsqldbManager;
import org.fireflow.kernel.KernelException;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

public class TestToolTaskMain {
	private final static String springConfigFile = "/spring_config/applicationContext.xml";
	private static ClassPathResource resource = null;
	private static XmlBeanFactory beanFactory = null;
	private static TransactionTemplate transactionTemplate = null;
	private static RuntimeContext runtimeContext = null;

	/**
	 * ��ʼ�����л���
	 * 
	 * @throws Exception
	 */
	public static void setUp() throws Exception {
		// ����hsqldb����demoʹ��hsqldb�洢����ʵ������
		HsqldbManager.startupHsqldb();

		resource = new ClassPathResource(springConfigFile);
		beanFactory = new XmlBeanFactory(resource);
		transactionTemplate = (TransactionTemplate) beanFactory
				.getBean("transactionTemplate");
		runtimeContext = (RuntimeContext) beanFactory.getBean("runtimeContext");

	}

	public static void tearDown() throws Exception {
		// ����hsqldb����demoʹ��hsqldb�洢����ʵ������
		HsqldbManager.stopHsqldb();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// �������ݿ⣬��ʼ������
			setUp();

			//��������ʵ������ִ�и�ʵ��
			System.out.println("===========��������ʵ������ִ��.........");
			transactionTemplate.execute(new TransactionCallback() {

				public Object doInTransaction(TransactionStatus arg0) {
					IWorkflowSession workflowSession = runtimeContext.getWorkflowSession();
					try {
						IProcessInstance processInstance = workflowSession.createProcessInstance("TestToolTaskProcess","test user");
						processInstance.run();
					} catch (EngineException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (KernelException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return null;
				}

			});
			System.out.println("===========����ʵ������!");
			//�ر����ݿ�
			tearDown();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
