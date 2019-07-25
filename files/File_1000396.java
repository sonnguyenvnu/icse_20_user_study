package org.nutz.dao.impl.link;

import java.util.Map;

import org.nutz.dao.FieldFilter;
import org.nutz.dao.FieldMatcher;
import org.nutz.dao.entity.LinkField;
import org.nutz.dao.impl.AbstractLinkVisitor;
import org.nutz.lang.Lang;

public class DoUpdateLinkVisitor extends AbstractLinkVisitor {

    public void visit(Object obj, final LinkField lnk) {
        Object value = lnk.getValue(obj);
        if (Lang.eleSize(value) == 0)
            return;
        if (value instanceof Map<?, ?>)
            value = ((Map<?, ?>) value).values();

        FieldMatcher fm = FieldFilter.get(lnk.getLinkedEntity().getType());

        // å¦‚æžœéœ€è¦?å¿½ç•¥ Null å­—æ®µï¼Œåˆ™ä¸ºæ¯?ä¸ª POJO éƒ½ç”Ÿæˆ?ä¸€æ?¡è¯­å?¥
        if (null != fm && fm.isIgnoreNull()) {
            opt.addUpdateForIgnoreNull(lnk.getLinkedEntity(), value, fm);
        }
        // å?¦åˆ™ç”Ÿæˆ?ä¸€æ?¡æ‰¹å¤„ç?†è¯­å?¥
        else {
            opt.addUpdate(lnk.getLinkedEntity(), value);
        }

    }

}
