@Override protected void parseHeaderCell(@NonNull MVHelper resolver,@Nullable JSONObject header){
  cell.mHeader=createCell(this,resolver,header,serviceManager,false);
  if (cell.mHeader.isValid()) {
    cell.mHeader.parent=this;
    cell.mHeader.parentId=id;
    cell.mHeader.pos=0;
    try {
      cell.mHeader.extras.put(MVResolver.KEY_INDEX,cell.mHeader.pos);
    }
 catch (    JSONException e) {
    }
  }
}
