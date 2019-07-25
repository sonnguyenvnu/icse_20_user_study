/**
 * Copyright (c) 2015-2019, Michael Yang �?��?海 (fuhai999@gmail.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.jboot.components.schedule;

import com.jfinal.log.Log;
import io.jboot.Jboot;
import io.jboot.support.redis.JbootRedis;

/**
 * @author Michael Yang �?��?海 （fuhai999@gmail.com）
 * @version V1.0
 * @Title: 分布�?任务
 * @Description: 在分布�?应用中，处�?�分布�?应用，基于redis。
 *
 * 特点：
 * 1�?简�?�，无需�?赖数�?�库。
 * 2�?高�?�用，�?存在�?�点故障
 * 3�?一致性，在集群环境中，�?�有一个任务在执行。
 * 4�?Failover，支�?故障转移
 * @Package io.jboot.schedule
 */
public class JbootDistributedRunnable implements Runnable {

    private static final Log LOG = Log.getLog(JbootDistributedRunnable.class);

    private JbootRedis redis;
    private int expire = 50 * 1000; // �?��?毫秒
    private String key;
    private Runnable runnable;


    public JbootDistributedRunnable() {

        this.redis = Jboot.getRedis();
        this.key = "jbootRunnable:" + this.getClass().getName();

        if (redis == null) {
            LOG.warn("redis is null, " +
                    "can not use @EnableDistributedRunnable in your Class[" + this.getClass().getName() + "], " +
                    "or config redis info in jboot.properties");
        }

    }

    public JbootDistributedRunnable(Runnable runnable) {
        this.runnable = runnable;
        this.key = "jbootRunnable:" + runnable.getClass().getName();
        this.redis = Jboot.getRedis();
        if (redis == null) {
            LOG.warn("redis is null, " +
                    "can not use @EnableDistributedRunnable in your Class[" + runnable.getClass().getName() + "], " +
                    "or config redis info in jboot.properties");
        }
    }

    public JbootDistributedRunnable(Runnable runnable, int expire) {
        this.expire = (expire - 1) * 1000;
        this.runnable = runnable;
        this.key = "jbootRunnable:" + runnable.getClass().getName();
        this.redis = Jboot.getRedis();
        if (redis == null) {
            LOG.warn("redis is null, " +
                    "can not use @EnableDistributedRunnable in your Class[" + runnable.getClass().getName() + "], " +
                    "or config redis info in jboot.properties");
        }
    }


    @Override
    public void run() {

        if (redis == null) {
            return;
        }

        Long result = null;

        for (int i = 0; i < 5; i++) {

            Long setTimeMillis = System.currentTimeMillis();
            result = redis.setnx(key, setTimeMillis);

            //error
            if (result == null) {
                quietSleep();
            }

            //setnx fail
            else if (result == 0) {
                Long saveTimeMillis = redis.get(key);
                if (saveTimeMillis == null) {
                    reset();
                }
                long ttl = System.currentTimeMillis() - saveTimeMillis;
                if (ttl > expire) {
                    //防止死�?
                    reset();
                }

                // 休�?� 2 秒钟，�?新去抢，因为�?�能别的应用执行失败了
                quietSleep();

            }

            //set success
            else if (result == 1) {
                break;
            }
        }

        //抢了5次都抢�?到，�?明已�?被别的应用抢走了
        if (result == null || result == 0) {
            return;
        }


        try {

            if (runnable != null) {
                runnable.run();
            } else {
                boolean runSuccess = execute();

                //run()执行失败，让别的分布�?应用APP去执行
                //如果run()执行的时间很长（超过30秒）,那么别的分布�?应用�?�能也抢�?到了，�?�能等待下次轮休
                //作用：故障转移
                if (!runSuccess) {
                    reset();
                }
            }
        }

        // 如果 run() 执行异常，让别的分布�?应用APP去执行
        // 作用：故障转移
        catch (Throwable ex) {
            LOG.error(ex.toString(), ex);
            reset();
        }
    }


    /**
     * �?置分布�?的key
     */
    private void reset() {
        redis.del(key);
    }

    public void quietSleep() {

        int millis = 2000;
        if (this.expire <= 2000) {
            millis = 100;
        } else if (this.expire <= 5000) {
            millis = 500;
        } else if (this.expire <= 300000) {
            millis = 1000;
        }

        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //for override
    public boolean execute() {
        return true;
    }


}
