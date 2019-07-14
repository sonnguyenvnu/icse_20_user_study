public int getDependent(int index){
  if (arcs[index] != null)   return arcs[index].relationId;
  return -1;
}
