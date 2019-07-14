protected void parseCell(@NonNull MVHelper resolver,@NonNull JSONObject data,@NonNull final BaseCell cell,boolean appended){
  resolver.parseCell(cell,data);
  if (appended && !addCellInternal(cell,false)) {
    if (TangramBuilder.isPrintLog())     LogUtils.w(TAG,"Parse invalid cell with data: " + data.toString());
  }
}
