private static String formatLockString(Set<GuardedByExpression> locks){
  ImmutableList<String> sortedUnhandled=FluentIterable.from(locks).transform(Functions.toStringFunction()).toSortedList(Comparator.naturalOrder());
  return Joiner.on(", ").join(sortedUnhandled);
}
