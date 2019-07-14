@Nonnull @Override public String resolve(@Nonnull BarCode barCode){
  return barCode.getType() + "/" + barCode.getKey();
}
