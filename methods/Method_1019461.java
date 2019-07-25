private static final int find(final DirectHllArray host,final int slotNo){
  final int lgAuxArrInts=extractLgArr(host.mem);
  assert lgAuxArrInts < host.lgConfigK : lgAuxArrInts;
  final int auxInts=1 << lgAuxArrInts;
  final int auxArrMask=auxInts - 1;
  final int configKmask=(1 << host.lgConfigK) - 1;
  int probe=slotNo & auxArrMask;
  final int loopIndex=probe;
  do {
    final int arrVal=host.mem.getInt(host.auxStart + (probe << 2));
    if (arrVal == EMPTY) {
      return ~probe;
    }
 else     if (slotNo == (arrVal & configKmask)) {
      return probe;
    }
    final int stride=(slotNo >>> lgAuxArrInts) | 1;
    probe=(probe + stride) & auxArrMask;
  }
 while (probe != loopIndex);
  throw new SketchesArgumentException("Key not found and no empty slots!");
}
