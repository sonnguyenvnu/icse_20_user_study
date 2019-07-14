protected void lightFalloff(int num,float c0,float c1,float c2){
  lightFalloffCoefficients[3 * num + 0]=c0;
  lightFalloffCoefficients[3 * num + 1]=c1;
  lightFalloffCoefficients[3 * num + 2]=c2;
}
