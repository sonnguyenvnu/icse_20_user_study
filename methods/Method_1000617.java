/** 
 * ?????????????? <b>?????JDK5???!!<b/>
 * @param arr ?????
 * @param is ??
 * @return ????????
 */
public static char[] join(char[] arr,char... is){
  if (null == arr)   return is;
  int length=arr.length + is.length;
  char[] re=new char[length];
  System.arraycopy(arr,0,re,0,arr.length);
  int i=arr.length;
  for (  char num : is)   re[i++]=num;
  return re;
}
