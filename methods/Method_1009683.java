@Override public MergingIterator iterator(){
  Builder<InternalIterator> builder=ImmutableList.builder();
  builder.add(level0.iterator());
  builder.addAll(getLevelIterators());
  return new MergingIterator(builder.build(),getInternalKeyComparator());
}
