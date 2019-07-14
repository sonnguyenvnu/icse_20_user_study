/** 
 * ????????????????????????
 * @param aggr ?????
 * @param str  ????????
 * @return
 */
public static boolean isInAggregate(String aggr,String str){
  if (aggr != null && str != null) {
    str+="1";
    for (int i=0; i < str.length(); i++) {
      String s=str.substring(i,i + 1);
      if (aggr.indexOf(s) == -1)       return false;
    }
    return true;
  }
  return false;
}
