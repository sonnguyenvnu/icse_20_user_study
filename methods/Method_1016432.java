@Override public Iterator<Map.Entry<byte[],Map<String,byte[]>>> iterator(){
  final Iterator<byte[]> k=this.keyIterator();
  return new Iterator<Map.Entry<byte[],Map<String,byte[]>>>(){
    @Override public boolean hasNext(){
      return k.hasNext();
    }
    @Override public Map.Entry<byte[],Map<String,byte[]>> next(){
      byte[] key=k.next();
      if (key == null)       return null;
      return new AbstractMap.SimpleImmutableEntry<byte[],Map<String,byte[]>>(key,AbstractMapStore.this.get(key));
    }
    @Override public void remove(){
      k.remove();
    }
  }
;
}
