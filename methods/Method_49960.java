/** 
 * Convert this object to a  {@link String}. If the encoding of the EncodedStringValue is null or unsupported, it will be treated as iso-8859-1 encoding.
 * @return The decoded String.
 */
public String getString(){
  if (CharacterSets.ANY_CHARSET == mCharacterSet) {
    return new String(mData);
  }
 else {
    try {
      String name=CharacterSets.getMimeName(mCharacterSet);
      return new String(mData,name);
    }
 catch (    UnsupportedEncodingException e) {
      if (LOCAL_LOGV) {
        Timber.v(e,e.getMessage());
      }
      try {
        return new String(mData,CharacterSets.MIMENAME_ISO_8859_1);
      }
 catch (      UnsupportedEncodingException f) {
        return new String(mData);
      }
    }
  }
}
