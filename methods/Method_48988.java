@Override public InternalVertex getVertex(int pos){
switch (pos) {
case 0:
    return start;
case 1:
  return end;
default :
throw new IllegalArgumentException("Invalid position: " + pos);
}
}
