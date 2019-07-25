@Override public Object apply(final ElementId e){
  if (null == e) {
    return null;
  }
  return e instanceof EntityId ? ((EntityId)e).getVertex() : e;
}
