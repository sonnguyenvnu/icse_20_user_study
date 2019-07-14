/** 
 * ??????
 * @param idA ?????id
 * @param idB ?????id
 * @return ????
 */
public static int getBiFrequency(int idA,int idB){
  if (idA < 0) {
    return -idA;
  }
  if (idB < 0) {
    return -idB;
  }
  int index=binarySearch(pair,start[idA],start[idA + 1] - start[idA],idB);
  if (index < 0)   return 0;
  index<<=1;
  return pair[index + 1];
}
