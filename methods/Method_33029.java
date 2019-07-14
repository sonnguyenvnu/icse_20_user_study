/** 
 * * Static properties and methods                                            *
 */
private static <T>StringConverter<T> defaultStringConverter(){
  return new StringConverter<T>(){
    @Override public String toString(    T t){
      return t == null ? null : t.toString();
    }
    @Override public T fromString(    String string){
      return (T)string;
    }
  }
;
}
