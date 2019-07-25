/** 
 * ???? [0,n)?????int?
 * @param n
 * @return
 */
public static int random(int n){
  if (n <= 0)   throw new IllegalArgumentException("Parameter N must be positive");
  return random.nextInt(n);
}
