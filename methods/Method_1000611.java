/** 
 * ??????, ??????????
 * @param arr ????
 * @return ???
 */
public static String[] permutation(int length,char... arr){
  if (arr == null || arr.length == 0 || length <= 0 || length > arr.length) {
    return null;
  }
  List<String> slist=new ArrayList<String>();
  char[] b=new char[length];
  getCombination(slist,arr,length,0,b,0);
  return slist.toArray(new String[]{});
}
