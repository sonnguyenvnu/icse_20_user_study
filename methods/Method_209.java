static boolean[] generatePrimeNumbers(){
  int number=(int)Math.sqrt(Integer.MAX_VALUE);
  boolean[] isPrime=new boolean[number + 1];
  for (int i=2; i < number + 1; i++) {
    isPrime[i]=true;
  }
  for (int factor=2; factor * factor < number + 1; factor++) {
    if (isPrime[factor]) {
      for (int j=factor; j * factor < number + 1; j++) {
        isPrime[j * factor]=false;
      }
    }
  }
  return isPrime;
}
