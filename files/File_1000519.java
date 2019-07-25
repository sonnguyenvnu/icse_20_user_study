package org.nutz.el.opt.object;

import java.util.Queue;

import org.nutz.el.opt.AbstractOpt;

/**
 * 方法执行
 * 以方法体�?�括�?��?�为边界
 * @author juqkai(juqkai@gmail.com)
 *
 */
public class InvokeMethodOpt extends AbstractOpt {
    private Object left;

    public int fetchPriority() {
        return 1;
    }

    public Object calculate() {
        if(left instanceof MethodOpt){
            return ((MethodOpt) left).calculate();
        }
        return null;
    }

    public String fetchSelf() {
        return "method invoke";
    }

    public void wrap(Queue<Object> operand) {
        left = operand.poll();
    }

}
