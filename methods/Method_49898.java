/** 
 * Get a canonical national format phone number. If parsing fails, just return the original number.
 * @param telephonyManager
 * @param subId The SIM ID associated with this number
 * @param phoneText The input phone number text
 * @return The formatted number or the original phone number if failed to parse
 */
public static String getNationalNumber(TelephonyManager telephonyManager,int subId,String phoneText){
  final String country=getSimOrDefaultLocaleCountry(telephonyManager,subId);
  final PhoneNumberUtil phoneNumberUtil=PhoneNumberUtil.getInstance();
  final Phonenumber.PhoneNumber parsed=getParsedNumber(phoneNumberUtil,phoneText,country);
  if (parsed == null) {
    return phoneText;
  }
  return phoneNumberUtil.format(parsed,PhoneNumberUtil.PhoneNumberFormat.NATIONAL).replaceAll("\\D","");
}
