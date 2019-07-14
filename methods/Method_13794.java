/** 
 * internal method to add a new data block record
 * @param raf
 * @param startIp
 * @param endIp
 * @param region data
 */
private void addDataBlock(RandomAccessFile raf,String startIp,String endIp,String region){
  try {
    byte[] data=region.getBytes("UTF-8");
    int dataPtr=0;
    if (regionPtrPool.containsKey(region)) {
      DataBlock dataBlock=regionPtrPool.get(region);
      dataPtr=dataBlock.getDataPtr();
      System.out.println("dataPtr: " + dataPtr + ", region: " + region);
    }
 else {
      byte[] city=new byte[4];
      int city_id=getCityId(region);
      Util.writeIntLong(city,0,city_id);
      dataPtr=(int)raf.getFilePointer();
      raf.write(city);
      raf.write(data);
      regionPtrPool.put(region,new DataBlock(city_id,region,dataPtr));
    }
    IndexBlock ib=new IndexBlock(Util.ip2long(startIp),Util.ip2long(endIp),dataPtr,data.length + 4);
    indexPool.add(ib);
  }
 catch (  UnsupportedEncodingException e) {
    e.printStackTrace();
  }
catch (  IOException e) {
    e.printStackTrace();
  }
}
