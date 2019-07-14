@Override public void generateContent(){
  definitionToSelectionMap=new HashMap<>();
  referenceToSelectionsMap=new HashMap<>();
  currentTypeQualifiedName=type.getPackageName() + "." + type.getName();
  final StringWriter stringwriter=new StringWriter();
  PlainTextOutput plainTextOutput=new PlainTextOutput(stringwriter){
    @Override public void writeDefinition(    String text,    Object definition,    boolean isLocal){
      super.writeDefinition(text,definition,isLocal);
      try {
        if (text != null && definition != null) {
          String uniqueStr=createUniqueStrForReference(definition);
          if (uniqueStr != null) {
            text=text.replaceAll("[^\\.]*\\.","");
            int from=stringwriter.getBuffer().length() - text.length();
            int to=stringwriter.getBuffer().length();
            definitionToSelectionMap.put(uniqueStr,new Selection(from,to));
          }
        }
      }
 catch (      Exception e) {
        Luyten.showExceptionDialog("Exception!",e);
      }
    }
    @Override public void writeReference(    String text,    Object reference,    boolean isLocal){
      super.writeReference(text,reference,isLocal);
      try {
        if (text != null && reference != null) {
          String uniqueStr=createUniqueStrForReference(reference);
          if (uniqueStr != null) {
            text=text.replaceAll("[^\\.]*\\.","");
            int from=stringwriter.getBuffer().length() - text.length();
            int to=stringwriter.getBuffer().length();
            if (reference instanceof FieldReference) {
              if (((FieldReference)reference).isDefinition()) {
                definitionToSelectionMap.put(uniqueStr,new Selection(from,to));
                return;
              }
            }
            if (referenceToSelectionsMap.containsKey(uniqueStr)) {
              Set<Selection> selectionsSet=referenceToSelectionsMap.get(uniqueStr);
              if (selectionsSet != null) {
                selectionsSet.add(new Selection(from,to));
              }
            }
 else {
              Set<Selection> selectionsSet=new HashSet<>();
              selectionsSet.add(new Selection(from,to));
              referenceToSelectionsMap.put(uniqueStr,selectionsSet);
            }
          }
        }
      }
 catch (      Exception e) {
        Luyten.showExceptionDialog("Exception!",e);
      }
    }
  }
;
  plainTextOutput.setUnicodeOutputEnabled(decompilationOptions.getSettings().isUnicodeOutputEnabled());
  settings.getLanguage().decompileType(type,plainTextOutput,decompilationOptions);
  textContent=stringwriter.toString();
  isSelectionMapsPopulated=true;
}
