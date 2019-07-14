@Override public void parseWith(@NonNull JSONObject data,@NonNull MVHelper resolver){
  if (cell == null)   cell=new BannerCell();
  super.parseWith(data,resolver);
  JSONObject obj=new JSONObject();
  try {
    obj.put("type",TangramBuilder.TYPE_CAROUSEL_CELL);
    obj.put("bizId",id);
    resolver.parseCell(cell,obj);
    if (!super.getCells().isEmpty()) {
      cell.mCells.addAll(super.getCells());
      for (int i=0, size=cell.mCells.size(); i < size; i++) {
        try {
          BaseCell item=cell.mCells.get(i);
          item.extras.put(MVResolver.KEY_INDEX,item.pos);
        }
 catch (        JSONException e) {
        }
      }
      super.setCells(Collections.<BaseCell>singletonList(cell));
    }
  }
 catch (  Exception e) {
    e.printStackTrace();
    setCells(null);
  }
}
