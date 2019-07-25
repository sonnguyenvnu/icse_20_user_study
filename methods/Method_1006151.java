@Override public List<FieldChange> cleanup(BibEntry entry){
  Optional<FieldChange> setFieldChange=entry.getField(sourceField).flatMap(value -> entry.setField(targetField,value));
  Optional<FieldChange> clearFieldChange=entry.clearField(sourceField);
  return OptionalUtil.toList(setFieldChange,clearFieldChange);
}
