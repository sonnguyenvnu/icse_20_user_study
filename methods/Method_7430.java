private String[] getEmoji(){
  ByteArrayOutputStream os=new ByteArrayOutputStream();
  try {
    os.write(authKey);
    os.write(g_a);
  }
 catch (  IOException ignore) {
  }
  return EncryptionKeyEmojifier.emojifyForCall(Utilities.computeSHA256(os.toByteArray(),0,os.size()));
}
