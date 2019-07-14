/** 
 * ??string??????????
 * @param s
 * @param onlyStart ???????????????
 * @return
 */
public static String getNumber(String s,boolean onlyStart){
  if (isNotEmpty(s,true) == false) {
    return "";
  }
  String numberString="";
  String single;
  for (int i=0; i < s.length(); i++) {
    single=s.substring(i,i + 1);
    if (isNumer(single)) {
      numberString+=single;
    }
 else {
      if (onlyStart) {
        return numberString;
      }
    }
  }
  return numberString;
}
