private static int zzUnpackTrans(final String packed,final int offset,final int[] result){
  int i=0;
  int j=offset;
  int l=packed.length();
  while (i < l) {
    int count=packed.charAt(i++);
    int value=packed.charAt(i++);
    value--;
    do     result[j++]=value;
 while (--count > 0);
  }
  return j;
}
