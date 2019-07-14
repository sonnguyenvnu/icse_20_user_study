public static String formatShortNumber(int number,int[] rounded){
  StringBuilder K=new StringBuilder();
  int lastDec=0;
  int KCount=0;
  while (number / 1000 > 0) {
    K.append("K");
    lastDec=(number % 1000) / 100;
    number/=1000;
  }
  if (rounded != null) {
    double value=number + lastDec / 10.0;
    for (int a=0; a < K.length(); a++) {
      value*=1000;
    }
    rounded[0]=(int)value;
  }
  if (lastDec != 0 && K.length() > 0) {
    if (K.length() == 2) {
      return String.format(Locale.US,"%d.%dM",number,lastDec);
    }
 else {
      return String.format(Locale.US,"%d.%d%s",number,lastDec,K.toString());
    }
  }
  if (K.length() == 2) {
    return String.format(Locale.US,"%dM",number);
  }
 else {
    return String.format(Locale.US,"%d%s",number,K.toString());
  }
}
