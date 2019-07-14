public final long longValue() throws NumberFormatException {
  long result=0;
  boolean negative=false;
  long limit;
  int digit;
  if (np == -1) {
    np=0;
  }
  int i=np, max=np + sp;
  if (charAt(np) == '-') {
    negative=true;
    limit=Long.MIN_VALUE;
    i++;
  }
 else {
    limit=-Long.MAX_VALUE;
  }
  long multmin=MULTMIN_RADIX_TEN;
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
