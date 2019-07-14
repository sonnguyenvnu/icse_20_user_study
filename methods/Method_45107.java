private Function<String[],Iterable<String>> arrayAsIterable(){
  return new Function<String[],Iterable<String>>(){
    @Override public Iterable<String> apply(    final String[] input){
      return ImmutableList.copyOf(input);
    }
  }
;
}
