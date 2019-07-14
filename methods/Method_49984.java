/** 
 * Check address type.
 * @param address address string without the postfix stinng type,such as "/TYPE=PLMN", "/TYPE=IPv6" and "/TYPE=IPv4"
 * @return PDU_PHONE_NUMBER_ADDRESS_TYPE if it is phone number,PDU_EMAIL_ADDRESS_TYPE if it is email address, PDU_IPV4_ADDRESS_TYPE if it is ipv4 address, PDU_IPV6_ADDRESS_TYPE if it is ipv6 address, PDU_UNKNOWN_ADDRESS_TYPE if it is unknown.
 */
protected static int checkAddressType(String address){
  if (null == address) {
    return PDU_UNKNOWN_ADDRESS_TYPE;
  }
  if (address.matches(REGEXP_IPV4_ADDRESS_TYPE)) {
    return PDU_IPV4_ADDRESS_TYPE;
  }
 else   if (address.matches(REGEXP_PHONE_NUMBER_ADDRESS_TYPE)) {
    return PDU_PHONE_NUMBER_ADDRESS_TYPE;
  }
 else   if (address.matches(REGEXP_EMAIL_ADDRESS_TYPE)) {
    return PDU_EMAIL_ADDRESS_TYPE;
  }
 else   if (address.matches(REGEXP_IPV6_ADDRESS_TYPE)) {
    return PDU_IPV6_ADDRESS_TYPE;
  }
 else {
    return PDU_UNKNOWN_ADDRESS_TYPE;
  }
}
