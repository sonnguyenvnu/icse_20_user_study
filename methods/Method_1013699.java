@Override public void compare(DcMeta future){
  if (current == null) {
    current=future;
    return;
  }
  DcMetaComparator comparator=DcMetaComparator.buildComparator(current,future);
  comparator.accept(this);
  current=future;
}
