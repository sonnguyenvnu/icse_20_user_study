private void closeSource(int requestId){
  DataSource source=mRequestSourceMap.remove(requestId);
  if (source != null) {
    source.close();
  }
}
