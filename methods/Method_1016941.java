/** 
 * Vastly inefficient O(x) method to compute cdf of B(n,p)
 */
public static double pbinom(int x,int n,double p){
  double sum=Double.NEGATIVE_INFINITY;
  for (int i=0; i <= x; i++) {
    sum=sumLogProb(sum,logBinom(i,n,p));
  }
  return Math.exp(sum);
}
