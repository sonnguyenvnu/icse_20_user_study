@Override public Expression optimize(Session session){
  if (session.getDatabase().getMode().treatEmptyStringsAsNull) {
    if (value instanceof ValueString && value.getString().isEmpty()) {
      value=ValueNull.INSTANCE;
    }
  }
  return this;
}
