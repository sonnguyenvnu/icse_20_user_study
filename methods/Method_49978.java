private EncodedStringValue appendAddressType(EncodedStringValue address){
  EncodedStringValue temp=null;
  try {
    int addressType=checkAddressType(address.getString());
    temp=EncodedStringValue.copy(address);
    if (PDU_PHONE_NUMBER_ADDRESS_TYPE == addressType) {
      temp.appendTextString(STRING_PHONE_NUMBER_ADDRESS_TYPE.getBytes());
    }
 else     if (PDU_IPV4_ADDRESS_TYPE == addressType) {
      temp.appendTextString(STRING_IPV4_ADDRESS_TYPE.getBytes());
    }
 else     if (PDU_IPV6_ADDRESS_TYPE == addressType) {
      temp.appendTextString(STRING_IPV6_ADDRESS_TYPE.getBytes());
    }
  }
 catch (  NullPointerException e) {
    return null;
  }
  return temp;
}
