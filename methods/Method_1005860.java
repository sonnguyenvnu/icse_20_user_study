/** 
 * ????
 * @return
 */
public String execute(String fields) throws Exception {
  Set<String> fieldSet=getFields(fields);
  if (fieldSet.size() == 0) {
    return "????????????";
  }
  StringBuilder poField=new StringBuilder();
  StringBuilder poGetSet=new StringBuilder();
  for (  String field : fieldSet) {
    String[] fieldSplits=field.split("_CA_SEPARATOR_");
    String type=fieldSplits.length > 1 ? fieldSplits[1] : null;
    field=fieldSplits[0];
    poField.append(String.format(FIELD,sqlType2JavaType(type),getCamel(field)));
    poGetSet.append(String.format(GET_SET,sqlType2JavaType(type),upperCaseFirst(getCamel(field)),getCamel(field),upperCaseFirst(getCamel(field)),sqlType2JavaType(type),getCamel(field),getCamel(field),getCamel(field)));
  }
  String poFilUrl=Tools.getServicePath() + "WEB-INF/classes/generate/JavaPO.txt";
  String poContent=Tools.readFile(poFilUrl);
  poContent=poContent.replaceAll(PO_FIELD,poField.toString());
  poContent=poContent.replaceAll(PO_GET_SET,poGetSet.toString());
  return poContent;
}
