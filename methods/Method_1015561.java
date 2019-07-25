@Override protected void write(List<PingData> list,String clustername){
  try {
    String filename=clustername + "/" + addressToFilename(local_addr);
    ByteArrayOutputStream out=new ByteArrayOutputStream(4096);
    write(list,out);
    byte[] data=out.toByteArray();
    rackspaceClient.createObject(container,filename,data);
  }
 catch (  Exception e) {
    log.error(Util.getMessage("ErrorMarshallingObject"),e);
  }
}
