/** 
 * ??????
 * @param name ????
 * @param style ???? ???Font.PLAIN Font.BOLD Font.ITALIC
 * @param size ????
 * @return ??
 */
public static Font get(String name,int style,int size){
  if (Strings.isBlank(name)) {
    Font ff=find(commonFonts,style,size);
    if (ff == null) {
      throw new RuntimeException("Please manually set the font, or add some common fonts in the system");
    }
    return ff;
  }
  return new Font(name,style,size);
}
