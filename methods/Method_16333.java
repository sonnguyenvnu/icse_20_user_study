@Override @SuppressWarnings("all") public Object converterValue(Object o){
  List<Object> values;
  if (o instanceof String) {
    values=splitter.apply((String)o);
  }
 else   if (o instanceof Object[]) {
    values=Arrays.asList(((Object[])o));
  }
 else   if (o instanceof Collection) {
    values=new ArrayList<>(((Collection)o));
  }
 else {
    values=Collections.singletonList(o);
  }
  if (writeObject) {
    return allOptionSupplier.get().stream().filter(e -> e.eq(values)).collect(Collectors.toSet());
  }
  return dictToText.apply(allOptionSupplier.get().stream().filter(e -> e.eq(values)).map(EnumDict::getText).map(String::valueOf));
}
