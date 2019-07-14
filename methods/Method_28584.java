/** 
 * ?????????id ???getResId("icon", R.drawable.class);
 * @param variableName
 * @param c
 * @return
 */
public static int getResId(String variableName,Class<?> c){
  try {
    Field idField=c.getDeclaredField(variableName);
    return idField.getInt(idField);
  }
 catch (  Exception e) {
    e.printStackTrace();
    return -1;
  }
}
