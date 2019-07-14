protected void colorCalc(int rgb,float alpha){
  if (((rgb & 0xff000000) == 0) && (rgb <= colorModeX)) {
    colorCalc((float)rgb,alpha);
  }
 else {
    colorCalcARGB(rgb,alpha);
  }
}
