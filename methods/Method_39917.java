@Override public String toString(){
  return count + ":" + (isFirst() ? 'F' : '_') + ':' + (last ? 'L' : '_') + ':' + getModulus();
}
