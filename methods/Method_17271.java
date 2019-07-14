private static void downloadFileFromURL(String urlString,File destination) throws Exception {
  URL website=new URL(urlString);
  ReadableByteChannel rbc;
  rbc=Channels.newChannel(website.openStream());
  FileOutputStream fos=new FileOutputStream(destination);
  fos.getChannel().transferFrom(rbc,0,Long.MAX_VALUE);
  fos.close();
  rbc.close();
}
