public void decompile(Map<String,String> preferences){
  try {
    clearHyperlinks();
    clearLineNumbers();
    declarations.clear();
    typeDeclarations.clear();
    strings.clear();
    boolean realignmentLineNumbers=getPreferenceValue(preferences,REALIGN_LINE_NUMBERS,true);
    boolean unicodeEscape=getPreferenceValue(preferences,ESCAPE_UNICODE_CHARACTERS,false);
    Map<String,Object> configuration=new HashMap<>();
    configuration.put("realignLineNumbers",realignmentLineNumbers);
    setShowMisalignment(realignmentLineNumbers);
    ContainerLoader loader=new ContainerLoader(entry);
    ClassFilePrinter printer=new ClassFilePrinter();
    printer.setRealignmentLineNumber(realignmentLineNumbers);
    printer.setUnicodeEscape(unicodeEscape);
    String entryPath=entry.getPath();
    assert entryPath.endsWith(".class");
    String entryInternalName=entryPath.substring(0,entryPath.length() - 6);
    DECOMPILER.decompile(loader,printer,entryInternalName,configuration);
  }
 catch (  Throwable t) {
    assert ExceptionUtil.printStackTrace(t);
    setText("// INTERNAL ERROR //");
  }
  maximumLineNumber=getMaximumSourceLineNumber();
}
