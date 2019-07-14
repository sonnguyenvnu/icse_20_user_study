@Override public Field getMember(){
  Field field=fieldReference == null ? null : fieldReference.get();
  if (field == null) {
    field=ClassLoaderUtil.getField(getClassNode().getType(),name);
    this.fieldReference=new WeakReference<>(field);
  }
  return field;
}
