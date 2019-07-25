@Override public List<JsonEntityField> make(Mirror<?> mirror){
  Field[] flds=mirror.getFields(true,false);
  List<JsonEntityField> fields=new ArrayList<JsonEntityField>(flds.length);
  for (  Field fld : flds) {
    JsonEntityField ef=make(mirror,fld);
    if (null != ef)     fields.add(ef);
  }
  for (  Method m : mirror.getMethods()) {
    JsonEntityField ef=make(mirror,m);
    if (null != ef)     fields.add(ef);
  }
  return fields;
}
