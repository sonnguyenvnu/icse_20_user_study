public static String coverString(String str,double percent){
  if (str.length() == 1) {
    return "*";
  }
  if (percent > 1) {
    percent=percent / 100d;
  }
  percent=1 - percent;
  long size=Math.round(str.length() * percent);
  long end=(str.length() - size / 2);
  long start=str.length() - end;
  start=start == 0 && percent > 0 ? 1 : start;
  char[] chars=str.toCharArray();
  for (int i=0; i < chars.length; i++) {
    if (i >= start && i <= end - 1) {
      chars[i]='*';
    }
  }
  return new String(chars);
}
