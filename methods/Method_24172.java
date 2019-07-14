protected void noLightFalloff(int num){
  lightFalloffCoefficients[3 * num + 0]=1;
  lightFalloffCoefficients[3 * num + 1]=0;
  lightFalloffCoefficients[3 * num + 2]=0;
}
