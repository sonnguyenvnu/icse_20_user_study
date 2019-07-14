public void writeBytes(byte[] b){
  try {
    if (!justCalc) {
      buffer.put(b);
    }
 else {
      len+=b.length;
    }
  }
 catch (  Exception e) {
    if (BuildVars.LOGS_ENABLED) {
      FileLog.e("write raw error");
    }
  }
}
