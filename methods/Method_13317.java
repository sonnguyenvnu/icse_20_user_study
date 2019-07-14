@Override public void decompile(Map<String,String> preferences){
  try {
    clearHyperlinks();
    clearLineNumbers();
    typeDeclarations.clear();
    boolean unicodeEscape=getPreferenceValue(preferences,ESCAPE_UNICODE_CHARACTERS,false);
    ContainerLoader loader=new ContainerLoader(entry);
    ModuleInfoFilePrinter printer=new ModuleInfoFilePrinter();
    printer.setUnicodeEscape(unicodeEscape);
    String entryPath=entry.getPath();
    assert entryPath.endsWith(".class");
    String entryInternalName=entryPath.substring(0,entryPath.length() - 6);
    DECOMPILER.decompile(loader,printer,entryInternalName);
  }
 catch (  Throwable t) {
    assert ExceptionUtil.printStackTrace(t);
    setText("// INTERNAL ERROR //");
  }
}
