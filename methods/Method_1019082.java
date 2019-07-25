/** 
 * @param text Raw text.
 * @return Text node of raw text.
 */
public static Node raw(String text){
  TextHBox t=new TextHBox();
  addRaw(t,text);
  return t;
}
