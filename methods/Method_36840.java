@Override protected void parseFooterCell(@NonNull MVHelper resolver,@Nullable JSONObject footer){
  BaseCell mFooter=createCell(this,resolver,footer,serviceManager,true);
  ensureBlock(mFooter);
}
