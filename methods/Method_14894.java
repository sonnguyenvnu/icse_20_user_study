@Override public List<User> parseArray(String json){
  return new JSONResponse(json).getList(getCacheClass().getSimpleName() + "[]",getCacheClass());
}
