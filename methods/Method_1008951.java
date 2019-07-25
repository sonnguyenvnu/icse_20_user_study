/** 
 * @since 2.7.2 
 */
@Override public OpcPackage clone(){
  OpcPackage result=null;
  ByteArrayOutputStream baos=new ByteArrayOutputStream();
  SaveToZipFile saver=new SaveToZipFile(this);
  try {
    saver.save(baos);
    result=load(new ByteArrayInputStream(baos.toByteArray()));
  }
 catch (  Docx4JException e) {
    e.printStackTrace();
  }
  return result;
}
