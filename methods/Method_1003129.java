@Override public boolean contains(Value[] values){
  if (parent != null) {
    return parent.contains(values);
  }
  assert distinct;
  if (visibleColumnCount != resultColumnCount) {
    return index.containsKey(ValueRow.get(values));
  }
  return map.containsKey(getKey(values));
}
