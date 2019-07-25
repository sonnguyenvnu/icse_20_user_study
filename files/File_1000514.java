package org.nutz.el.opt.logic;

import org.nutz.castor.Castors;
import org.nutz.el.opt.TwoTernary;

/**
 * 三元�?算符: '?' <br>
 * 说明,三元表达�?包�?�两个�?算符:'?',':'.整个表达�?的结果由它们共�?�完�?.而�?个符�?�承担一部分�?作.<br>
 * <li>'?':包�?�两个�?作对象,�?�,'?'左侧的逻辑表达�?,与'?'�?�侧的第一值.<br>
 * <li>':':也包�?�两个�?作对象,�?�,':'�?�?�生�?的'?'对象,与':'�?�侧的第二个值.<br>
 * 在进行�?算的时候,是先�?算':',而':'中将�?�件的判断委托到'?'当中.然�?�':'对象根�?�'?'中的返回 结果分别读�?�'?'中的的左值或,':'的�?�值
 * 
 * @author juqkai(juqkai@gmail.com)
 */
public class QuestionOpt extends TwoTernary {
    public int fetchPriority() {
        return 13;
    }

    public Object calculate() {
        Object obj = getLeft();
        if (null == obj)
            return false;
        if (obj instanceof Boolean)
            return (Boolean) obj;
        // throw new ElException("三元表达�?错误! --> " + obj);
        return Castors.me().castTo(obj, Boolean.class);
    }

    public String fetchSelf() {
        return "?";
    }
}
