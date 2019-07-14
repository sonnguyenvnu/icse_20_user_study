/** 
 * Returns a "brighter" color.
 * @param c The color.
 * @return A brighter color.
 */
private Color getBrighterColor(Color c){
  if (brighterColors == null) {
    brighterColors=new HashMap<Color,Color>(5);
  }
  Color brighter=brighterColors.get(c);
  if (brighter == null) {
    int r=possiblyBrighter(c.getRed());
    int g=possiblyBrighter(c.getGreen());
    int b=possiblyBrighter(c.getBlue());
    brighter=new Color(r,g,b);
    brighterColors.put(c,brighter);
  }
  return brighter;
}
