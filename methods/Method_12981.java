public static List<Message> toArrayList(String jsonStr){
  List<Message> list=new ArrayList<Message>();
  try {
    JSONArray jsonArray=new JSONArray(jsonStr);
    for (int i=0; i < jsonArray.length(); i++) {
      Message m=new Message();
      JSONObject jsonObject=jsonArray.getJSONObject(i);
      m.setHandlerName(jsonObject.has(HANDLER_NAME_STR) ? jsonObject.getString(HANDLER_NAME_STR) : null);
      m.setCallbackId(jsonObject.has(CALLBACK_ID_STR) ? jsonObject.getString(CALLBACK_ID_STR) : null);
      m.setResponseData(jsonObject.has(RESPONSE_DATA_STR) ? jsonObject.getString(RESPONSE_DATA_STR) : null);
      m.setResponseId(jsonObject.has(RESPONSE_ID_STR) ? jsonObject.getString(RESPONSE_ID_STR) : null);
      m.setData(jsonObject.has(DATA_STR) ? jsonObject.getString(DATA_STR) : null);
      list.add(m);
    }
  }
 catch (  JSONException e) {
    e.printStackTrace();
  }
  return list;
}
