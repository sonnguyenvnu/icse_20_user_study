@Override public BaseBlock apply(BlockVector3 position){
  double r=random.nextDouble();
  double offset=0;
  for (  Chance chance : patterns) {
    if (r <= (offset + chance.getChance()) / max) {
      return chance.getPattern().apply(position);
    }
    offset+=chance.getChance();
  }
  throw new RuntimeException("ProportionalFillPattern");
}
