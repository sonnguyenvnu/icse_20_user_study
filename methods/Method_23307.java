/** 
 * ( begin auto-generated from PFont_list.xml ) Gets a list of the fonts installed on the system. The data is returned as a String array. This list provides the names of each font for input into <b>createFont()</b>, which allows Processing to dynamically format fonts. This function is meant as a tool for programming local applications and is not recommended for use in applets. ( end auto-generated )
 * @webref pfont
 * @usage application
 * @brief     Gets a list of the fonts installed on the system
 */
static public String[] list(){
  loadFonts();
  String list[]=new String[fonts.length];
  for (int i=0; i < list.length; i++) {
    list[i]=fonts[i].getName();
  }
  return list;
}
