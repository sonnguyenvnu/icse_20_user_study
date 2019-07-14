static protected ImportColumnGroup createColumnGroup(Project project,ImportColumnGroup columnGroup,String localName){
  ImportColumnGroup newGroup=new ImportColumnGroup();
  newGroup.name=columnGroup.name.length() == 0 ? (localName == null ? "Text" : localName) : (localName == null ? columnGroup.name : (columnGroup.name + " - " + localName));
  newGroup.nextRowIndex=columnGroup.nextRowIndex;
  return newGroup;
}
