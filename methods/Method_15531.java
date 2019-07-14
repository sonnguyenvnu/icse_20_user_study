@JSONField(serialize=false) public String getValuesString(){
  String s="";
  if (values != null && values.size() > 0) {
    Object[] items=new Object[values.size()];
    List<Object> vs;
    for (int i=0; i < values.size(); i++) {
      vs=values.get(i);
      if (vs == null) {
        continue;
      }
      items[i]="(";
      for (int j=0; j < vs.size(); j++) {
        items[i]+=((j <= 0 ? "" : ",") + getValue(vs.get(j)));
      }
      items[i]+=")";
    }
    s=StringUtil.getString(items);
  }
  return s;
}
