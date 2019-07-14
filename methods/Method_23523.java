protected void colorCalc(int rgb){
  if (((rgb & 0xff000000) == 0) && (rgb <= colorModeX)) {
    colorCalc((float)rgb);
  }
 else {
    colorCalcARGB(rgb,colorModeA);
  }
}
