/** 
 * ???????HTML??
 * @return
 */
public StringBuffer end(){
  StringBuffer resultSb=new StringBuffer();
  textInput(resultSb);
  codeInput(resultSb);
  tree(resultSb);
  initScriptResource(resultSb);
  return resultSb;
}
