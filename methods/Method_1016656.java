<E extends Where>SelectCoreImpl<E> from(final E where){
  clearSelection();
  return new SelectCoreImpl<>(columns,isDistinct,where,intoMeasurement);
}
