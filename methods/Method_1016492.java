/** 
 * get the name of the alpha-2 country code
 * @param code, the mnemonic of the country in alpha-2
 * @return the name of the country
 */
public static final String country(String code){
  return mapping.get(code.toLowerCase());
}
