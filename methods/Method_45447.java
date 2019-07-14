private Function<String,InputStream> toStream(){
  return new Function<String,InputStream>(){
    @Override public InputStream apply(    final String input){
      try {
        return new FileInputStream(input);
      }
 catch (      FileNotFoundException e) {
        throw new MocoException(e);
      }
    }
  }
;
}
