public static void serialize(SMILDocument smilDoc,OutputStream out){
  try {
    Writer writer=new BufferedWriter(new OutputStreamWriter(out,"UTF-8"),2048);
    writeElement(writer,smilDoc.getDocumentElement());
    writer.flush();
  }
 catch (  UnsupportedEncodingException e) {
    Timber.e(e,"exception thrown");
  }
catch (  IOException e) {
    Timber.e(e,"exception thrown");
  }
}
