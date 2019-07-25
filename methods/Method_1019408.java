private static float gaussian(float mu,float inverseSigma,int x){
  float z=(x - mu) * inverseSigma;
  return ((float)Math.exp(-z * z)) * inverseSigma;
}
