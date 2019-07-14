public static String printBinary(double num){
  if (num >= 1 || num <= 0) {
    return "ERROR";
  }
  StrinBuilder binary=new StringBuilder();
  binary.append(".");
  while (num > 0) {
    if (binary.length() >= 32) {
      return "ERROR";
    }
    double r=num * 2;
    if (r >= 1) {
      binary.append(1);
      num=r - 1;
    }
 else {
      binary.append(0);
      num=r;
    }
  }
  return binary.toString();
}
