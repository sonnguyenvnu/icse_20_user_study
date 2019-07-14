/** 
 * ??????????
 * @param s
 * @return
 */
public static int[] stringToInts(String s){
  int[] n=new int[s.length()];
  if (RxDataTool.isNullString(s)) {
  }
 else {
    for (int i=0; i < s.length(); i++) {
      n[i]=Integer.parseInt(s.substring(i,i + 1));
    }
  }
  return n;
}
