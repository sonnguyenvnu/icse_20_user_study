/** 
 * ??????
 * @param tmpl ?????
 * @return ????????
 * @see #parse(String,Pattern,int,int)
 */
public static Tmpl parse(String tmpl){
  if (null == tmpl)   return null;
  return new Tmpl(tmpl,null,-1,-1,null);
}
