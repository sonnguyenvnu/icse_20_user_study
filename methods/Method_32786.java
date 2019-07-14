@ReactMethod public BigInteger getTotalDiskCapacity(){
  try {
    StatFs root=new StatFs(Environment.getRootDirectory().getAbsolutePath());
    return BigInteger.valueOf(root.getBlockCount()).multiply(BigInteger.valueOf(root.getBlockSize()));
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
  return null;
}
