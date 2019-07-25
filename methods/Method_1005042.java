@Override public Element _apply(final ExampleDomainObject obj){
  if (obj.getIds().length > 1) {
    final Edge.Builder builder=new Edge.Builder().group(obj.getType()).source(obj.getIds()[0]).dest(obj.getIds()[1]);
    if (obj.getIds().length > 2) {
      builder.directed(Boolean.TRUE.equals(obj.getIds()[2]));
    }
    return builder.build();
  }
  return new Entity.Builder().group(obj.getType()).vertex(obj.getIds()[0]).build();
}
