public static ImmutableList<Supplier<Type>> fromStrings(Iterable<String> types){
  return ImmutableList.copyOf(Iterables.transform(types,new Function<String,Supplier<Type>>(){
    @Override public Supplier<Type> apply(    String input){
      return Suppliers.typeFromString(input);
    }
  }
));
}
