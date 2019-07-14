@Override protected void parseFooterCell(@NonNull MVHelper resolver,@Nullable JSONObject footer){
  cell.mFooter=createCell(this,resolver,footer,serviceManager,false);
  if (cell.mFooter.isValid()) {
    cell.mFooter.parent=this;
    cell.mFooter.parentId=id;
    cell.mFooter.pos=cell.mHeader.isValid() ? getCells().size() + 1 : getCells().size();
    try {
      cell.mFooter.extras.put(MVResolver.KEY_INDEX,cell.mFooter.pos);
    }
 catch (    JSONException e) {
    }
  }
}
