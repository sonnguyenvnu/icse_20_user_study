protected final <T>Predicate<Field> isValidField(final T target){
  return and(not(or(isClassField(),isFinalField())),fieldExist(target));
}
