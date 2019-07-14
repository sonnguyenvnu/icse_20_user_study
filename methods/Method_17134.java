<K1 extends K,V1 extends V>CacheWriter<K1,V1> getCacheWriter(){
  @SuppressWarnings("unchecked") CacheWriter<K1,V1> castedWriter=(CacheWriter<K1,V1>)writer;
  return (castedWriter == null) ? CacheWriter.disabledWriter() : castedWriter;
}
