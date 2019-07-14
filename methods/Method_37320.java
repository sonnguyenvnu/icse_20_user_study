@Override protected String[] convertStringToArray(final String value){
  String[] strings=StringUtil.splitc(value,NUMBER_DELIMITERS);
  int count=0;
  for (int i=0; i < strings.length; i++) {
    strings[count]=strings[i].trim();
    if (strings[count].length() == 0) {
      continue;
    }
    if (!strings[count].startsWith(StringPool.HASH)) {
      count++;
    }
  }
  if (count != strings.length) {
    return ArraysUtil.subarray(strings,0,count);
  }
  return strings;
}
