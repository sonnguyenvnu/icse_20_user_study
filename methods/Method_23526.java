protected void colorCalc(float gray,float alpha){
  if (gray > colorModeX)   gray=colorModeX;
  if (alpha > colorModeA)   alpha=colorModeA;
  if (gray < 0)   gray=0;
  if (alpha < 0)   alpha=0;
  calcR=colorModeScale ? (gray / colorModeX) : gray;
  calcG=calcR;
  calcB=calcR;
  calcA=colorModeScale ? (alpha / colorModeA) : alpha;
  calcRi=(int)(calcR * 255);
  calcGi=(int)(calcG * 255);
  calcBi=(int)(calcB * 255);
  calcAi=(int)(calcA * 255);
  calcColor=(calcAi << 24) | (calcRi << 16) | (calcGi << 8) | calcBi;
  calcAlpha=(calcAi != 255);
}
