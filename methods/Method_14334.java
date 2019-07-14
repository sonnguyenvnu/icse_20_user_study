static public void appendColumnName(List<String> columnNames,int index,String name){
  name=name.trim();
  while (columnNames.size() <= index) {
    columnNames.add("");
  }
  if (!name.isEmpty()) {
    String oldName=columnNames.get(index);
    if (!oldName.isEmpty()) {
      name=oldName + " " + name;
    }
    columnNames.set(index,name);
  }
}
