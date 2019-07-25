private static final int find(final int[] auxArr,final int lgAuxArrInts,final int lgConfigK,final int slotNo){
  assert lgAuxArrInts < lgConfigK;
  final int auxArrMask=(1 << lgAuxArrInts) - 1;
  final int configKmask=(1 << lgConfigK) - 1;
  int probe=slotNo & auxArrMask;
  final int loopIndex=probe;
  do {
    final int arrVal=auxArr[probe];
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
