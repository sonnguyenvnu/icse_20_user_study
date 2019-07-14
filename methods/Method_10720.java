/** 
 * ?????
 * @param value ??
 * @return
 */
public static String getAmountValue(String value){
  if (isNullString(value)) {
    return "0";
  }
  return AMOUNT_FORMAT.format(Double.parseDouble(value));
}
