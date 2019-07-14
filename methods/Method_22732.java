public String getDocumentText() throws BadLocationException {
  return document.getText(0,document.getLength());
}
