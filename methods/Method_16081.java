static <T extends EnumDict>List<T> getByMask(Supplier<List<T>> allOptionsSupplier,long mask){
  return getByMask(allOptionsSupplier.get(),mask);
}
