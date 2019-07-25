@Override public ReadContext init(final InitContext context){
  return new ReadContext(context.getFileSchema());
}
