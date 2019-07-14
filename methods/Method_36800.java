protected void parseCell(MVHelper resolver,BaseCell cell,JSONObject json){
  if (json != null) {
    cell.extras=json;
    cell.id=json.optString(KEY_BIZ_ID);
    if (TextUtils.isEmpty(cell.id) && json.has(KEY_ID)) {
      cell.id=json.optString(KEY_ID);
    }
    cell.type=json.optInt(KEY_TYPE);
    cell.stringType=json.optString(KEY_TYPE);
    cell.typeKey=json.optString(KEY_TYPE_KEY);
    String reuseId=json.optString(KEY_TYPE_REUSEID);
    if (!TextUtils.isEmpty(reuseId)) {
      cell.typeKey=reuseId;
    }
    cell.position=json.optInt(KEY_POSITION,-1);
    parseBizParams(cell,json);
    cell.parseWith(json);
    cell.parseWith(json,resolver);
    JSONObject styleJson=json.optJSONObject(KEY_STYLE);
    parseStyle(cell,styleJson);
    parseBizParams(cell,styleJson);
  }
 else {
    cell.extras=new JSONObject();
  }
}
