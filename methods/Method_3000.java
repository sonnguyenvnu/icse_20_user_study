public int getHead(int index){
  if (arcs[index] != null)   return arcs[index].headIndex;
  return -1;
}
