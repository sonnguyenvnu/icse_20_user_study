private AggregationBuilder getGroupAgg(Field field,Select select2) throws SqlParseException {
  boolean refrence=false;
  AggregationBuilder lastAgg=null;
  for (  Field temp : select.getFields()) {
    if (temp instanceof MethodField && temp.getName().equals("script")) {
      MethodField scriptField=(MethodField)temp;
      for (      KVValue kv : scriptField.getParams()) {
        if (kv.value.equals(field.getName())) {
          lastAgg=aggMaker.makeGroupAgg(scriptField);
          refrence=true;
          break;
        }
      }
    }
  }
  if (!refrence)   lastAgg=aggMaker.makeGroupAgg(field);
  return lastAgg;
}
