public static double parseDouble(String str){
  final int len=str.length();
  if (len > 10) {
    return Double.parseDouble(str);
  }
  boolean negative=false;
  long longValue=0;
  int scale=0;
  for (int i=0; i < len; ++i) {
    char ch=str.charAt(i);
    if (ch == '-' && i == 0) {
      negative=true;
      continue;
    }
    if (ch == '.') {
      if (scale != 0) {
        return Double.parseDouble(str);
      }
      scale=len - i - 1;
      continue;
    }
    if (ch >= '0' && ch <= '9') {
      int digit=ch - '0';
      longValue=longValue * 10 + digit;
    }
 else {
      return Double.parseDouble(str);
    }
  }
  if (negative) {
    longValue=-longValue;
  }
switch (scale) {
case 0:
    return (double)longValue;
case 1:
  return ((double)longValue) / 10;
case 2:
return ((double)longValue) / 100;
case 3:
return ((double)longValue) / 1000;
case 4:
return ((double)longValue) / 10000;
case 5:
return ((double)longValue) / 100000;
case 6:
return ((double)longValue) / 1000000;
case 7:
return ((double)longValue) / 10000000;
case 8:
return ((double)longValue) / 100000000;
case 9:
return ((double)longValue) / 1000000000;
}
return Double.parseDouble(str);
}
