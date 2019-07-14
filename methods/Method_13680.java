@Override public boolean exists(){
  try {
    return isBucket() ? getBucket() != null : getOSSObject() != null;
  }
 catch (  Exception e) {
    return false;
  }
}
