public void writeByte(byte b){
  try {
    if (!justCalc) {
      out.writeByte(b);
    }
 else {
      len+=1;
    }
  }
 catch (  Exception e) {
    if (BuildVars.LOGS_ENABLED) {
      FileLog.e("write byte error");
    }
  }
}
