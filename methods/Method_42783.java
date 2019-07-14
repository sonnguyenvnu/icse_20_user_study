/** 
 * ???ABC-B2C$ABC-B2C
 * @param frpCode
 * @return
 */
public static boolean isCapitalBar(String frpCode){
  Pattern pattern=Pattern.compile("^[A-Z]+[-?][A-Z[22]]+(\\$[A-Z]+[-?][A-Z[22]]+)*");
  return pattern.matcher(frpCode).matches();
}
