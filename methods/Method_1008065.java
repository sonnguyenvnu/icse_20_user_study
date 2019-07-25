@Override void analyze(Locals locals){
  if (!read) {
    throw createError(new IllegalArgumentException("Regex constant may only be read [" + pattern + "]."));
  }
  try {
    Pattern.compile(pattern,flags);
  }
 catch (  PatternSyntaxException e) {
    throw new Location(location.getSourceName(),location.getOffset() + 1 + e.getIndex()).createError(new IllegalArgumentException("Error compiling regex: " + e.getDescription()));
  }
  constant=new Constant(location,locals.getDefinition().PatternType.type,"regexAt$" + location.getOffset(),this::initializeConstant);
  actual=locals.getDefinition().PatternType;
}
