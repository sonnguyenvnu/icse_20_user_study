package org.nutz.dao.impl.link;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.nutz.dao.entity.Entity;
import org.nutz.dao.entity.LinkField;
import org.nutz.dao.impl.AbstractLinkVisitor;
import org.nutz.dao.impl.EntityHolder;
import org.nutz.dao.impl.entity.field.ManyManyLinkField;
import org.nutz.dao.sql.Pojo;
import org.nutz.dao.util.RelationObjectMap;
import org.nutz.lang.Each;
import org.nutz.lang.ExitLoop;
import org.nutz.lang.Lang;
import org.nutz.lang.LoopException;

public class DoInsertRelationLinkVisitor extends AbstractLinkVisitor {

    private EntityHolder holder;

    public DoInsertRelationLinkVisitor(EntityHolder holder) {
        this.holder = holder;
    }

    public void visit(final Object obj, LinkField lnk) {
        // �?�有多对多的映射�?被考虑
        if (lnk instanceof ManyManyLinkField) {
            // 获�?�两边映射主键的值
            final ManyManyLinkField mm = (ManyManyLinkField) lnk;

            Object value = lnk.getValue(obj);
            
            final List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(Lang.eleSize(value));
            Lang.each(value, new Each<Object>() {
                public void invoke(int i, Object ele, int length) throws ExitLoop, LoopException {
                    list.add(new RelationObjectMap(mm, obj, ele));
                }
            });

            if (list.isEmpty())
                return;

            Entity<Map<String, Object>> en = holder.makeEntity(mm.getRelationName(), list.get(0));
            Pojo pojo = opt.maker().makeInsert(en);
            pojo.setOperatingObject(list);
            for (Object p : list)
                pojo.addParamsBy(p);

            opt.add(pojo);

        }
    }

}
