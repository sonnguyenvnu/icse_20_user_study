private ValueDescriptor<Entry<K,V>> wrapValueDescriptor(final ValueDescriptor<V> evictableValueDescriptor){
  return new ValueDescriptor<Entry<K,V>>(){
    @Override public int getSizeInBytes(    Entry<K,V> entry){
      return evictableValueDescriptor.getSizeInBytes(entry.valueRef.get());
    }
  }
;
}
