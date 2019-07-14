private static Function<Class<? extends Extension>,Extension> toExtensions(){
  return new Function<Class<? extends Extension>,Extension>(){
    @SuppressWarnings("unchecked") public Extension apply(    Class<? extends Extension> extensionClass){
      try {
        return extensionClass.newInstance();
      }
 catch (      Exception e) {
        return throwUnchecked(e,Extension.class);
      }
    }
  }
;
}
