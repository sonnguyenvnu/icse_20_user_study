public static void bind(@NotNull State s,@NotNull Name name,@NotNull Type rvalue,Binding.Kind kind){
  if (s.isGlobalName(name.id) || name.isGlobalVar()) {
    Binding b=new Binding(name,rvalue,kind);
    s.getGlobalTable().update(name.id,b);
    Analyzer.self.putRef(name,b);
  }
 else {
    s.insert(name.id,name,rvalue,kind);
  }
}
