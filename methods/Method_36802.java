protected void parseStyle(BaseCell cell,@Nullable JSONObject json){
  if (!Utils.isCard(cell.extras)) {
    cell.style=new Style();
    if (json != null) {
      cell.style.parseWith(json);
      cell.parseStyle(json);
    }
  }
}
