public void process(Context ctx,Element element) throws Exception {
  Object value=getValue(ctx,element);
  if (value != null) {
    setValue(ctx.getObject(),value);
  }
}
