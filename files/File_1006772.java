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
package x7.core.async;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * 任何�?�?�的计算，但计算结果�?影�?�?��?�的计算的情况下，�?�以创建临时任务, 交给此类处�?�<br>
 * 在改�?�数�?�时�?能用，如果�?用，在�?�那个数�?�时，也必需用
 * CasualWorker.accept(new ICasualTask(){execute(){}});<br>
 * 适应举例：<br>
 * 1. 在创建场景时，�?始化场景的数�?�<br>
 * 2. 在倒计时的时间段里，<br>
 * 
 * @author wangyan
 *
 */
public final class CasualWorker {
	
	private final static ExecutorService service = Executors.newFixedThreadPool(1);
	
	private final static BlockingQueue<IAsyncTask> tasks = new ArrayBlockingQueue<IAsyncTask>(4096);
	
	static {
		
		service.execute(new Runnable(){

			@Override
			public void run() {

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				while (true) {
					try {
						tasks.take().execute();
					} catch (NullPointerException npe){
						npe.printStackTrace();
					}catch (InterruptedException e) {
						e.printStackTrace();
					} catch (Exception e){
						e.printStackTrace();
					}
				}
			}
			
		});

	}
	/**
	 * 接�?�临时任务，异步执行
	 * @param task
	 * @throws InterruptedException
	 */
	public static void accept(IAsyncTask task) {
		try {
			tasks.put(task);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
}
