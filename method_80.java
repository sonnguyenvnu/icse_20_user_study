@Override public void _XXXXX_(JobBaseAPIEntity entity) throws Exception {
  list.add(entity);
  if (list.size() % batchSize == 0) {
    flush();
    list.clear();
  }
}