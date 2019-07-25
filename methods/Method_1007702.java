@Override public Operation resume(RunContext run) throws WorldEditException {
  for (  BlockVector2 pt : flatRegion.asFlatRegion()) {
    if (function.apply(pt)) {
      affected++;
    }
  }
  return null;
}
