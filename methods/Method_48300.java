final KeyValueEntry concatenate(StaticBuffer front,Entry entry){
  return new KeyValueEntry(concatenate(front,entry.getColumnAs(StaticBuffer.STATIC_FACTORY)),entry.getValueAs(StaticBuffer.STATIC_FACTORY));
}
