/** 
 * ??????????
 * @param snum
 * @return
 */
public static boolean isYearTime(String snum){
  if (snum != null) {
    int len=snum.length();
    String first=snum.substring(0,1);
    if (isAllSingleByte(snum) && (len == 4 || len == 2 && (cint(first) > 4 || cint(first) == 0)))     return true;
    if (isAllNum(snum) && (len >= 3 || len == 2 && "??????".indexOf(first) != -1))     return true;
    if (getCharCount("????????????????????",snum) == len && len >= 2)     return true;
    if (len == 4 && getCharCount("????",snum) == 2)     return true;
    if (len == 1 && getCharCount("??",snum) == 1)     return true;
    if (len == 2 && getCharCount("??????????",snum) == 1 && getCharCount("????????????",snum.substring(1)) == 1)     return true;
  }
  return false;
}
