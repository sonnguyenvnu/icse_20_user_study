public void ensureBlock(BaseCell cell){
  if (cell.isValid()) {
    if (cell.style.extras == null) {
      cell.style.extras=new JSONObject();
    }
    cell.gridDisplayType=BaseCell.GridDisplayType.block;
  }
}
