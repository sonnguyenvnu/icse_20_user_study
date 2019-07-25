private static final void grow(final DirectHllArray host,final int oldLgAuxArrInts){
  final int oldAuxArrInts=1 << oldLgAuxArrInts;
  final int[] oldIntArray=new int[oldAuxArrInts];
  host.wmem.getIntArray(host.auxStart,oldIntArray,0,oldAuxArrInts);
  insertLgArr(host.wmem,oldLgAuxArrInts + 1);
  final long newAuxBytes=oldAuxArrInts << 3;
  final long requestBytes=host.auxStart + newAuxBytes;
  final long oldCapBytes=host.wmem.getCapacity();
  if (requestBytes > oldCapBytes) {
    final MemoryRequestServer svr=host.wmem.getMemoryRequestServer();
    final WritableMemory newWmem=svr.request(requestBytes);
    host.wmem.copyTo(0,newWmem,0,host.auxStart);
    newWmem.clear(host.auxStart,newAuxBytes);
    svr.requestClose(host.wmem,newWmem);
    host.updateMemory(newWmem);
  }
  final int configKmask=(1 << host.lgConfigK) - 1;
  for (int i=0; i < oldAuxArrInts; i++) {
    final int fetched=oldIntArray[i];
    if (fetched != EMPTY) {
      final int index=find(host,fetched & configKmask);
      host.wmem.putInt(host.auxStart + (~index << 2),fetched);
    }
  }
}
