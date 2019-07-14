private Function<String,File> toFile(){
  return new Function<String,File>(){
    @Override public File apply(    final String input){
      return new File(input);
    }
  }
;
}
