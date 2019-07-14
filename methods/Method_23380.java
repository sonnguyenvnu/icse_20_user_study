protected void backgroundFromCalc(){
  backgroundR=calcR;
  backgroundG=calcG;
  backgroundB=calcB;
  backgroundA=(format == RGB) ? 1 : calcA;
  backgroundRi=calcRi;
  backgroundGi=calcGi;
  backgroundBi=calcBi;
  backgroundAi=(format == RGB) ? 255 : calcAi;
  backgroundAlpha=(format == RGB) ? false : calcAlpha;
  backgroundColor=calcColor;
  backgroundImpl();
}
