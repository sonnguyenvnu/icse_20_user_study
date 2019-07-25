/** 
 * ?????????????? <b>?????JDK5???!!<b/>
 * @param arr ?????
 * @param is ??
 * @return ????????
 */
public static long[] join(long[] arr,long... is){
  if (null == arr)   return is;
  int length=arr.length + is.length;
  long[] re=new long[length];
  System.arraycopy(arr,0,re,0,arr.length);
  int i=arr.length;
  for (  long num : is)   re[i++]=num;
  return re;
}
