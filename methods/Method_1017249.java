@Override public AsyncFuture<WriteSuggest> write(final WriteSuggest.Request request){
  final Series s=request.getSeries();
  final Lock l=lock.writeLock();
  l.lock();
  try {
    series.add(s);
    keyIndex.put(s.getKey(),new KeyDocument(s.getKey(),s));
    for (    final String t : analyze(s.getKey())) {
      putEntry(keys,t,s.getKey());
    }
    for (    final Map.Entry<String,String> tag : s.getTags().entrySet()) {
      final TagId id=new TagId(tag.getKey(),tag.getValue());
      tagIndex.put(id,new TagDocument(id,s));
      for (      final String t : analyze(tag.getKey())) {
        putEntry(tagKeys,t,id);
      }
      for (      final String t : analyze(tag.getValue())) {
        putEntry(tagValues,t,id);
      }
    }
    return async.resolved(WriteSuggest.of());
  }
  finally {
    l.unlock();
  }
}
