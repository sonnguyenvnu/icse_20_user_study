@Override public Collection<Module> call(Collection<Module> modules){
  final List<Element> noStatics=Lists.newArrayList();
  for (  Element element : Elements.getElements(Stage.TOOL,modules)) {
    element.acceptVisitor(new DefaultElementVisitor<Void>(){
      @Override public Void visit(      StaticInjectionRequest request){
        return null;
      }
      @Override public Void visitOther(      Element element){
        noStatics.add(element);
        return null;
      }
    }
);
  }
  return ImmutableList.<Module>of(Elements.getModule(noStatics));
}
