public static double trigamma(double z){
  int shift=0;
  while (z < 2) {
    z++;
    shift++;
  }
  double oneOverZ=1.0 / z;
  double oneOverZSquared=oneOverZ * oneOverZ;
  double result=oneOverZ + 0.5 * oneOverZSquared + 0.1666667 * oneOverZSquared * oneOverZ - 0.03333333 * oneOverZSquared * oneOverZSquared * oneOverZ + 0.02380952 * oneOverZSquared * oneOverZSquared * oneOverZSquared * oneOverZ - 0.03333333 * oneOverZSquared * oneOverZSquared * oneOverZSquared * oneOverZSquared * oneOverZ;
  while (shift > 0) {
    shift--;
    z--;
    result+=1.0 / (z * z);
  }
  return result;
}
