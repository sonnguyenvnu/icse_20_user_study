@Override protected ComponentLayout resolve(ComponentContext c){
  if (delegate == null) {
    return ComponentContext.NULL_LAYOUT;
  }
  return c.newLayoutBuilder(delegate,0,0);
}
