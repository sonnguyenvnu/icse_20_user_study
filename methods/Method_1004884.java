public static byte[] escape(final byte[] auth,final boolean quote){
  int escapeCount=0;
  byte[] newAuth=auth;
  for (int i=0; i < auth.length; i++) {
    if (auth[i] == '"' || auth[i] == '\\') {
      escapeCount++;
    }
  }
  if (escapeCount > 0 || quote) {
    byte[] escapedAuth=new byte[auth.length + escapeCount + (quote ? 2 : 0)];
    int index=quote ? 1 : 0;
    for (int i=0; i < auth.length; i++) {
      if (auth[i] == '"' || auth[i] == '\\') {
        escapedAuth[index++]='\\';
      }
      escapedAuth[index++]=auth[i];
    }
    if (quote) {
      escapedAuth[0]='"';
      escapedAuth[escapedAuth.length - 1]='"';
    }
    newAuth=escapedAuth;
  }
  return newAuth;
}
