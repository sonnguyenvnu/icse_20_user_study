private static String russianPassportTranslit(String s){
  final String cyrillic="?????????????????????????????????";
  final String latin="ABVGDE2JZIQKLMNOPRSTUFHC34WXY9678";
  char[] chars=s.toCharArray();
  for (int i=0; i < chars.length; i++) {
    int idx=latin.indexOf(chars[i]);
    if (idx != -1)     chars[i]=cyrillic.charAt(idx);
  }
  return new String(chars);
}
