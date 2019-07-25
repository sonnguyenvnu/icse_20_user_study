/** 
 * Initialized the static CSL instance if needed.
 * @param newStyle  journal style of the output
 * @param newFormat usually HTML or RTF.
 * @throws IOException An error occurred in the underlying JavaScript framework
 */
private void initialize(String newStyle,CitationStyleOutputFormat newFormat) throws IOException {
  if ((cslInstance == null) || !Objects.equals(newStyle,style)) {
    cslInstance=new CSL(dataProvider,new JabRefLocaleProvider(),newStyle,"en-US",false);
    style=newStyle;
  }
  if (!Objects.equals(newFormat,format)) {
    cslInstance.setOutputFormat(newFormat.getFormat());
    format=newFormat;
  }
}
