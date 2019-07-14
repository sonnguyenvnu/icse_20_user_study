public void navigateTo(final String uniqueStr){
  new Thread(new Runnable(){
    @Override public void run(){
      if (uniqueStr == null)       return;
      String[] linkParts=uniqueStr.split("\\|");
      if (linkParts.length <= 1)       return;
      String destinationTypeStr=linkParts[1];
      try {
        bar.setVisible(true);
        getLabel().setText("Navigating: " + destinationTypeStr.replaceAll("/","."));
        TypeReference type=metadataSystem.lookupType(destinationTypeStr);
        if (type == null)         throw new RuntimeException("Cannot lookup type: " + destinationTypeStr);
        TypeDefinition typeDef=type.resolve();
        if (typeDef == null)         throw new RuntimeException("Cannot resolve type: " + destinationTypeStr);
        String tabTitle=typeDef.getName() + ".class";
        extractClassToTextPane(typeDef,tabTitle,destinationTypeStr,uniqueStr);
        getLabel().setText("Complete");
      }
 catch (      Exception e) {
        getLabel().setText("Cannot navigate: " + destinationTypeStr.replaceAll("/","."));
        Luyten.showExceptionDialog("Cannot Navigate!",e);
      }
 finally {
        bar.setVisible(false);
      }
    }
  }
).start();
}
