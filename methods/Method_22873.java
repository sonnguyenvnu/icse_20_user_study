/** 
 * Set the HSB values based on the current RGB values.
 */
protected void updateHSB(){
  float hsb[]=new float[3];
  Color.RGBtoHSB(red,green,blue,hsb);
  hue=(int)(hsb[0] * 359.0f);
  saturation=(int)(hsb[1] * 99.0f);
  brightness=(int)(hsb[2] * 99.0f);
  hueField.setText(String.valueOf(hue));
  saturationField.setText(String.valueOf(saturation));
  brightnessField.setText(String.valueOf(brightness));
}
