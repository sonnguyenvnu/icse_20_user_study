private void update(final int red,final int green,final int blue,final int alpha){
  isValid(red,"red");
  isValid(green,"green");
  isValid(blue,"blue");
  isValid(alpha,"alpha");
  argb=((alpha & 0xFF) << 24) | ((red & 0xFF) << 16) | ((green & 0xFF) << 8) | ((blue & 0xFF) << 0);
}
