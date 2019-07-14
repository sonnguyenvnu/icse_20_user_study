static protected ImportColumnGroup getColumnGroup(Project project,ImportColumnGroup columnGroup,String localName){
  if (columnGroup.subgroups.containsKey(localName)) {
    return columnGroup.subgroups.get(localName);
  }
  ImportColumnGroup subgroup=createColumnGroup(project,columnGroup,localName);
  columnGroup.subgroups.put(localName,subgroup);
  return subgroup;
}
