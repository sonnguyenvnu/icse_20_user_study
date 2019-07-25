private static int trim(double number){
  return number > Short.MAX_VALUE ? Short.MAX_VALUE : (number < Short.MIN_VALUE ? Short.MIN_VALUE : (int)number);
}
