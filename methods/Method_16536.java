private <E>Function<Object,String> createGetter(Class<E> type,Function<E,String> getter){
  return entity -> {
    if (type.isInstance(entity)) {
      return getter.apply(((E)entity));
    }
    return defaultTargetIdGetter.apply(entity);
  }
;
}
