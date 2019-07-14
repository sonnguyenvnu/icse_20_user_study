public static boolean checkPasscode(String passcode){
  if (passcodeSalt.length == 0) {
    boolean result=Utilities.MD5(passcode).equals(passcodeHash);
    if (result) {
      try {
        passcodeSalt=new byte[16];
        Utilities.random.nextBytes(passcodeSalt);
        byte[] passcodeBytes=passcode.getBytes("UTF-8");
        byte[] bytes=new byte[32 + passcodeBytes.length];
        System.arraycopy(passcodeSalt,0,bytes,0,16);
        System.arraycopy(passcodeBytes,0,bytes,16,passcodeBytes.length);
        System.arraycopy(passcodeSalt,0,bytes,passcodeBytes.length + 16,16);
        passcodeHash=Utilities.bytesToHex(Utilities.computeSHA256(bytes,0,bytes.length));
        saveConfig();
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
    }
    return result;
  }
 else {
    try {
      byte[] passcodeBytes=passcode.getBytes("UTF-8");
      byte[] bytes=new byte[32 + passcodeBytes.length];
      System.arraycopy(passcodeSalt,0,bytes,0,16);
      System.arraycopy(passcodeBytes,0,bytes,16,passcodeBytes.length);
      System.arraycopy(passcodeSalt,0,bytes,passcodeBytes.length + 16,16);
      String hash=Utilities.bytesToHex(Utilities.computeSHA256(bytes,0,bytes.length));
      return passcodeHash.equals(hash);
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
  return false;
}
