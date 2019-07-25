public int location(int index){
  if (index2location == null)   setIndex2Location();
  if (index >= index2location.length)   return -1;
  return index2location[index];
}
