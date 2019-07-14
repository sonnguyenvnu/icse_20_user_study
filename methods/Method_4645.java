@Nullable private static TextInformationFrame parseIndexAndCountAttribute(int type,String attributeName,ParsableByteArray data){
  int atomSize=data.readInt();
  int atomType=data.readInt();
  if (atomType == Atom.TYPE_data && atomSize >= 22) {
    data.skipBytes(10);
    int index=data.readUnsignedShort();
    if (index > 0) {
      String value="" + index;
      int count=data.readUnsignedShort();
      if (count > 0) {
        value+="/" + count;
      }
      return new TextInformationFrame(attributeName,null,value);
    }
  }
  Log.w(TAG,"Failed to parse index/count attribute: " + Atom.getAtomTypeString(type));
  return null;
}
