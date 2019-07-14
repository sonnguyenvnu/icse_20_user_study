protected void lightAmbient(int num,float r,float g,float b){
  colorCalc(r,g,b);
  lightAmbient[3 * num + 0]=calcR;
  lightAmbient[3 * num + 1]=calcG;
  lightAmbient[3 * num + 2]=calcB;
}
