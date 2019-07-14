@Override public List<CommentItem> parseArray(String json){
  return new JSONResponse(json).getList(CommentItem.class);
}
