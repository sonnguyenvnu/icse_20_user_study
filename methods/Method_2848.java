public static String convertId2String(long id){
  StringBuilder sbId=new StringBuilder(7);
  sbId.append((char)(id / (26 * 10 * 10 * 26 * 10 * 10) + 'A'));
  sbId.append((char)(id % (26 * 10 * 10 * 26 * 10 * 10) / (10 * 10 * 26 * 10 * 10) + 'a'));
  sbId.append((char)(id % (10 * 10 * 26 * 10 * 10) / (10 * 26 * 10 * 10) + '0'));
  sbId.append((char)(id % (10 * 26 * 10 * 10) / (26 * 10 * 10) + '0'));
  sbId.append((char)(id % (26 * 10 * 10) / (10 * 10) + 'A'));
  sbId.append((char)(id % (10 * 10) / (10) + '0'));
  sbId.append((char)(id % (10) / (1) + '0'));
  return sbId.toString();
}
