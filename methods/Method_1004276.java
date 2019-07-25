@Override public boolean add(SelectionKey o){
  if (o == null) {
    return false;
  }
  if (isA) {
    int size=keysASize;
    keysA[size++]=o;
    keysASize=size;
    if (size == keysA.length) {
      doubleCapacityA();
    }
  }
 else {
    int size=keysBSize;
    keysB[size++]=o;
    keysBSize=size;
    if (size == keysB.length) {
      doubleCapacityB();
    }
  }
  return true;
}
