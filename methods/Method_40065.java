private void addSpecialAttribute(@NotNull State s,String name,Type proptype){
  Binding b=new Binding(name,Builtins.newTutUrl("classes.html"),proptype,Binding.Kind.ATTRIBUTE);
  s.update(name,b);
  b.markSynthetic();
  b.markStatic();
}
