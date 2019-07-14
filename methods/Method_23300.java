/** 
 * Set the native complement of this font. Might be set internally via the findFont() function, or externally by a deriveFont() call if the font is resized by PGraphicsJava2D.
 */
public void setNative(Object font){
  this.font=(Font)font;
}
