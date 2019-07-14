/** 
 * Class[]?String[] <br> ??????String????????Class.forName????getClasses(String[])????
 * @param types Class[]
 * @param javaStyle JDK??????? int[], true???? [I; false????int[]            
 * @return ????
 * @see #getClasses(String[])
 */
public static String[] getTypeStrs(Class[] types,boolean javaStyle){
  if (CommonUtils.isEmpty(types)) {
    return StringUtils.EMPTY_STRING_ARRAY;
  }
 else {
    String[] strings=new String[types.length];
    for (int i=0; i < types.length; i++) {
      strings[i]=javaStyle ? types[i].getName() : getTypeStr(types[i]);
    }
    return strings;
  }
}
