public BytesArray entry(int index){
  Entry entry=entries.get(index);
  return new BytesArray(data.bytes,entry.offset,entry.length);
}
