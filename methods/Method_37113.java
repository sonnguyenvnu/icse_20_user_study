@Nullable @Override public LayoutHelper convertLayoutHelper(LayoutHelper oldHelper){
  LinearLayoutHelper helper=new LinearLayoutHelper();
  helper.setItemCount(getCells().size());
  return helper;
}
