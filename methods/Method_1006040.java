public int replace(){
  findString=findStringProperty.getValue();
  replaceString=replaceStringProperty.getValue();
  fieldStrings=fieldStringProperty.getValue().split(";");
  boolean selOnly=selectOnlyProperty.getValue();
  allFieldReplace=allFieldReplaceProperty.getValue();
  final NamedCompound compound=new NamedCompound(Localization.lang("Replace string"));
  int counter=0;
  if (selOnly) {
    for (    BibEntry bibEntry : this.panel.getSelectedEntries()) {
      counter+=replaceItem(bibEntry,compound);
    }
  }
 else {
    for (    BibEntry bibEntry : this.panel.getDatabase().getEntries()) {
      counter+=replaceItem(bibEntry,compound);
    }
  }
  return counter;
}
