private static Function<String,Class<? extends Extension>> toClasses(){
  return new Function<String,Class<? extends Extension>>(){
    @SuppressWarnings("unchecked") public Class<? extends Extension> apply(    String className){
      try {
        return (Class<? extends Extension>)Class.forName(className);
      }
 catch (      ClassNotFoundException e) {
        return throwUnchecked(e,Class.class);
      }
    }
  }
;
}
