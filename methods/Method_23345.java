/** 
 * Used by PGraphics to remove the requirement for loading a font.
 */
protected PFont createDefaultFont(float size){
  Font baseFont=new Font("Lucida Sans",Font.PLAIN,1);
  return createFont(baseFont,size,true,null,false);
}
