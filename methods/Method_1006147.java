@Override public List<FieldChange> cleanup(BibEntry entry){
  List<FieldChange> changes=new ArrayList<>();
  entry.getPublicationDate().ifPresent(date -> {
    if (StringUtil.isBlank(entry.getField(FieldName.YEAR))) {
      date.getYear().flatMap(year -> entry.setField(FieldName.YEAR,year.toString())).ifPresent(changes::add);
    }
    if (StringUtil.isBlank(entry.getField(FieldName.MONTH))) {
      date.getMonth().flatMap(month -> entry.setField(FieldName.MONTH,month.getJabRefFormat())).ifPresent(changes::add);
    }
    if (changes.size() > 0) {
      entry.clearField(FieldName.DATE).ifPresent(changes::add);
    }
  }
);
  for (  Map.Entry<String,String> alias : EntryConverter.FIELD_ALIASES_TEX_TO_LTX.entrySet()) {
    String oldFieldName=alias.getValue();
    String newFieldName=alias.getKey();
    entry.getField(oldFieldName).ifPresent(oldValue -> {
      if (!oldValue.isEmpty() && (!entry.getField(newFieldName).isPresent())) {
        entry.setField(newFieldName,oldValue).ifPresent(changes::add);
        entry.clearField(oldFieldName).ifPresent(changes::add);
      }
    }
);
  }
  return changes;
}
