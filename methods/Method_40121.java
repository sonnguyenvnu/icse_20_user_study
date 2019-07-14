public static void bind(@NotNull State s,@NotNull Name name,@NotNull Type rvalue,Binding.Kind kind){
  if (s.isGlobalName(name.id)) {
    Set<Binding> bs=s.lookup(name.id);
    if (bs != null) {
      for (      Binding b : bs) {
        b.addType(rvalue);
        Analyzer.self.putRef(name,b);
      }
    }
  }
 else {
    s.insert(name.id,name,rvalue,kind);
  }
}
