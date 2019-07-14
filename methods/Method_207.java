private static String convertToBinary(int number){
  StringBuilder s=new StringBuilder("");
  while (number != 0) {
    s=s.append(number % 2);
    number=number / 2;
  }
  return s.reverse().toString();
}
