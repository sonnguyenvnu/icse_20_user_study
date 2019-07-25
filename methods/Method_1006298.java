/** 
 * Given a word w, return the position of the jth true bit.
 * @param w word
 * @param j index
 * @return position of jth true bit in w
 */
public static int select(short w,int j){
  int sumtotal=0;
  for (int counter=0; counter < 16; ++counter) {
    sumtotal+=(w >> counter) & 1;
    if (sumtotal > j)     return counter;
  }
  throw new IllegalArgumentException("cannot locate " + j + "th bit in " + w + " weight is " + Integer.bitCount(w));
}
