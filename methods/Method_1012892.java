/** 
 * Loads the rules from a DateInputStream, often in a jar file.
 * @param dis  the DateInputStream to load, not null
 * @throws Exception if an error occurs
 */
@SuppressWarnings("PMD.SignatureDeclareThrowsException") private void load(DataInputStream dis) throws Exception {
  if (dis.readByte() != 1) {
    throw new StreamCorruptedException("File format not recognised");
  }
  String groupId=dis.readUTF();
  if (!"TZDB".equals(groupId)) {
    throw new StreamCorruptedException("File format not recognised");
  }
  int versionCount=dis.readShort();
  for (int i=0; i < versionCount; i++) {
    versionId=dis.readUTF();
  }
  int regionCount=dis.readShort();
  String[] regionArray=new String[regionCount];
  for (int i=0; i < regionCount; i++) {
    regionArray[i]=dis.readUTF();
  }
  regionIds=Arrays.asList(regionArray);
  int ruleCount=dis.readShort();
  Object[] ruleArray=new Object[ruleCount];
  for (int i=0; i < ruleCount; i++) {
    byte[] bytes=new byte[dis.readShort()];
    dis.readFully(bytes);
    ruleArray[i]=bytes;
  }
  for (int i=0; i < versionCount; i++) {
    int versionRegionCount=dis.readShort();
    regionToRules.clear();
    for (int j=0; j < versionRegionCount; j++) {
      String region=regionArray[dis.readShort()];
      Object rule=ruleArray[dis.readShort() & 0xffff];
      regionToRules.put(region,rule);
    }
  }
}
