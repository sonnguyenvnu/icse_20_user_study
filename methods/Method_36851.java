@Nullable @Override public LayoutHelper convertLayoutHelper(@Nullable LayoutHelper oldHelper){
  LinearLayoutHelper helper=new LinearLayoutHelper();
  helper.setItemCount(getCells().size());
  return helper;
}
