public static String stripAccents(String s){
  int[] messageData=SmsMessage.calculateLength(s,false);
  if (messageData[0] != 1) {
    for (int i=0; i < characters.length(); i++) {
      s=s.replaceAll(characters.substring(i,i + 1),gsm.substring(i,i + 1));
    }
  }
  return s;
}
