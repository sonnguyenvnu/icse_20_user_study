@Override public Operation resume(RunContext run) throws WorldEditException {
  while (iterator.hasNext()) {
    if (function.apply(iterator.next())) {
      affected++;
    }
  }
  return null;
}
