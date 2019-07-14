private static void downloadFileFromURL(String urlString,File destination) throws Exception {
  if (System.getenv("MVNW_USERNAME") != null && System.getenv("MVNW_PASSWORD") != null) {
    String username=System.getenv("MVNW_USERNAME");
    char[] password=System.getenv("MVNW_PASSWORD").toCharArray();
    Authenticator.setDefault(new Authenticator(){
      @Override protected PasswordAuthentication getPasswordAuthentication(){
        return new PasswordAuthentication(username,password);
      }
    }
);
  }
  URL website=new URL(urlString);
  ReadableByteChannel rbc;
  rbc=Channels.newChannel(website.openStream());
  FileOutputStream fos=new FileOutputStream(destination);
  fos.getChannel().transferFrom(rbc,0,Long.MAX_VALUE);
  fos.close();
  rbc.close();
}
