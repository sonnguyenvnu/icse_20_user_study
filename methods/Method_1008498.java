@Override public FD load(LeafReaderContext context){
  if (context.reader().getFieldInfos().fieldInfo(fieldName) == null) {
    return empty(context.reader().maxDoc());
  }
  try {
    FD fd=cache.load(context,this);
    return fd;
  }
 catch (  Exception e) {
    if (e instanceof ElasticsearchException) {
      throw (ElasticsearchException)e;
    }
 else {
      throw new ElasticsearchException(e);
    }
  }
}
