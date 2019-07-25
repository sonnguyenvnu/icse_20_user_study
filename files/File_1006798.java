/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package x7.repository.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import x7.core.async.HeartBeator;
import x7.core.async.HeartBeat;
import x7.core.bean.BeanElement;
import x7.core.bean.Parsed;
import x7.core.bean.Parser;
import x7.core.repository.X;
import x7.core.util.TimeUtil;
import x7.repository.mapper.Mapper;
import x7.repository.mapper.MapperFactory;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 
 * 
 * @author Sim
 * 
 */
@Component
public class AsyncDaoImpl implements HeartBeat, AsyncDao {

	/**
	 * 批处�?�最多�?�数
	 */
	public static int MAX_BATCH = 500;
	/**
	 * 延时1分钟
	 */
	private final static int HEARTBEAT_DELAY = (int) TimeUtil.ONE_MINUTE;
	private long heartBeatTime = 0; // �?低心跳的实时性�?��??高性能

	private final ExecutorService mainExecutor = Executors.newSingleThreadExecutor();

	private final ExecutorService inner = Executors.newSingleThreadExecutor();


	public AsyncDaoImpl() {
		HeartBeator.add(this);
	}

	@Autowired
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@SuppressWarnings("rawtypes")
	private  Map<Class, ArrayList<Object>> creationMap = new HashMap<Class, ArrayList<Object>>();

	@SuppressWarnings("rawtypes")
	private  Map<Class, ArrayList<Object>> refreshMap = new HashMap<Class, ArrayList<Object>>();
	
	@SuppressWarnings("rawtypes")
	private  Map<Class, ArrayList<Object>> removeMap = new HashMap<Class, ArrayList<Object>>();
	
	private Connection getConnection() throws SQLException {
		if (dataSource == null){
			System.err.println("No DataSource");
		}
		return dataSource.getConnection();
	}
	
	private static void close (PreparedStatement pstmt){
		if (pstmt != null){
			try{
				pstmt.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	/**
	 * 放回连接池,<br>
	 * 连接池已�?�?写了关闭连接的方法
	 */
	private static void close(Connection conn) {
		try {
			if (conn != null){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 *
	 */
	@Override
	public void create(final Object obj) {
		mainExecutor.submit(new Runnable() {

			@SuppressWarnings("rawtypes")
			@Override
			public void run()  {

				/*
				 * 加入需�?�?久化的的对象MAP
				 */
				Class clz = obj.getClass();
				
				filterTryToCreate(clz);
				
				ArrayList<Object> objectList = creationMap.get(clz);
				if (objectList == null) {
					objectList = new ArrayList<Object>();
					creationMap.put(clz, objectList);
				}
				/*
				 * 如果�?存在，就添加
				 */
				if (!objectList.contains(obj)) {
					objectList.add(obj);
				}

			}
		});

	}
	
	/*
	 *
	 */
	@Override
	public void refresh(final Object obj) {
		mainExecutor.submit(new Runnable() {

			@SuppressWarnings("rawtypes")
			@Override
			public void run()  {
				/*
				 * 加入需�?�?久化的的对象MAP
				 */
				Class clz = obj.getClass();
				
				filterTryToCreate(clz);
				
				ArrayList<Object> objectList = refreshMap.get(clz);
				if (objectList == null) {
					objectList = new ArrayList<Object>();
					refreshMap.put(clz, objectList);
				}
				/*
				 * 如果�?存在，就添加
				 */
				if (!objectList.contains(obj)) {
					objectList.add(obj);
				}

			}
		});

	}

	/*
	 *
	 */
	@Override
	public void remove(final Object obj) {
		mainExecutor.submit(new Runnable() {

			@SuppressWarnings("rawtypes")
			@Override
			public void run()  {

				/*
				 * 加入需�?�?久化的的对象MAP
				 */
				Class clz = obj.getClass();
				
				filterTryToCreate(clz);
				
				ArrayList<Object> objectList = removeMap.get(clz);
				if (objectList == null) {
					objectList = new ArrayList<Object>();
					removeMap.put(clz, objectList);
				}
				/*
				 * 如果�?存在，就添加
				 */
				if (!objectList.contains(obj)) {
					objectList.add(obj);
				}

			}
		});

	}
	/**
	 * 批处�?�
	 * 
	 * @throws Exception
	 */
	private void batch() throws Exception {

		/*
		 * 创建批处�?�
		 */
		stepCreate();

		/*
		 * 更新批处�?�
		 */
		stepRefresh();
		
		/*
		 * 删除处�?�
		 */
		stepRemove();

	}

	@SuppressWarnings("rawtypes")
	private void stepCreate() throws Exception {
		if (creationMap.size() == 0)
			return;
		/*
		 * 执行�?的拷�?
		 */
		final Map<Class, ArrayList<Object>> tempCreationMap = new HashMap<Class, ArrayList<Object>>();
		tempCreationMap.putAll(creationMap);
		creationMap.clear();

		/*
		 * 切�?�内部线程处�?�
		 */
		inner.execute(new Runnable() {

			@Override
			public void run() {
				try {
					/*
					 * 执行批处�?�
					 */
					executeCreate(tempCreationMap);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});

	}

	/**
	 * 批处�?�的执行<br>
	 * 在内部线程里调用
	 */
	@SuppressWarnings({ "rawtypes" })
	private void executeCreate(final Map<Class, ArrayList<Object>> tempMap)
			throws Exception {
		for (Class clz : tempMap.keySet()) {

			ArrayList<Object> objList = tempMap.get(clz);
			
			String sql = MapperFactory.getSql(clz, Mapper.CREATE);
			
			List<BeanElement> eles = MapperFactory.getElementList(clz);
			
			/*
			 * 分段批处�?�，�?段�?超过MAX_BATCH
			 */
			int size = objList.size();
			int times = size / MAX_BATCH + 1;

			for (int i = 0; i < times; i++) {

				int segment = 0;
				if (i + 1 == times) {
					segment = size % MAX_BATCH;
					if (segment == 0){
						break;
					}
				}else{
					segment = MAX_BATCH;
				}
				
				int fromIndex = i * MAX_BATCH;
				List<Object> subList = objList.subList(fromIndex, fromIndex + segment);
				batchCreate(subList, sql, eles);
				
			}

		}
	}
	/**
	 * 批处�?�，对象转SQL并完�?�?�入
	 * @param objList
	 * @param sql
	 * @param eles
	 */
	private void batchCreate(List<Object> objList, String sql, List<BeanElement> eles) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			
			Parsed parsed = Parser.get(objList.get(0).getClass());
			
			for (Object obj : objList) {

				int i = 1;
				for (BeanElement ele : eles) {

					Method method = null;
					try {
						method = obj.getClass().getSuperclass()
								.getDeclaredMethod(ele.getter);
					} catch (NoSuchMethodException e) {
						method = obj.getClass().getDeclaredMethod(ele.getter);
					}
					Object value = method.invoke(obj);
					pstmt.setObject(i++, value);
				}

				pstmt.addBatch();
			}

			pstmt.executeBatch();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				pstmt.clearBatch();
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				pstmt.close();
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			close(conn);
		}

	}
	
	@SuppressWarnings("rawtypes")
	private void stepRemove() throws Exception {
		if (removeMap.size() == 0)
			return;
		/*
		 * 执行�?的拷�?
		 */
		final Map<Class, ArrayList<Object>> tempRefreshMap = new HashMap<Class, ArrayList<Object>>();
		tempRefreshMap.putAll(removeMap);
		removeMap.clear();

		/*
		 * 切�?�内部线程处�?�
		 */
		inner.execute(new Runnable() {

			@Override
			public void run() {
				try {
					/*
					 * 执行批处�?�
					 */
					executeRemove(tempRefreshMap);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});

	}

	/**
	 * 批处�?�的执行<br>
	 * 在内部线程里调用
	 */
	@SuppressWarnings("rawtypes")
	private void executeRemove(final Map<Class, ArrayList<Object>> tempMap)
			throws Exception {
		for (Class clz : tempMap.keySet()) {

			String sql = MapperFactory.getSql(clz, Mapper.REMOVE);

			Connection conn = null;
			PreparedStatement pstmt = null;
			try {
				conn = getConnection();
				conn.setAutoCommit(false);
				pstmt = conn.prepareStatement(sql);
				
				Parsed parsed = Parser.get(clz);
				String keyOne = parsed.getKey(X.KEY_ONE);
				
				ArrayList<Object> objList = tempMap.get(clz);
				for (Object obj : objList) {

					int i = 1;
					
					SqlUtil.adpterSqlKey(pstmt, keyOne, obj, i);

					pstmt.addBatch();
				}

				pstmt.executeBatch();
				conn.commit();
			}  catch (Exception e) {
				e.printStackTrace();
				pstmt.clearBatch();
				conn.rollback();
			} finally {
				try {
					conn.setAutoCommit(true);
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				close(conn);
			}

		}
	}

	
	
	@SuppressWarnings("rawtypes")
	private void stepRefresh() throws Exception {
		if (refreshMap.size() == 0)
			return;
		/*
		 * 执行�?的拷�?
		 */
		final Map<Class, ArrayList<Object>> tempRefreshMap = new HashMap<Class, ArrayList<Object>>();
		tempRefreshMap.putAll(refreshMap);
		refreshMap.clear();

		/*
		 * 切�?�内部线程处�?�
		 */
		inner.execute(new Runnable() {

			@Override
			public void run() {
				try {
					/*
					 * 执行批处�?�
					 */
					executeRefresh(tempRefreshMap);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});

	}
	

	/**
	 * 批处�?�的执行<br>
	 * 在内部线程里调用
	 */
	@SuppressWarnings({ "rawtypes"})
	private void executeRefresh(final Map<Class, ArrayList<Object>> tempMap)
			throws Exception {
		for (Class clz : tempMap.keySet()) {
			
			ArrayList<Object> objList = tempMap.get(clz);
			
			String sql = MapperFactory.getSql(clz, Mapper.REFRESH);
			
			List<BeanElement> eles = MapperFactory.getElementList(clz);

			/*
			 * 分段批处�?�，�?段�?超过MAX_BATCH
			 */
			int size = objList.size();
			int times = size / MAX_BATCH + 1;

			for (int i = 0; i < times; i++) {

				int segment = 0;
				if (i + 1 == times) {
					segment = size % MAX_BATCH;
					if (segment == 0){
						break;
					}
				}else{
					segment = MAX_BATCH;
				}
				
				int fromIndex = i * MAX_BATCH;
				List<Object> subList = objList.subList(fromIndex, fromIndex + segment);
				batchRefresh(subList, sql, eles);
				
			}

		}
	}
	
	private void batchRefresh(List<Object> objList, String sql, List<BeanElement> eles) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			
			Parsed parsed = Parser.get(objList.get(0).getClass());
			String keyOne = parsed.getKey(X.KEY_ONE);
			
			for (Object obj : objList) {

				int i = 1;
				for (BeanElement ele : eles) {
					
					if (ele.property.equals(keyOne) ) {
						continue;
					}
					

					Method method = null;
					try {
						method = obj.getClass().getSuperclass()
								.getDeclaredMethod(ele.getter);
					} catch (NoSuchMethodException e) {
						method = obj.getClass().getDeclaredMethod(ele.getter);
					}
					Object value = method.invoke(obj);
					pstmt.setObject(i++, value);
				}
				
				/*
				 * 处�?�KEY
				 */
				SqlUtil.adpterSqlKey(pstmt, keyOne, obj, i);

				pstmt.addBatch();
			}

			pstmt.executeBatch();
			conn.commit();
		}  catch (Exception e) {
			e.printStackTrace();
			try {
				pstmt.clearBatch();
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.setAutoCommit(true);
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			close(conn);
		}
	}
	
	/**
	 * 内部机制, 永远�?能调用此方法
	 */
	@Override
	@Deprecated
	public void tick(long now) {
		onHeartBeat(now);

	}

	/**
	 * 定时处�?�
	 */
	private void onHeartBeat(final long now) {
		mainExecutor.submit(new Runnable() {

			@SuppressWarnings("rawtypes")
			@Override
			public void run()  {
				if (heartBeatTime == 0){
					heartBeatTime = now;
					return;
				}
				if (now - heartBeatTime >= HEARTBEAT_DELAY) {
					heartBeatTime = now;
					/*
					 * 定时批处�?�
					 */
					try {
						batch();
					}catch (Exception e){
						e.printStackTrace();
					}
				}
			}
		});

	}

	/**
	 * <br>
	 * 关机时, 或业务上�?求立�?�执行时 调用<br>
	 * �?必等待定时处�?�,调用�?�, DAO线程将立�?�处�?�<br>
	 * 在没有了在线玩家的情况下,�?�以�?�?数�?�的完整性<br>
	 * 设计上, 没有在shutDownHook的线程里�?行, 如何�?�?执行完毕 ? <br>
	 * 建议在shutDownHook的最�?�一步调用Thread.sleep(?) <br>
	 * <br>
	 */
	public void doImmediately() {
		mainExecutor.submit(new Runnable() {

			@SuppressWarnings("rawtypes")
			@Override
			public void run()  {
				try {
					batch();
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		});
	}
	
	@SuppressWarnings("rawtypes")
	private void filterTryToCreate(Class clz){
		String sql = MapperFactory.tryToCreate(clz);
		if (sql == null || sql.equals(""))
			return;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(true);
			pstmt = conn.prepareStatement(sql);

			pstmt.execute();
		}  catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			close(conn);
		}

	}

}
