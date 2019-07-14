static ResponseList<Relationship> createRelationshipList(HttpResponse res,Configuration conf) throws TwitterException {
  try {
    if (conf.isJSONStoreEnabled()) {
      TwitterObjectFactory.clearThreadLocalMap();
    }
    JSONArray list=res.asJSONArray();
    int size=list.length();
    ResponseList<Relationship> relationships=new ResponseListImpl<Relationship>(size,res);
    for (int i=0; i < size; i++) {
      JSONObject json=list.getJSONObject(i);
      Relationship relationship=new RelationshipJSONImpl(json);
      if (conf.isJSONStoreEnabled()) {
        TwitterObjectFactory.registerJSONObject(relationship,json);
      }
      relationships.add(relationship);
    }
    if (conf.isJSONStoreEnabled()) {
      TwitterObjectFactory.registerJSONObject(relationships,list);
    }
    return relationships;
  }
 catch (  JSONException jsone) {
    throw new TwitterException(jsone);
  }
}
