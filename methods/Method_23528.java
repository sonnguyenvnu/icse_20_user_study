protected void colorCalcARGB(int argb,float alpha){
  if (alpha == colorModeA) {
    calcAi=(argb >> 24) & 0xff;
    calcColor=argb;
  }
 else {
    calcAi=(int)(((argb >> 24) & 0xff) * (alpha / colorModeA));
    calcColor=(calcAi << 24) | (argb & 0xFFFFFF);
  }
  calcRi=(argb >> 16) & 0xff;
  calcGi=(argb >> 8) & 0xff;
  calcBi=argb & 0xff;
  calcA=calcAi / 255.0f;
  calcR=calcRi / 255.0f;
  calcG=calcGi / 255.0f;
  calcB=calcBi / 255.0f;
  calcAlpha=(calcAi != 255);
}
