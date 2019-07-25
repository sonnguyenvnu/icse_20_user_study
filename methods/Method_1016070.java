@Override public Collection<Module> call(Collection<Module> modules){
  for (  Element element : Elements.getElements(Stage.TOOL,modules)) {
    element.acceptVisitor(new DefaultElementVisitor<Void>(){
      @Override public Void visit(      StaticInjectionRequest request){
        LOG.warn("You shouldn't be using static injection at: " + request.getSource());
        return null;
      }
    }
);
  }
  return modules;
}
