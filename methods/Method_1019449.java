/** 
 * Computes an approximation to the erf() function.
 * @param x is the input to the erf function
 * @return returns erf(x), accurate to roughly 7 decimal digits.
 */
public static double erf(final double x){
  if (x < 0.0) {
    return (-1.0 * (erf_of_nonneg(-1.0 * x)));
  }
 else {
    return (erf_of_nonneg(x));
  }
}
