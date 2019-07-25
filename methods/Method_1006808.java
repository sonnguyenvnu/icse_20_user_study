private void mapping(String script,Criteria criteria,StringBuilder sb){
  String[] keyArr=script.split(SqlScript.SPACE);
  int length=keyArr.length;
  for (int i=0; i < length; i++) {
    String origin=keyArr[i].trim();
    String target=mapping(origin,criteria);
    sb.append(target).append(SqlScript.SPACE);
  }
}
