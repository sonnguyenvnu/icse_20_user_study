public boolean isModule(){
  return binding != null && binding.getKind() == Binding.Kind.MODULE;
}
