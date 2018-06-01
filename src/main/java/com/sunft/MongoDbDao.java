package com.sunft;

import java.util.Map;

import org.bson.types.ObjectId;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

/**
 * ʹ��Java����������Mongodb���ݿ�
 * 
 * @author sunft
 * 
 */
public class MongoDbDao {

	/**
	 * ����
	 * 
	 * @throws Exception
	 */
	@Test
	public void add() throws Exception {
		// ������һ������
		Mongo mongo = new Mongo("localhost", 27017);
		// ��ȡ��ָ�������ݿ�
		DB db = mongo.getDB("test");
		DBCollection dbcollection = db.getCollection("person");
		// Ĭ��Ϊ{}
		DBObject dbobject = new BasicDBObject();
		dbobject.put("name", "��������");
		dbobject.put("age", 18);
		// db.collectionName.insert
		dbcollection.insert(dbobject);
		mongo.close();
	}

	/**
	 * ��ѯ
	 * @throws Exception
	 */
	@Test
	public void testQuery() throws Exception {
		// ������һ������
		Mongo mongo = new Mongo("localhost", 27017);
		// ��ȡ��ָ�������ݿ�
		DB db = mongo.getDB("test");
		DBCollection dbCollection = db.getCollection("person");
		// {}
		// db.collectionName.find();
		DBObject dbobject = new BasicDBObject();
		dbobject.put("age", 18);
		// ���ؽ����
		DBCursor dbCursor = dbCollection.find(dbobject);
		// ������ȡ�����
		while (dbCursor.hasNext()) {
			DBObject dbo = dbCursor.next();
			Map map = dbo.toMap();
			for (Object key : map.keySet()) {
				System.out.println("key:" + key.toString());
			}

			for (Object value : map.values()) {
				System.out.println("value:" + value.toString());
			}

		}

	}

	/**
	 * ɾ��
	 * @throws Exception
	 */
	@Test
	public void testRemove() throws Exception {
		// ������һ������
		Mongo mongo = new Mongo("localhost", 27017);
		// ��ȡ��ָ�������ݿ�
		DB db = mongo.getDB("test");
		DBCollection dbCollection = db.getCollection("person");
		DBObject dbobject = new BasicDBObject();
		dbCollection.remove(dbobject);
		mongo.close();
	}
	
	/**
	 * ����
	 * @throws Exception
	 */
	@Test
	public void testUpdate() throws Exception {
		// ������һ������
		Mongo mongo = new Mongo("localhost", 27017);
		// ��ȡ��ָ�������ݿ�
		DB db = mongo.getDB("test");
		DBCollection dbCollection = db.getCollection("person");
		/**
		 * ���µ�����
		 * ���µ����ݵĶ���
		 * ���û�з��������ļ�¼,�Ƿ�����һ����¼
		 * ����ж�����¼����,�Ƿ�ȫ������
		 */
		DBObject updateCondition=new BasicDBObject();  
        
        updateCondition.put("_id", new ObjectId("5b1089ff44634a6c9ac6ddd2")); 
		DBObject updatedValue=new BasicDBObject();  
        updatedValue.put("name", "������");  
          
        DBObject updateSetValue=new BasicDBObject("$set",updatedValue);
		int n = dbCollection.update(updateCondition, updateSetValue, true, true).getN();
		System.out.println(n);
		mongo.close();
	}

}
