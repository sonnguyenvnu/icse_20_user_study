@OnCreateLayout static @Nullable Component onCreateLayout(final ComponentContext c,final @Prop(varArg="component",optional=true) List<Component> components){
  if (components == null) {
    return null;
  }
  for (int i=0; i < components.size(); i++) {
    final Component component=components.get(i);
    if (Component.willRender(c,component)) {
      return component;
    }
  }
  return null;
}
