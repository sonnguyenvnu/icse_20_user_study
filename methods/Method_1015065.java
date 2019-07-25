@Override public MessagePayload encode(){
  MessagePayload payload=new MessagePayload();
  try {
    JSONObject objWrite=new JSONObject();
    objWrite.put("g",groupId);
    objWrite.put("o",invitor);
    JSONArray objArray=new JSONArray();
    for (int i=0; i < invitees.size(); i++) {
      objArray.put(i,invitees.get(i));
    }
    objWrite.put("ms",objArray);
    payload.binaryContent=objWrite.toString().getBytes();
    return payload;
  }
 catch (  JSONException e) {
    e.printStackTrace();
  }
  return null;
}
