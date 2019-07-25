<E extends Where>SelectCoreImpl<E> from(final FromClause fromClause,final E where){
  clearSelection();
  return new SelectCoreImpl<>(fromClause,columns,isDistinct,where,intoMeasurement);
}
