private List<Object> compact(List<Object> list){
  if (list == null || list.isEmpty()) {
    return null;
  }
  List<Object> compacted=new ArrayList<Object>();
  StringBuilder stringAccumulator=new StringBuilder();
  for (  Object object : list) {
    if (object instanceof FieldWriter) {
      if (stringAccumulator.length() > 0) {
        compacted.add(new BytesArray(stringAccumulator.toString()));
        stringAccumulator.setLength(0);
      }
      compacted.add(object);
    }
 else     if (object instanceof FieldExtractor) {
      if (stringAccumulator.length() > 0) {
        compacted.add(new BytesArray(stringAccumulator.toString()));
        stringAccumulator.setLength(0);
      }
      compacted.add(new FieldWriter((FieldExtractor)object));
    }
 else {
      stringAccumulator.append(object.toString());
    }
  }
  if (stringAccumulator.length() > 0) {
    compacted.add(new BytesArray(stringAccumulator.toString()));
  }
  return compacted;
}
