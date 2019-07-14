/** 
 * ( begin auto-generated from loadFont.xml ) Loads a font into a variable of type <b>PFont</b>. To load correctly, fonts must be located in the data directory of the current sketch. To create a font to use with Processing, select "Create Font..." from the Tools menu. This will create a font in the format Processing requires and also adds it to the current sketch's data directory.<br /> <br /> Like <b>loadImage()</b> and other functions that load data, the <b>loadFont()</b> function should not be used inside <b>draw()</b>, because it will slow down the sketch considerably, as the font will be re-loaded from the disk (or network) on each frame.<br /> <br /> For most renderers, Processing displays fonts using the .vlw font format, which uses images for each letter, rather than defining them through vector data. When <b>hint(ENABLE_NATIVE_FONTS)</b> is used with the JAVA2D renderer, the native version of a font will be used if it is installed on the user's machine.<br /> <br /> Using <b>createFont()</b> (instead of loadFont) enables vector data to be used with the JAVA2D (default) renderer setting. This can be helpful when many font sizes are needed, or when using any renderer based on JAVA2D, such as the PDF library. ( end auto-generated )
 * @webref typography:loading_displaying
 * @param filename name of the font to load
 * @see PFont
 * @see PGraphics#textFont(PFont,float)
 * @see PApplet#createFont(String,float,boolean,char[])
 */
public PFont loadFont(String filename){
  if (!filename.toLowerCase().endsWith(".vlw")) {
    throw new IllegalArgumentException("loadFont() is for .vlw files, try createFont()");
  }
  try {
    InputStream input=createInput(filename);
    return new PFont(input);
  }
 catch (  Exception e) {
    die("Could not load font " + filename + ". " + "Make sure that the font has been copied " + "to the data folder of your sketch.",e);
  }
  return null;
}
