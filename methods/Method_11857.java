private static Object objectWithToString(final String string){
  return new Object(){
    @Override public String toString(){
      return string;
    }
  }
;
}
