@Override public void output(javax.xml.transform.Result result) throws Docx4JException {
  if (wmlPackage == null) {
    throw new Docx4JException("Must setWmlPackage");
  }
  if (htmlSettings == null) {
    log.debug("Using empty HtmlSettings");
    htmlSettings=new HTMLSettings();
  }
  try {
    html(wmlPackage,result,htmlSettings);
  }
 catch (  Exception e) {
    throw new Docx4JException("Failed to create HTML output",e);
  }
}
