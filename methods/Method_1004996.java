@Override public ElementId apply(final Element element){
  if (element.getGroup().endsWith("|Edge")) {
    return new EdgeSeed(element.getProperty("source"),element.getProperty("destination"),Boolean.TRUE.equals(element.getProperty("directed")));
  }
  return new EntitySeed(element.getProperty("vertex"));
}
