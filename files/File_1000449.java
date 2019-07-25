package org.nutz.dao.impl.sql.pojo;

import java.util.List;

import org.nutz.dao.entity.Entity;
import org.nutz.dao.entity.MappingField;
import org.nutz.dao.util.Pojos;

public class UpdateFieldsPItem extends InsertValuesPItem {

    private static final long serialVersionUID = 1L;

    /**
     * �?�考对象，根�?�这个对象�?�决定是�?�忽略空值
     */
    private Object refer;

    public UpdateFieldsPItem(Object refer) {
        this.refer = refer;
    }

    protected List<MappingField> _mfs(Entity<?> en) {
        if (null == mfs)
        	return Pojos.getFieldsForUpdate(_en(en), getFieldMatcher(), refer == null ? pojo.getOperatingObject() : refer);
        return mfs;
    }

    public void joinSql(Entity<?> en, StringBuilder sb) {
        List<MappingField> mfs = _mfs(en);

        sb.append(" SET ");
        for (MappingField mf : mfs)
            sb.append(mf.getColumnNameInSql()).append("=?,");

        sb.setCharAt(sb.length() - 1, ' ');
    }

}
