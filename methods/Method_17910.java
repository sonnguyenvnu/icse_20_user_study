static List<?> extractContent(SparseArrayCompat<MountItem> items){
  final int size=items.size();
  if (size == 1) {
    return Collections.singletonList(items.valueAt(0).getContent());
  }
  final List<Object> content=new ArrayList<>(size);
  for (int i=0; i < size; i++) {
    content.add(items.valueAt(i).getContent());
  }
  return content;
}
