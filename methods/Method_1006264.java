private static LinkedFile convert(List<String> entry){
  while (entry.size() < 3) {
    entry.add("");
  }
  LinkedFile field=new LinkedFile(entry.get(0),entry.get(1),entry.get(2));
  if (field.getDescription().isEmpty() && field.getLink().isEmpty() && !field.getFileType().isEmpty()) {
    field=new LinkedFile("",field.getFileType(),"");
  }
 else   if (!field.getDescription().isEmpty() && field.getLink().isEmpty() && field.getFileType().isEmpty()) {
    field=new LinkedFile("",field.getDescription(),"");
  }
  entry.clear();
  return field;
}
