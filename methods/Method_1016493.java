/** 
 * see if the given country in alpha-2 country code exists
 * @param code, the mnemonic of the country in alpha-2
 * @return true if the code exists
 */
public static final boolean exists(String code){
  return mapping.containsKey(code.toLowerCase());
}
