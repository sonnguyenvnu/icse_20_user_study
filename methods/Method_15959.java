@Override default PagerResult<E> selectPager(Entity param){
  JSONObject result=createRequest("/",param).get().as(JSONObject.class);
  return PagerResult.of(result.getInteger("total"),result.getJSONArray("data").toJavaList(getEntityType()));
}
