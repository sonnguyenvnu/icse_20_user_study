public void writeDouble(double d){
  try {
    writeInt64(Double.doubleToRawLongBits(d));
  }
 catch (  Exception e) {
    if (BuildVars.LOGS_ENABLED) {
      FileLog.e("write double error");
    }
  }
}
