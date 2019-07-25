/** 
 * Given a word w, return the position of the jth true bit.
 * @param w word
 * @param j index
 * @return position of jth true bit in w
 */
public static int select(long w,int j){
  int seen=0;
  int part=(int)(w & 0xFFFFFFFF);
  int n=Integer.bitCount(part);
  if (n <= j) {
    part=(int)(w >>> 32);
    seen+=32;
    j-=n;
  }
  int ww=part;
  part=ww & 0xFFFF;
  n=Integer.bitCount(part);
  if (n <= j) {
    part=ww >>> 16;
    seen+=16;
    j-=n;
  }
  ww=part;
  part=ww & 0xFF;
  n=Integer.bitCount(part);
  if (n <= j) {
    part=ww >>> 8;
    seen+=8;
    j-=n;
  }
  ww=part;
  int counter;
  for (counter=0; counter < 8; counter++) {
    j-=(ww >>> counter) & 1;
    if (j < 0) {
      break;
    }
  }
  return seen + counter;
}
