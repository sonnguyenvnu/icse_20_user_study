@SuppressWarnings("unchecked") @Override protected Object getValue(Context ctx,Element base) throws Exception {
  if (type == Element.class) {
    return base;
  }
  return XMapSpringUtil.getSpringOjbect(this,xaso.getApplicationContext(),base);
}
