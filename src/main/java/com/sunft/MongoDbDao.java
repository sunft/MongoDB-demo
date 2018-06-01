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
 * 使用Java代码来调用Mongodb数据库
 * 
 * @author sunft
 * 
 */
public class MongoDbDao {

	/**
	 * 增加
	 * 
	 * @throws Exception
	 */
	@Test
	public void add() throws Exception {
		// 建立起一个连接
		Mongo mongo = new Mongo("localhost", 27017);
		// 获取到指定的数据库
		DB db = mongo.getDB("test");
		DBCollection dbcollection = db.getCollection("person");
		// 默认为{}
		DBObject dbobject = new BasicDBObject();
		dbobject.put("name", "卡卡罗特");
		dbobject.put("age", 18);
		// db.collectionName.insert
		dbcollection.insert(dbobject);
		mongo.close();
	}

	/**
	 * 查询
	 * @throws Exception
	 */
	@Test
	public void testQuery() throws Exception {
		// 建立起一个连接
		Mongo mongo = new Mongo("localhost", 27017);
		// 获取到指定的数据库
		DB db = mongo.getDB("test");
		DBCollection dbCollection = db.getCollection("person");
		// {}
		// db.collectionName.find();
		DBObject dbobject = new BasicDBObject();
		dbobject.put("age", 18);
		// 返回结果集
		DBCursor dbCursor = dbCollection.find(dbobject);
		// 遍历获取结果集
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
	 * 删除
	 * @throws Exception
	 */
	@Test
	public void testRemove() throws Exception {
		// 建立起一个连接
		Mongo mongo = new Mongo("localhost", 27017);
		// 获取到指定的数据库
		DB db = mongo.getDB("test");
		DBCollection dbCollection = db.getCollection("person");
		DBObject dbobject = new BasicDBObject();
		dbCollection.remove(dbobject);
		mongo.close();
	}
	
	/**
	 * 更新
	 * @throws Exception
	 */
	@Test
	public void testUpdate() throws Exception {
		// 建立起一个连接
		Mongo mongo = new Mongo("localhost", 27017);
		// 获取到指定的数据库
		DB db = mongo.getDB("test");
		DBCollection dbCollection = db.getCollection("person");
		/**
		 * 更新的条件
		 * 更新的内容的对象
		 * 如果没有符合条件的记录,是否新增一条记录
		 * 如果有多条记录符合,是否全部更新
		 */
		DBObject updateCondition=new BasicDBObject();  
        
        updateCondition.put("_id", new ObjectId("5b1089ff44634a6c9ac6ddd2")); 
		DBObject updatedValue=new BasicDBObject();  
        updatedValue.put("name", "贝吉塔");  
          
        DBObject updateSetValue=new BasicDBObject("$set",updatedValue);
		int n = dbCollection.update(updateCondition, updateSetValue, true, true).getN();
		System.out.println(n);
		mongo.close();
	}

}
