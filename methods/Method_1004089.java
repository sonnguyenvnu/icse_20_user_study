@Override public void startup(final RuntimeData data) throws Exception {
  super.startup(data);
  final Field field=systemClass.getField(accessFieldName);
  field.set(null,data);
}
