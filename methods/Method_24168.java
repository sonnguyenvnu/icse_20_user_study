protected void lightDiffuse(int num,float r,float g,float b){
  colorCalc(r,g,b);
  lightDiffuse[3 * num + 0]=calcR;
  lightDiffuse[3 * num + 1]=calcG;
  lightDiffuse[3 * num + 2]=calcB;
}
