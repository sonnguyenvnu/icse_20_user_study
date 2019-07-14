@Nullable private static Id3Frame parseUint8Attribute(int type,String id,ParsableByteArray data,boolean isTextInformationFrame,boolean isBoolean){
  int value=parseUint8AttributeValue(data);
  if (isBoolean) {
    value=Math.min(1,value);
  }
  if (value >= 0) {
    return isTextInformationFrame ? new TextInformationFrame(id,null,Integer.toString(value)) : new CommentFrame(LANGUAGE_UNDEFINED,id,Integer.toString(value));
  }
  Log.w(TAG,"Failed to parse uint8 attribute: " + Atom.getAtomTypeString(type));
  return null;
}
