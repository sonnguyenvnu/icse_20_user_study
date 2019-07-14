protected void parseCell(BaseCell cell,JSONObject json){
  if (json != null) {
    cell.extras=json;
    cell.id=json.getString(KEY_BIZ_ID);
    if (TextUtils.isEmpty(cell.id) && json.containsKey(KEY_ID)) {
      cell.id=json.getString(KEY_ID);
    }
    cell.stringType=parseCellType(json);
    cell.typeKey=json.getString(KEY_TYPE_KEY);
    String reuseId=json.getString(KEY_TYPE_REUSEID);
    if (!TextUtils.isEmpty(reuseId)) {
      cell.typeKey=reuseId;
    }
    Integer position=json.getInteger(KEY_POSITION);
    if (position == null) {
      position=-1;
    }
    cell.position=position;
    JSONObject styleJson=json.getJSONObject(KEY_STYLE);
    Style style=new Style();
    cell.style=parseStyle(style,styleJson);
  }
 else {
    cell.extras=new JSONObject();
  }
}
