private void addSpecialAttribute(Scope s,String name,Type proptype){
  Binding b=s.update(name,Builtins.newTutUrl("classes.html"),proptype,Binding.Kind.ATTRIBUTE);
  b.markSynthetic();
  b.markStatic();
  b.markReadOnly();
}
