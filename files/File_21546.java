package org.nlpcn.es4sql.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nlpcn.es4sql.Util;

/**
 * �?�索域
 * 
 * @author ansj
 *
 */
public class MethodField extends Field {
	private List<KVValue> params = null;
	private String option; //zhongshu-comment 暂时�?�用于DISTINCT去�?查询

	public MethodField(String name, List<KVValue> params, String option, String alias) {
		super(name, alias);
		this.params = params;
		this.option = option;
		if (alias==null||alias.trim().length()==0) {
            Map<String, Object> paramsAsMap = this.getParamsAsMap();
            if(paramsAsMap.containsKey("alias")){
                this.setAlias(paramsAsMap.get("alias").toString());
            }
            else {
                this.setAlias(this.toString());
            }
		}
	}

	public List<KVValue> getParams() {
		return params;
	}

    public Map<String,Object> getParamsAsMap(){
        Map<String,Object> paramsAsMap = new HashMap<>();
        if(this.params == null ) return paramsAsMap;
        for(KVValue kvValue : this.params){
            paramsAsMap.put(kvValue.key,kvValue.value);
        }
        return paramsAsMap;
    }

    //zhongshu-comment 在这里拼上script(....)
	@Override
	public String toString() {
		if (option != null) {
			return this.name + "(" + option + " " + Util.joiner(params, ",") + ")";
		}
		return this.name + "(" + Util.joiner(params, ",") + ")";//zhongshu-comment 报错
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

    @Override
    public boolean isNested() {
        Map<String, Object> paramsAsMap = this.getParamsAsMap();
        return paramsAsMap.containsKey("nested") || paramsAsMap.containsKey("reverse_nested");
    }

    @Override
    public boolean isReverseNested() {
        return this.getParamsAsMap().containsKey("reverse_nested");

    }

    @Override
    public String getNestedPath() {
        if(!this.isNested()) return null;
        if(this.isReverseNested()){
            String reverseNestedPath = this.getParamsAsMap().get("reverse_nested").toString();
            return reverseNestedPath.isEmpty() ? null : reverseNestedPath;
        }
        return this.getParamsAsMap().get("nested").toString();
    }

    @Override
    public boolean isChildren() {
        Map<String, Object> paramsAsMap = this.getParamsAsMap();
        return paramsAsMap.containsKey("children");
    }

    @Override
    public String getChildType() {
        if(!this.isChildren()) return null;

        return this.getParamsAsMap().get("children").toString();
    }
}
