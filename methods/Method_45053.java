private void decompileWithoutLinks(){
  this.invalidateContent();
  isNavigationLinksValid=false;
  textArea.setHyperlinksEnabled(false);
  StringWriter stringwriter=new StringWriter();
  PlainTextOutput plainTextOutput=new PlainTextOutput(stringwriter);
  plainTextOutput.setUnicodeOutputEnabled(decompilationOptions.getSettings().isUnicodeOutputEnabled());
  settings.getLanguage().decompileType(type,plainTextOutput,decompilationOptions);
  setContentPreserveLastScrollPosition(stringwriter.toString());
  this.isContentValid=true;
}
