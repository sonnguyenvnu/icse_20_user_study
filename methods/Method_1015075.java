@Override public MessagePayload encode(){
  MessagePayload payload=new MessagePayload();
  try {
    JSONObject objWrite=new JSONObject();
    objWrite.put("g",groupId);
    objWrite.put("o",operator);
    JSONArray objArray=new JSONArray();
    for (int i=0; i < kickedMembers.size(); i++) {
      objArray.put(i,kickedMembers.get(i));
    }
    objWrite.put("ms",objArray);
    payload.binaryContent=objWrite.toString().getBytes();
  }
 catch (  JSONException e) {
    e.printStackTrace();
  }
  return payload;
}
