protected Entry<K,V> put(@NonNull K key,@NonNull V v){
  Entry<K,V> newEntry=new Entry<>(key,v);
  mSize++;
  if (mEnd == null) {
    mStart=newEntry;
    mEnd=mStart;
    return newEntry;
  }
  mEnd.mNext=newEntry;
  newEntry.mPrevious=mEnd;
  mEnd=newEntry;
  return newEntry;
}
