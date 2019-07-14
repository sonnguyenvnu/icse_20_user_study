/** 
 * Set the RGB values based on a calculated ARGB int. Used by both updateRGB() to set the color from the HSB values, and by updateHex(), to unpack the hex colors and assign them.
 */
protected void updateRGB(int rgb){
  red=(rgb >> 16) & 0xff;
  green=(rgb >> 8) & 0xff;
  blue=rgb & 0xff;
  redField.setText(String.valueOf(red));
  greenField.setText(String.valueOf(green));
  blueField.setText(String.valueOf(blue));
}
