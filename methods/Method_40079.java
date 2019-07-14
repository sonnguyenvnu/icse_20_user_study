@NotNull @Override public Type transform(@NotNull State s){
  for (  Alias a : names) {
    Type mod=Analyzer.self.loadModule(a.name,s);
    if (mod == null) {
      Analyzer.self.putProblem(this,"Cannot load module");
    }
 else     if (a.asname != null) {
      s.insert(a.asname.id,a.asname,mod,Binding.Kind.VARIABLE);
    }
  }
  return Type.CONT;
}
