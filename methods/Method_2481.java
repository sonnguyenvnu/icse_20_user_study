/** 
 * ???????p??????????????????????????JS??? http://www.fourmilab.ch/rpkp/experiments/analysis/chiCalc.js
 * @param p  p??????
 * @param df
 * @return
 */
public static double ChisquareInverseCdf(double p,int df){
  final double CHI_EPSILON=0.000001;
  final double CHI_MAX=99999.0;
  double minchisq=0.0;
  double maxchisq=CHI_MAX;
  double chisqval=0.0;
  if (p <= 0.0) {
    return CHI_MAX;
  }
 else   if (p >= 1.0) {
    return 0.0;
  }
  chisqval=df / Math.sqrt(p);
  while ((maxchisq - minchisq) > CHI_EPSILON) {
    if (1 - ChisquareCdf(chisqval,df) < p) {
      maxchisq=chisqval;
    }
 else {
      minchisq=chisqval;
    }
    chisqval=(maxchisq + minchisq) * 0.5;
  }
  return chisqval;
}
