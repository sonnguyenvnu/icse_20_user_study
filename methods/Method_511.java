public final int intValue(){
  if (np == -1) {
    np=0;
  }
  int result=0;
  boolean negative=false;
  int i=np, max=np + sp;
  int limit;
  int digit;
  if (charAt(np) == '-') {
    negative=true;
    limit=Integer.MIN_VALUE;
    i++;
  }
 else {
    limit=-Integer.MAX_VALUE;
  }
  long multmin=INT_MULTMIN_RADIX_TEN;
  if (i < max) {
    digit=charAt(i++) - '0';
    result=-digit;
  }
  while (i < max) {
    char chLocal=charAt(i++);
    if (chLocal == 'L' || chLocal == 'S' || chLocal == 'B') {
      break;
    }
    digit=chLocal - '0';
    if (result < multmin) {
      throw new NumberFormatException(numberString());
    }
    result*=10;
    if (result < limit + digit) {
      throw new NumberFormatException(numberString());
    }
    result-=digit;
  }
  if (negative) {
    if (i > np + 1) {
      return result;
    }
 else {
      throw new NumberFormatException(numberString());
    }
  }
 else {
    return -result;
  }
}
