private static Result regularEquals(final Type commandType,final Type fallbackType){
  return Result.of(Objects.equal(commandType,fallbackType),new Supplier<List<Error>>(){
    @Override public List<Error> get(){
      return Collections.singletonList(new Error(commandType,String.format("Different types. Command type: '%s'; fallback type: '%s'",commandType,fallbackType),fallbackType));
    }
  }
);
}
