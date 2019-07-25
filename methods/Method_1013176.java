public boolean disconnect(){
  try {
    DistributedFPSet.shutdown();
  }
 catch (  Exception e) {
    e.printStackTrace();
    return false;
  }
  return true;
}
