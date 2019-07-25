/** 
 * Initializes the internal output stream and writes the BOM if the specified encoding is a Unicode encoding.
 * @param out The output stream we are writing.
 * @param encoding The encoding in which to write.
 * @throws IOException If an I/O error occurs while writing a BOM.
 */
private void init(OutputStream out,String encoding) throws IOException {
  internalOut=new OutputStreamWriter(out,encoding);
  if ("UTF-8".equals(encoding)) {
    if (getWriteUtf8BOM()) {
      out.write(UTF8_BOM,0,UTF8_BOM.length);
    }
  }
 else   if ("UTF-16LE".equals(encoding)) {
    out.write(UTF16LE_BOM,0,UTF16LE_BOM.length);
  }
 else   if ("UTF-16BE".equals(encoding)) {
    out.write(UTF16BE_BOM,0,UTF16BE_BOM.length);
  }
 else   if ("UTF-32LE".equals(encoding)) {
    out.write(UTF32LE_BOM,0,UTF32LE_BOM.length);
  }
 else   if ("UTF-32".equals(encoding) || "UTF-32BE".equals(encoding)) {
    out.write(UTF32BE_BOM,0,UTF32BE_BOM.length);
  }
}
