public int toInteger(){
  int packedList=0;
  int current=1;
  for (int i=0; i < 7; i++) {
    if (weekdays[i])     packedList|=current;
    current=current << 1;
  }
  return packedList;
}
