private String getTableHeader(){
  String colWidths="2,3,1,1";
  String colNames="Name | Description | Datatype | Default Value";
  if (showMutability) {
    colWidths+=",1";
    colNames+=" | Mutability";
  }
  return "[role=\"tss-config-table\",cols=\"" + colWidths + "\",options=\"header\",width=\"100%\"]\n" + "|=====\n" + "| " + colNames;
}
