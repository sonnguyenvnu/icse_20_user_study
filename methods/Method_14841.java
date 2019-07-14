@Override public List<MomentItem> parseArray(String json){
  return new JSONResponse(json).getList(getCacheClass());
}
