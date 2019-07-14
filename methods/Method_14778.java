/** 
 * ??string,?null???""
 * @param array
 * @param split
 * @param ignoreEmptyItem
 * @return
 */
public static String getString(Object[] array,String split,boolean ignoreEmptyItem){
  String s="";
  if (array != null) {
    if (split == null) {
      split=",";
    }
    for (int i=0; i < array.length; i++) {
      if (ignoreEmptyItem && isEmpty(array[i],true)) {
        continue;
      }
      s+=((i > 0 ? split : "") + array[i]);
    }
  }
  return getString(s);
}
