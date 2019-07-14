/** 
 * judge name equals to  t.add / t.remove / t.clear
 * @param name
 * @param variableName
 * @return
 */
private boolean judgeName(String name,String variableName){
  return name.equals(variableName + ADD) || name.equals(variableName + REMOVE) || name.equals(variableName + CLEAR);
}
