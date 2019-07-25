/** 
 * ?????????
 * @return
 * @throws IOException
 * @since 1.3.0
 */
public NiceXWPFDocument generate() throws IOException {
  ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
  this.write(byteArrayOutputStream);
  this.close();
  return new NiceXWPFDocument(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
}
