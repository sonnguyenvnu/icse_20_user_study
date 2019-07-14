/** 
 * ????????????
 * @param currentAddress ????
 * @return
 */
public static String getAnonymousCurrentAddress(String currentAddress){
  if (StringUtils.hasLength(currentAddress) && currentAddress.length() > 4) {
    String last=currentAddress.substring(currentAddress.length() - 4,currentAddress.length());
    String stars="";
    for (int i=0; i < currentAddress.length() - 4; i++) {
      stars+="*";
    }
    return stars + last;
  }
  return currentAddress;
}
