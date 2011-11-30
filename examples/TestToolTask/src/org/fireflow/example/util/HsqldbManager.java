package org.fireflow.example.util;

import java.util.HashMap;
import java.util.Map;

import org.hsqldb.Server;

public class HsqldbManager {
	// ���ݿ�����
	public final static String DATABASE_NAME = "fireflow";
	// ���ݿ�·��
	public final static String DATABASE_PATH = "\\db\\";
	// ���ݿ�˿�ȱʡΪ9001
	public final static int PORT = 9001;
	// ���ݿ����
	public final static Server server = new Server();

	public static void startupHsqldb() {

		Map<String, String> dbConfig = getDBConfig();
		server.setDatabaseName(0, dbConfig.get("dbname"));
		server.setDatabasePath(0, dbConfig.get("dbpath"));
		int port = PORT;
		try {
			port = Integer.parseInt(dbConfig.get("port"));
		} catch (Exception e) {
			port = PORT;
		}
		server.setPort(port);
		server.setSilent(true);
		// ��ʼ���ݿ����
		server.start();

		System.out.println("����hsqldb�����������!");
		try {
			Thread.sleep(800);
		} catch (InterruptedException e) {
		}
	}

	/*
	 * ��ȡ���ݿ����������������Ϣ
	 */
	private static Map<String, String> getDBConfig() {
		Map<String, String> dbConfig = new HashMap<String, String>();

		// ���classpath·��
		String classpath = URLUtil.getClassPath(HsqldbManager.class);

		// ���ݿ�·��
		String dbpath = "/db/";
		if (null == dbpath) {
			dbpath = DATABASE_PATH;
		}
		if (!dbpath.startsWith("\\") || !dbpath.startsWith("/")) {
			dbpath = "/" + dbpath;
		}
		if (!dbpath.endsWith("\\") || !dbpath.endsWith("/")) {
			dbpath = dbpath + "/";
		}
		// ���ݿ���

		String dbname = DATABASE_NAME;

		// ���ݿ���

		String strPort = PORT + "";

		// ������ǰ���ݿ��ļ�·��
		// path = path.substring(0, path.length()-7)+"lib\\db\\fireflow";
		dbpath = classpath + dbpath + dbname;

		dbConfig.put("dbpath", dbpath);
		dbConfig.put("dbname", dbname);
		dbConfig.put("port", strPort);

		return dbConfig;
	}

	/**
	 * context����ʱ.�ر����ݿ�
	 * 
	 * @param sce
	 *            ServletContextEvent
	 */
	public static void stopHsqldb() {
		// �������ݿ����
		server.stop();

		System.out.println("���ݿ�����ѹر�!");
		try {
			Thread.sleep(800);
		} catch (InterruptedException e) {
		}
	}

}
