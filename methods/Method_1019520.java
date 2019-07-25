/** 
 * This implements a stateless, pair-wise union operation on ordered, CompactSketches that are either Heap-based or Direct. The returned sketch will be cutback to k if required, similar to the regular Union operation. If a cutback is required, the returned sketch will always be on the heap. If both inputs are null a null is returned. If either sketch is empty its Theta is ignored. If one is null the other is returned, which may be either Direct or heap-based if a cutback is required.
 * @param skA The first ordered, CompactSketch argument.
 * @param skB The second ordered, CompactSketch argument
 * @param k The upper bound of the number of entries to be retained by the sketch
 * @return the result as an ordered CompactSketch.
 */
@SuppressWarnings("null") public static CompactSketch union(final CompactSketch skA,final CompactSketch skB,final int k){
  final int swA, swB;
  if (skA == null) {
    swA=1;
  }
 else {
    checkOrdered(skA);
    swA=skA.isEmpty() ? 2 : 3;
  }
  if (skB == null) {
    swB=1;
  }
 else {
    checkOrdered(skB);
    swB=skB.isEmpty() ? 2 : 3;
  }
  final int sw=(swA << 2) | swB;
switch (sw) {
case 5:
{
      return null;
    }
case 6:
{
    final long thetaLong=skB.getThetaLong();
    return (thetaLong == Long.MAX_VALUE) ? skB : HeapCompactOrderedSketch.compact(new long[0],true,skB.getSeedHash(),0,Long.MAX_VALUE);
  }
case 7:
{
  return maybeCutback(skB,k);
}
case 9:
{
final long thetaLong=skA.getThetaLong();
return (thetaLong == Long.MAX_VALUE) ? skA : HeapCompactOrderedSketch.compact(new long[0],true,skA.getSeedHash(),0,Long.MAX_VALUE);
}
case 10:
{
final short seedHash=seedHashesCheck(skA,skB);
long thetaLong=skA.getThetaLong();
if (thetaLong == Long.MAX_VALUE) {
return skA;
}
thetaLong=skB.getThetaLong();
if (thetaLong == Long.MAX_VALUE) {
return skB;
}
return HeapCompactOrderedSketch.compact(new long[0],true,seedHash,0,Long.MAX_VALUE);
}
case 11:
{
seedHashesCheck(skA,skB);
return maybeCutback(skB,k);
}
case 13:
{
return maybeCutback(skA,k);
}
case 14:
{
seedHashesCheck(skA,skB);
return maybeCutback(skA,k);
}
case 15:
{
seedHashesCheck(skA,skB);
break;
}
}
final long thetaLongA=skA.getThetaLong();
final long thetaLongB=skB.getThetaLong();
long thetaLong=Math.min(thetaLongA,thetaLongB);
final long[] cacheA=(skA.hasMemory()) ? skA.getCache() : skA.getCache().clone();
final long[] cacheB=(skB.hasMemory()) ? skB.getCache() : skB.getCache().clone();
final int aLen=cacheA.length;
final int bLen=cacheB.length;
final long[] outCache=new long[aLen + bLen];
int indexA=0;
int indexB=0;
int indexOut=0;
long hashA=(aLen == 0) ? thetaLong : cacheA[indexA];
long hashB=(bLen == 0) ? thetaLong : cacheB[indexB];
while ((indexA < aLen) || (indexB < bLen)) {
if (hashA == hashB) {
if (hashA < thetaLong) {
if (indexOut >= k) {
thetaLong=hashA;
break;
}
outCache[indexOut++]=hashA;
hashA=(++indexA < aLen) ? cacheA[indexA] : thetaLong;
hashB=(++indexB < bLen) ? cacheB[indexB] : thetaLong;
continue;
}
break;
}
 else if (hashA < hashB) {
if (hashA < thetaLong) {
if (indexOut >= k) {
thetaLong=hashA;
break;
}
outCache[indexOut++]=hashA;
hashA=(++indexA < aLen) ? cacheA[indexA] : thetaLong;
continue;
}
break;
}
 else {
if (hashB < thetaLong) {
if (indexOut >= k) {
thetaLong=hashB;
break;
}
outCache[indexOut++]=hashB;
hashB=(++indexB < bLen) ? cacheB[indexB] : thetaLong;
continue;
}
break;
}
}
int curCount=indexOut;
final long[] outArr;
if (indexOut > k) {
outArr=Arrays.copyOf(outCache,k);
curCount=k;
}
 else {
outArr=Arrays.copyOf(outCache,curCount);
}
return createCompactSketch(outArr,false,skA.getSeedHash(),curCount,thetaLong,true,null);
}
