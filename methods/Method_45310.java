public static Function<String,File> filenameToFile(){
  return new Function<String,File>(){
    @Override public File apply(    final String input){
      return new File(input);
    }
  }
;
}
