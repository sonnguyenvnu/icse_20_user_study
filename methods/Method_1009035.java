public List<Object> apply(Object o,List contentList,int index){
  if (o instanceof CTAltChunk) {
    getAltChunks().add(new LocatedChunk((CTAltChunk)o,contentList,index));
  }
  return null;
}
