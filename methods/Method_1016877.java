public final int location(int index){
  if (indices == null)   return index;
  if (size - 1 != maxSortedIndex)   sortIndices();
  for (int i=0; i < size; i++) {
    if (indices[i] == index)     return i;
 else     if (indices[i] > index)     return -1;
  }
  return -1;
}
