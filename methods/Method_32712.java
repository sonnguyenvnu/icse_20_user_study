/** 
 * @param zimap maps string ids to DateTimeZone objects.
 */
static void writeZoneInfoMap(DataOutputStream dout,Map<String,DateTimeZone> zimap) throws IOException {
  if (dout == null) {
    throw new IllegalArgumentException("DataOutputStream must not be null.");
  }
  Map<String,Short> idToIndex=new HashMap<String,Short>(zimap.size());
  TreeMap<Short,String> indexToId=new TreeMap<Short,String>();
  short count=0;
  for (  Entry<String,DateTimeZone> entry : zimap.entrySet()) {
    String id=(String)entry.getKey();
    if (!idToIndex.containsKey(id)) {
      Short index=Short.valueOf(count);
      idToIndex.put(id,index);
      indexToId.put(index,id);
      if (++count == 0) {
        throw new InternalError("Too many time zone ids");
      }
    }
    id=((DateTimeZone)entry.getValue()).getID();
    if (!idToIndex.containsKey(id)) {
      Short index=Short.valueOf(count);
      idToIndex.put(id,index);
      indexToId.put(index,id);
      if (++count == 0) {
        throw new InternalError("Too many time zone ids");
      }
    }
  }
  dout.writeShort(indexToId.size());
  for (  String id : indexToId.values()) {
    dout.writeUTF(id);
  }
  dout.writeShort(zimap.size());
  for (  Entry<String,DateTimeZone> entry : zimap.entrySet()) {
    String id=entry.getKey();
    dout.writeShort(idToIndex.get(id).shortValue());
    id=entry.getValue().getID();
    dout.writeShort(idToIndex.get(id).shortValue());
  }
}
