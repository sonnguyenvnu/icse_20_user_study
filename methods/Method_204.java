private static boolean[] sieveOfEratosthenes(int number){
  boolean[] isPrime=new boolean[number + 1];
  for (int i=2; i < number + 1; i++) {
    isPrime[i]=true;
  }
  for (int factor=2; factor * factor <= number; factor++) {
    if (isPrime[factor]) {
      for (int j=factor; factor * j <= number; j++) {
        isPrime[factor * j]=false;
      }
    }
  }
  return isPrime;
}
