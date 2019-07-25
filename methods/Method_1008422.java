private void reset(int code,long id){
  assert assertConsistent(id,code);
  final long slot=slot(code,mask);
  for (long index=slot; ; index=nextSlot(index,mask)) {
    final long curId=id(index);
    if (curId == -1) {
      id(index,id);
      break;
    }
  }
}
