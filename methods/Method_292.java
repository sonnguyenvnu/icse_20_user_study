@Override public void unbind(){
  if (unbinders == null) {
    throw new IllegalStateException("Bindings already cleared.");
  }
  for (  Unbinder unbinder : unbinders) {
    unbinder.unbind();
  }
  unbinders=null;
}
