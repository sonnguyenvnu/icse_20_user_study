private void dumpCallObject(){
  try {
    if (BuildVars.LOGS_ENABLED) {
      Field[] flds=TLRPC.PhoneCall.class.getFields();
      for (      Field f : flds) {
        FileLog.d(f.getName() + " = " + f.get(call));
      }
    }
  }
 catch (  Exception x) {
    if (BuildVars.LOGS_ENABLED) {
      FileLog.e(x);
    }
  }
}
