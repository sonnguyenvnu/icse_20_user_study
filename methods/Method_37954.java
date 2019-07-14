/** 
 * Creates random string whose length is the number of characters specified. Characters are chosen from the multiple sets defined by range pairs. All ranges must be in acceding order.
 */
public String randomRanges(int count,final char... ranges){
  if (count == 0) {
    return StringPool.EMPTY;
  }
  int i=0;
  int len=0;
  final int[] lens=new int[ranges.length];
  while (i < ranges.length) {
    int gap=ranges[i + 1] - ranges[i] + 1;
    len+=gap;
    lens[i]=len;
    i+=2;
  }
  final char[] result=new char[count];
  while (count-- > 0) {
    char c=0;
    int r=rnd.nextInt(len);
    for (i=0; i < ranges.length; i+=2) {
      if (r < lens[i]) {
        r+=ranges[i];
        if (i != 0) {
          r-=lens[i - 2];
        }
        c=(char)r;
        break;
      }
    }
    result[count]=c;
  }
  return new String(result);
}
