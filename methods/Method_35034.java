@Nonnull static String pathForBarcode(@Nonnull BarCode barCode){
  return barCode.getType() + barCode.getKey();
}
