public static long convertString2Id(String idString){
  long id;
  id=(idString.charAt(0) - 'A') * 26L * 10 * 10 * 26 * 10 * 10 + (idString.charAt(1) - 'a') * 10 * 10 * 26 * 10 * 10 + (idString.charAt(2) - '0') * 10 * 26 * 10 * 10 + (idString.charAt(3) - '0') * 26 * 10 * 10 + (idString.charAt(4) - 'A') * 10 * 10 + (idString.charAt(5) - '0') * 10 + (idString.charAt(6) - '0');
  return id;
}
