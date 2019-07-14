private void checkForHiddenFields(List<VariableTree> originalClassMembers,Map<Name,VarSymbol> parentMembers,Name parentClassName,ClassTree classTree,VisitorState visitorState){
  Iterator<VariableTree> origVariableIterator=originalClassMembers.iterator();
  VariableTree origVariable=null;
  while (origVariableIterator.hasNext()) {
    origVariable=origVariableIterator.next();
    if (parentMembers.containsKey(origVariable.getName())) {
      if (isPackagePrivateAndInDiffPackage(parentMembers.get(origVariable.getName()),classTree)) {
        continue;
      }
      Description.Builder matchDesc=buildDescription(origVariable);
      matchDesc.setMessage("Hiding fields of superclasses may cause confusion and errors. " + "This field is hiding a field of the same name in superclass: " + parentClassName);
      visitorState.reportMatch(matchDesc.build());
      origVariableIterator.remove();
    }
  }
}
