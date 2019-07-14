public void writeInt64(long x){
  try {
    if (!justCalc) {
      buffer.putLong(x);
    }
 else {
      len+=8;
    }
  }
 catch (  Exception e) {
    if (BuildVars.LOGS_ENABLED) {
      FileLog.e("write int64 error");
    }
  }
}
