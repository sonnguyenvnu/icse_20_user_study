@SuppressWarnings("unchecked") private static Constructor<? extends StringValuePattern> findConstructor(Class<? extends StringValuePattern> clazz){
  Optional<Constructor<?>> optionalConstructor=tryFind(asList(clazz.getDeclaredConstructors()),new Predicate<Constructor<?>>(){
    @Override public boolean apply(    Constructor<?> input){
      return input.getParameterTypes().length == 1 && input.getGenericParameterTypes()[0].equals(String.class);
    }
  }
);
  if (!optionalConstructor.isPresent()) {
    throw new IllegalStateException("Constructor for " + clazz.getSimpleName() + " must have a single string argument constructor");
  }
  return (Constructor<? extends StringValuePattern>)optionalConstructor.get();
}
