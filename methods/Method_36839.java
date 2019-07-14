@Override protected void parseHeaderCell(@NonNull MVHelper resolver,@Nullable JSONObject header){
  BaseCell mHeader=createCell(this,resolver,header,serviceManager,true);
  ensureBlock(mHeader);
}
