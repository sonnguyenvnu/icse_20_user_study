private static void setTo(String[] to,MimeMessage message) throws MessagingException {
  if (null != to && to.length > 0) {
    if (to.length == 1) {
      message.setRecipient(Message.RecipientType.TO,new InternetAddress(to[0]));
    }
 else {
      List<InternetAddress> iaList=new ArrayList<InternetAddress>();
      for (      String t : to) {
        InternetAddress ia=new InternetAddress(t);
        iaList.add(ia);
      }
      InternetAddress[] iaArray=new InternetAddress[to.length];
      message.setRecipients(Message.RecipientType.TO,iaList.toArray(iaArray));
    }
  }
}
