@Override public String format(String raw) throws Exception {
  raw=super.format(raw);
  IStructuredDocument document=(IStructuredDocument)new HTMLDocumentLoader().createNewStructuredDocument();
  document.setPreferredLineDelimiter(LINE_DELIMITER);
  document.set(raw);
  StructuredDocumentProcessor<CodeFormatter> jsProcessor=new StructuredDocumentProcessor<CodeFormatter>(document,IHTMLPartitions.SCRIPT,JsRegionProcessor.createFactory(htmlFormatterIndent));
  jsProcessor.apply(jsFormatter);
  return document.get();
}
