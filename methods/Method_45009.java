/** 
 * Set the family name of the selected font.
 * @param name the family name of the selected font.
 * @see getSelectedFontFamily
 */
public void setSelectedFontFamily(String name){
  String[] names=getFontFamilies();
  for (int i=0; i < names.length; i++) {
    if (names[i].toLowerCase().equals(name.toLowerCase())) {
      getFontFamilyList().setSelectedIndex(i);
      break;
    }
  }
  updateSampleFont();
}
