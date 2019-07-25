/** 
 * Resizes existing hash array into a larger one within a single Memory assuming enough space. This assumes a Memory preamble of standard form with the correct value of thetaLong. The Memory lgArrLongs will change. Afterwards, the caller must update local copies of lgArrLongs and hashTableThreshold from Memory.
 * @param mem the Memory
 * @param preambleLongs the size of the preamble in longs
 * @param srcLgArrLongs the size of the source hash table
 * @param tgtLgArrLongs the LgArrLongs value for the new hash table
 */
static final void resize(final WritableMemory mem,final int preambleLongs,final int srcLgArrLongs,final int tgtLgArrLongs){
  final int preBytes=preambleLongs << 3;
  final int srcHTLen=1 << srcLgArrLongs;
  final long[] srcHTArr=new long[srcHTLen];
  mem.getLongArray(preBytes,srcHTArr,0,srcHTLen);
  final int dstHTLen=1 << tgtLgArrLongs;
  final long[] dstHTArr=new long[dstHTLen];
  final long thetaLong=extractThetaLong(mem);
  HashOperations.hashArrayInsert(srcHTArr,dstHTArr,tgtLgArrLongs,thetaLong);
  mem.putLongArray(preBytes,dstHTArr,0,dstHTLen);
  insertLgArrLongs(mem,tgtLgArrLongs);
}
