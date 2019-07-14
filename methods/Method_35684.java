public static Predicate<BinaryFile> byFileExtension(final String extension){
  return new Predicate<BinaryFile>(){
    public boolean apply(    BinaryFile input){
      return input.name().endsWith("." + extension);
    }
  }
;
}
