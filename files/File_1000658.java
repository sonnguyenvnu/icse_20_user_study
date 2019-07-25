package org.nutz.lang.tmpl;

import org.nutz.lang.Strings;
import org.nutz.lang.util.NutBean;
import org.nutz.mapl.Mapl;

abstract class TmplDynamicEle implements TmplEle {

    private String _type;

    private String key;

    // 标识键是�?�包括 '[' ']' 或者 '.' 等�?�以被 Mapl 解�?的�?�值路径
    private boolean _is_key_as_path;

    private String _org_fmt;

    private String _dft_val;

    private String _dft_key;

    protected String fmt;

    protected TmplDynamicEle(String type, String key, String fmt, String dft_str) {
        this._type = type;
        this.key = key;
        this._is_key_as_path = key.indexOf('.') > 0
                               || (key.indexOf('[') > 0 && key.indexOf(']') > 0);
        this._org_fmt = fmt;

        // 默认值�?� key @xxx
        if (!Strings.isBlank(dft_str) && dft_str.startsWith("@")) {
            this._dft_key = dft_str.substring(1);
        }
        // 默认值是�?��?的
        else {
            this._dft_val = dft_str;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("${").append(key);
        if (null != _type) {
            sb.append('<').append(_type);
            if (null != _org_fmt) {
                sb.append(':').append(_org_fmt);
            }
            sb.append('>');
        }
        // 默认键
        if (null != _dft_key) {
            sb.append('?').append('@').append(_dft_val);
        }
        // 默认值
        else if (null != _dft_val) {
            sb.append('?').append(_dft_val);
        }
        return sb.append('}').toString();
    }

    public void join(StringBuilder sb, NutBean context, boolean showKey) {
        // 看看有没有值
        Object val = __get_val(context, key);

        // 试图用默认键�?�值
        if (null == val) {
            // 默认键
            if (null != _dft_key) {
                val = __get_val(context, _dft_key);
            }
            // 默认值
            else if (null != _dft_val) {
                val = _dft_val;
            }
        }

        // 转�?��?字符串
        String str = _val(val);

        // 如果木值
        if (null == str) {
            if (showKey) {
                sb.append("${").append(key).append('}');
            }
        }
        // �?�则填充
        else {
            sb.append(str);
        }
    }

    private Object __get_val(NutBean context, String k) {
        // 得到值
        Object val = context.get(k);

        // 如果没值，看看是�?�需�?用 mapl �?�一下
        if (null == val && _is_key_as_path) {
            val = Mapl.cell(context, k);
        }
        return val;
    }

    protected abstract String _val(Object val);

}
