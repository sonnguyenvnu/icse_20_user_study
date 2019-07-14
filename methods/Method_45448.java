private Function<? super GlobalSetting,Iterable<? extends File>> toFiles(){
  return new Function<GlobalSetting,Iterable<? extends File>>(){
    @Override public Iterable<? extends File> apply(    final GlobalSetting input){
      return from(input.includes()).transform(toFile());
    }
  }
;
}
