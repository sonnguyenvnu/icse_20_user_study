protected int getSegmentMapKey(Segment s,int off,int len){
  return (Character.toUpperCase(s.array[off]) + Character.toUpperCase(s.array[off + len - 1])) % MAP_LENGTH;
}
