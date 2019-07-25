private static Long factorial(int n){
  if (n < 0) {
    return null;
  }
  long result=1;
  while (n > 0) {
    result*=n--;
  }
  return result;
}
