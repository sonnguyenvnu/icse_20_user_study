public boolean readBool(boolean exception){
  int consructor=readInt32(exception);
  if (consructor == 0x997275b5) {
    return true;
  }
 else   if (consructor == 0xbc799737) {
    return false;
  }
  if (exception) {
    throw new RuntimeException("Not bool value!");
  }
 else {
    if (BuildVars.LOGS_ENABLED) {
      FileLog.e("Not bool value!");
    }
  }
  return false;
}
