/** 
 * For a given address type, extract the recipients from the headers.
 * @param addressType     can be PduHeaders.FROM, PduHeaders.TO or PduHeaders.CC
 * @param recipients      a HashSet that is loaded with the recipients from the FROM, TO or CC headers
 * @param addressMap      a HashMap of the addresses from the ADDRESS_FIELDS header
 * @param excludeMyNumber if true, the number of this phone will be excluded from recipients
 */
private void loadRecipients(int addressType,HashSet<String> recipients,HashMap<Integer,EncodedStringValue[]> addressMap,boolean excludeMyNumber){
  EncodedStringValue[] array=addressMap.get(addressType);
  if (array == null) {
    return;
  }
  if (excludeMyNumber && array.length == 1 && addressType == PduHeaders.TO) {
    return;
  }
  String myNumber=excludeMyNumber ? mTelephonyManager.getLine1Number() : null;
  for (  EncodedStringValue v : array) {
    if (v != null) {
      String number=v.getString();
      if ((myNumber == null || !PhoneNumberUtils.compare(number,myNumber)) && !recipients.contains(number)) {
        recipients.add(number);
      }
    }
  }
}
