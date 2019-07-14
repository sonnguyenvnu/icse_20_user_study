private JSONArray getChildCommentIdList(long tid){
  JSONArray arr=new JSONArray();
  JSONRequest request=new JSONRequest();
  JSONRequest idItem=new JSONRequest();
  JSONRequest comment=new JSONRequest();
  comment.put("toId",tid);
  comment.setColumn("id");
  idItem.put("Comment",comment);
  request.putAll(idItem.toArray(0,0,"Comment-id"));
  JSONObject rp=new DemoParser().setNoVerify(true).parseResponse(request);
  JSONArray a=rp.getJSONArray("Comment-id[]");
  if (a != null) {
    arr.addAll(a);
    JSONArray a2;
    for (int i=0; i < a.size(); i++) {
      a2=getChildCommentIdList(a.getLongValue(i));
      if (a2 != null) {
        arr.addAll(a2);
      }
    }
  }
  return arr;
}
