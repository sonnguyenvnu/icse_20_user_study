private List<byte[]> getByteLists(byte[] approvalKey,RedisConnection conn){
  List<byte[]> byteList;
  Long size=conn.sCard(approvalKey);
  byteList=new ArrayList<byte[]>(size.intValue());
  Cursor<byte[]> cursor=conn.sScan(approvalKey,ScanOptions.NONE);
  while (cursor.hasNext()) {
    byteList.add(cursor.next());
  }
  return byteList;
}
