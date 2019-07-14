private static boolean isAPerfectSquare(long number){
  long squareRoot=(long)Math.sqrt(number);
  return squareRoot * squareRoot == number;
}
