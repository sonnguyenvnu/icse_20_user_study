private static int[] split(long value,int divider,int length){
  int[] result=new int[length];
  for (int i=0; i < length - 1; i++) {
    result[i]=(int)(value % divider);
    value/=divider;
  }
  result[length - 1]=(int)value;
  return result;
}
