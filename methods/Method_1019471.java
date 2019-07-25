static final HeapAuxHashMap heapify(final Memory mem,final long offset,final int lgConfigK,final int auxCount,final boolean srcCompact){
  final int lgAuxArrInts;
  final HeapAuxHashMap auxMap;
  if (srcCompact) {
    lgAuxArrInts=PreambleUtil.computeLgArr(mem,auxCount,lgConfigK);
  }
 else {
    lgAuxArrInts=extractLgArr(mem);
  }
  auxMap=new HeapAuxHashMap(lgAuxArrInts,lgConfigK);
  final int configKmask=(1 << lgConfigK) - 1;
  if (srcCompact) {
    for (int i=0; i < auxCount; i++) {
      final int pair=extractInt(mem,offset + (i << 2));
      final int slotNo=HllUtil.getLow26(pair) & configKmask;
      final int value=HllUtil.getValue(pair);
      auxMap.mustAdd(slotNo,value);
    }
  }
 else {
    final int auxArrInts=1 << lgAuxArrInts;
    for (int i=0; i < auxArrInts; i++) {
      final int pair=extractInt(mem,offset + (i << 2));
      if (pair == EMPTY) {
        continue;
      }
      final int slotNo=HllUtil.getLow26(pair) & configKmask;
      final int value=HllUtil.getValue(pair);
      auxMap.mustAdd(slotNo,value);
    }
  }
  return auxMap;
}
