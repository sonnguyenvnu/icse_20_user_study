@NotNull @Override public Type transform(@NotNull State s){
  if (module == null) {
    return Type.CONT;
  }
  Type mod=Analyzer.self.loadModule(module,s);
  if (mod == null) {
    Analyzer.self.putProblem(this,"Cannot load module");
  }
 else   if (isImportStar()) {
    importStar(s,mod);
  }
 else {
    for (    Alias a : names) {
      Name first=a.name.get(0);
      Set<Binding> bs=mod.table.lookup(first.id);
      if (bs != null) {
        if (a.asname != null) {
          s.update(a.asname.id,bs);
          Analyzer.self.putRef(a.asname,bs);
        }
 else {
          s.update(first.id,bs);
          Analyzer.self.putRef(first,bs);
        }
      }
 else {
        List<Name> ext=new ArrayList<>(module);
        ext.add(first);
        Type mod2=Analyzer.self.loadModule(ext,s);
        if (mod2 != null) {
          if (a.asname != null) {
            s.insert(a.asname.id,a.asname,mod2,Binding.Kind.VARIABLE);
          }
 else {
            s.insert(first.id,first,mod2,Binding.Kind.VARIABLE);
          }
        }
      }
    }
  }
  return Type.CONT;
}
