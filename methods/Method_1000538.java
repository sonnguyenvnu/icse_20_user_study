/** 
 * ??????
 * @param style ???? ???Font.PLAIN Font.BOLD Font.ITALIC
 * @param size ????
 * @return ??
 */
public static Font random(int style,int size){
  Font font=null;
  while (font == null) {
    try {
      int index=R.random(0,commonFonts.length - 1);
      font=get(commonFonts[index],style,size);
    }
 catch (    Exception e) {
    }
  }
  return font;
}
