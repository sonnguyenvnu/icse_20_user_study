public List<String> getMultiBulkReply(){
  return BuilderFactory.STRING_LIST.build(getBinaryMultiBulkReply());
}
