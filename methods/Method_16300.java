@Override public Object getWriteJSONObject(){
  JSONObject jsonObject=new JSONObject();
  jsonObject.put("id",getId());
  jsonObject.put("name",getName());
  jsonObject.put("dictId",getDictId());
  jsonObject.put("value",getValue());
  jsonObject.put("text",getText());
  jsonObject.put("ordinal",getOrdinal());
  jsonObject.put("sortIndex",getSortIndex());
  jsonObject.put("path",getPath());
  jsonObject.put("mask",getMask());
  jsonObject.put("searchCode",getSearchCode());
  jsonObject.put("status",getStatus());
  jsonObject.put("describe",getDescribe());
  return jsonObject;
}
