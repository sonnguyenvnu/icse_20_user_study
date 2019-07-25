/**
 * Copyright (c) 2015-2019, Michael Yang �?��?海 (fuhai999@gmail.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 *  http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.jboot.support.redis;

import io.jboot.Jboot;

/**
 * Created by michael.
 * <p>
 * Redis 分布�?�?
 * <p>
 * 使用方法：
 * <p>
 * JbootRedisLock lock = new JbootRedisLock("lockName");
 * try{
 * boolean acquire = lock.acquire();
 * if(acquire){
 * // do your something
 * }
 * }finally {
 * lock.release();
 * }
 * <p>
 * 使用方法2：
 * JbootRedisLock lock = new JbootRedisLock("lockName");
 * lock.runIfAcquired(new Runnable(){
 * <p>
 * public void run() {
 * //do your something
 * }
 * });
 */
public class JbootRedisLock {

    long expireMsecs = 1000 * 60;//60秒expireMsecs �?�?有超时，防止线程在入�?以�?�，无�?的执行下去，让�?无法释放
    long timeoutMsecs = 0;// �?等待超时

    private String lockName;
    private boolean locked = false;
    private JbootRedis redis;

    /**
     * 创建redis分布�?�?
     *
     * @param lockName �?的�??称
     */
    public JbootRedisLock(String lockName) {
        if (lockName == null) {
            throw new NullPointerException("lockName must not null !");
        }
        this.lockName = lockName;
        this.redis = Jboot.getRedis();
    }

    /**
     * 创建redis分布�?�?
     *
     * @param lockName     �?�??称
     * @param timeoutMsecs 获�?��?的时候，等待时长
     */
    public JbootRedisLock(String lockName, long timeoutMsecs) {
        if (lockName == null) {
            throw new NullPointerException("lockName must not null !");
        }
        this.lockName = lockName;
        this.timeoutMsecs = timeoutMsecs;
        this.redis = Jboot.getRedis();
    }


    public void runIfAcquired(Runnable runnable) {
        if (runnable == null) {
            throw new NullPointerException("runnable must not null!");
        }
        try {
            if (acquire()) {
                runnable.run();
            }
        } finally {
            //执行完毕，释放�?
            release();
        }
    }


    /**
     * 获�?��?
     *
     * @return true：活动�?了 ， false ：没获得�?。 如果设置了timeoutMsecs，那么这个方法�?�能被延迟 timeoutMsecs 毫秒。
     */
    public boolean acquire() {
        long timeout = timeoutMsecs;

        do {
            long expires = System.currentTimeMillis() + expireMsecs + 1;

            Long result = redis.setnx(lockName, expires);
            if (result != null && result == 1) {
                // lock acquired
                locked = true;
                return true;
            }

            Long currentValue = redis.get(lockName);
            if (currentValue != null && currentValue < System.currentTimeMillis()) {
                //判断是�?�为空，�?为空的情况下，如果被其他线程设置了值，则第二个�?�件判断是过�?去的
                // lock is expired

                Long oldValue = redis.getSet(lockName, expires);
                //获�?�上一个�?到期时间，并设置现在的�?到期时间，
                //�?�有一个线程�?能获�?�上一个线上的设置时间，因为jedis.getSet是�?�步的
                if (oldValue != null && oldValue.equals(currentValue)) {
                    //如果这个时候，多个线程�?�好都到了这里
                    //�?�有一个线程的设置值和当�?值相�?�，他�?有�?�利获�?��?
                    //lock acquired
                    locked = true;
                    return true;
                }
            }

            if (timeout > 0) {
                timeout -= 100;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } while (timeout > 0);
        return false;
    }


    /**
     * 是�?�获得 �? 了
     *
     * @return
     */
    public boolean isLocked() {
        return locked;
    }

    /**
     * 释放 �?
     */
    public void release() {
        if (!isLocked()) {
            return;
        }
        if (Jboot.getRedis().del(lockName) > 0) {
            locked = false;
        }
    }
}
