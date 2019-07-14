private List<ColumnDescriptor<RuleViolation>> activeColumns(){
  List<ColumnDescriptor<RuleViolation>> actives=new ArrayList<>();
  for (  ColumnDescriptor<RuleViolation> desc : ALL_COLUMNS) {
    PropertyDescriptor<Boolean> prop=booleanPropertyFor(desc.id,null);
    if (getProperty(prop)) {
      actives.add(desc);
    }
  }
  return actives;
}
