private static long computeFactorial(int number){
  long product=1;
  for (int i=2; i < number + 1; i++) {
    product=product * i;
  }
  return product;
}
