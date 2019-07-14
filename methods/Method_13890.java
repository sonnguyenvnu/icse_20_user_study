public String getUrl(){
  return tableId == null || exceptions.size() > 0 ? null : "https://www.google.com/fusiontables/DataSource?docid=" + tableId;
}
