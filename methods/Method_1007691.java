@Override public Operation resume(RunContext run) throws WorldEditException {
  for (  LocatedBlock block : blocks) {
    extent.setBlock(block.getLocation(),block.getBlock());
  }
  return null;
}
