package org.n3r.idworker;

import org.n3r.idworker.strategy.DefaultWorkerIdStrategy;
import org.n3r.idworker.utils.Utils;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Sid {
    private static WorkerIdStrategy workerIdStrategy;
    private static IdWorker idWorker;

    static {
        configure(DefaultWorkerIdStrategy.instance);
    }


    public static synchronized void configure(WorkerIdStrategy custom) {
        if (workerIdStrategy != null) workerIdStrategy.release();
        workerIdStrategy = custom;
        idWorker = new IdWorker(workerIdStrategy.availableWorkerId()) {
            @Override
            public long getEpoch() {
                return Utils.midnightMillis();
            }
        };
    }

    /**
     * 一天最大毫秒86400000，最大�?�用27比特
     * 27+10+11=48�? 最大值281474976710655(15字)，YK0XXHZ827(10字)
     * 6�?(YYMMDD)+15�?，共21�?
     *
     * @return 固定21�?数字字符串
     */

    public static String next() {
        long id = idWorker.nextId();
        String yyMMdd = new SimpleDateFormat("yyMMdd").format(new Date());
        return yyMMdd + String.format("%014d", id);
    }


    /**
     * 返回固定16�?的字�?数字混编的字符串。
     */
    public String nextShort() {
        long id = idWorker.nextId();
        String yyMMdd = new SimpleDateFormat("yyMMdd").format(new Date());
        return yyMMdd + Utils.padLeft(Utils.encode(id), 10, '0');
    }
    
//    public static void main(String[] args) {
//		String aa = new Sid().nextShort();
//		String bb = new Sid().next();
//		
//		System.out.println(aa);
//		System.out.println(bb);
//	}
}
