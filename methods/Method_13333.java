public static void matchFragmentAndAddDocumentRange(String fragment,HashMap<String,DeclarationData> declarations,List<DocumentRange> ranges){
  if ((fragment.indexOf('?') != -1) || (fragment.indexOf('*') != -1)) {
    int lastDash=fragment.lastIndexOf('-');
    if (lastDash == -1) {
      String slashAndTypeName=fragment.substring(1);
      String typeName=fragment.substring(2);
      for (      Map.Entry<String,DeclarationData> entry : declarations.entrySet()) {
        if (entry.getKey().endsWith(slashAndTypeName) || entry.getKey().equals(typeName)) {
          ranges.add(new DocumentRange(entry.getValue().startPosition,entry.getValue().endPosition));
        }
      }
    }
 else {
      String prefix=fragment.substring(0,lastDash + 1);
      String suffix=fragment.substring(lastDash + 1);
      BiFunction<String,String,Boolean> matchDescriptors;
      if (suffix.charAt(0) == '(') {
        matchDescriptors=DescriptorMatcher::matchMethodDescriptors;
      }
 else {
        matchDescriptors=DescriptorMatcher::matchFieldDescriptors;
      }
      if (fragment.charAt(0) == '*') {
        String slashAndTypeNameAndName=prefix.substring(1);
        String typeNameAndName=prefix.substring(2);
        for (        Map.Entry<String,DeclarationData> entry : declarations.entrySet()) {
          String key=entry.getKey();
          if ((key.indexOf(slashAndTypeNameAndName) != -1) || (key.startsWith(typeNameAndName))) {
            int index=key.lastIndexOf('-') + 1;
            if (matchDescriptors.apply(suffix,key.substring(index))) {
              ranges.add(new DocumentRange(entry.getValue().startPosition,entry.getValue().endPosition));
            }
          }
        }
      }
 else {
        for (        Map.Entry<String,DeclarationData> entry : declarations.entrySet()) {
          String key=entry.getKey();
          if (key.startsWith(prefix)) {
            int index=key.lastIndexOf('-') + 1;
            if (matchDescriptors.apply(suffix,key.substring(index))) {
              ranges.add(new DocumentRange(entry.getValue().startPosition,entry.getValue().endPosition));
            }
          }
        }
      }
    }
  }
 else {
    DeclarationData data=declarations.get(fragment);
    if (data != null) {
      ranges.add(new DocumentRange(data.startPosition,data.endPosition));
    }
 else     if (fragment.endsWith("-<clinit>-()V")) {
      String typeName=fragment.substring(0,fragment.indexOf('-'));
      data=declarations.get(typeName);
      ranges.add(new DocumentRange(data.startPosition,data.endPosition));
    }
  }
}
