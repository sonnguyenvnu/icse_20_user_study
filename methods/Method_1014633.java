/** 
 * ??????
 * @param c ????
 * @param policy ??
 */
public Configure plugin(char c,RenderPolicy policy){
  defaultPolicys.put(Character.valueOf(c),policy);
  return this;
}
