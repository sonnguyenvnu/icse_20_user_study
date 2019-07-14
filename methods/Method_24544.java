/** 
 * List the fonts known to the PDF renderer. This is like PFont.list(), however not all those fonts are available by default.
 */
static public String[] listFonts(){
  if (fontList == null) {
    HashMap<?,?> map=getMapper().getAliases();
    fontList=new String[map.size()];
    int count=0;
    for (    Object key : map.keySet()) {
      fontList[count++]=(String)key;
    }
    fontList=PApplet.sort(fontList);
  }
  return fontList;
}
