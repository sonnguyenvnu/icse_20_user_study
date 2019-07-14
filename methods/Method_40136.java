@NotNull ClassType newClass(@NotNull String name,State table,ClassType superClass,@NotNull ClassType... moreSupers){
  ClassType t=new ClassType(name,table,superClass);
  for (  ClassType c : moreSupers) {
    t.addSuper(c);
  }
  return t;
}
