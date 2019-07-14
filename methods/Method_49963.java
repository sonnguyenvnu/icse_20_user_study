public static EncodedStringValue copy(EncodedStringValue value){
  if (value == null) {
    return null;
  }
  return new EncodedStringValue(value.mCharacterSet,value.mData);
}
