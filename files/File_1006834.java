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
package x7.repository;

import x7.core.async.CasualWorker;
import x7.core.async.IAsyncTask;
import x7.repository.internal.DefaultRepository;
import x7.repository.redis.JedisConnector_Persistence;

import javax.sql.DataSource;
import java.util.List;
import java.util.Objects;


public class RepositoryBooter {

    private static RepositoryBooter instance = null;

    private static DataRepository dataRepository;

    protected static void init(DataRepository repository){
        dataRepository = repository;
    }

    public static void boot() {
        if (instance == null) {
            instance = new RepositoryBooter();
            init();
            HealthChecker.onStarted();
            CasualWorker.accept(new IAsyncTask() {
                @Override
                public void execute() throws Exception {
                    try {
                        Thread.sleep(3000);
                        generateId();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public static void onStarted(){

        HealthChecker.onStarted();
        CasualWorker.accept(new IAsyncTask() {
            @Override
            public void execute() throws Exception {
                try {
                    Thread.sleep(1000);
                    generateId();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void boot(DataSource ds_W, DataSource ds_R) {
        if (instance == null) {
            instance = new RepositoryBooter();
            setDataSource(ds_W, ds_R);

        }
    }

    private static void init() {
        setDataSource(null, null);
    }




    private static void setDataSource(DataSource ds_W, DataSource ds_R) {

        if (Objects.isNull(ds_W))
            throw new RuntimeException("Primary DataSource start failed");

        DataSourceSetter.set(ds_W, ds_R);

    }


    public static void generateId() {
        System.out.println("\n" + "----------------------------------------");
        List<IdGenerator> idGeneratorList = dataRepository.list(IdGenerator.class);
        for (IdGenerator generator : idGeneratorList) {
            String name = generator.getClzName();
            long maxId = generator.getMaxId();

            String idInRedis = JedisConnector_Persistence.getInstance().hget(DefaultRepository.ID_MAP_KEY, name);

            System.out.println(name + ",test, idInDB = " + maxId + ", idInRedis = " + idInRedis);


            if (idInRedis == null) {
                JedisConnector_Persistence.getInstance().hset(DefaultRepository.ID_MAP_KEY, name, String.valueOf(maxId));
            } else if (idInRedis != null && maxId > Long.valueOf(idInRedis)) {
                JedisConnector_Persistence.getInstance().hset(DefaultRepository.ID_MAP_KEY, name, String.valueOf(maxId));
            }

            System.out.println(name + ",final, idInRedis = " + JedisConnector_Persistence.getInstance().hget(DefaultRepository.ID_MAP_KEY, name));


        }
        System.out.println("----------------------------------------" + "\n");
    }

}
