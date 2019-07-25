/** 
 * Wraps the specified Guava  {@link Function} as a Scala {@link Function1}.
 */
public static <T1,R>Function1<T1,R> wrap(@Nonnull final Function<T1,R> function){
  return new GuavaFunction1<T1,R>(){
    @Override public R apply(    T1 v1){
      return function.apply(v1);
    }
    @Override public String toString(){
      return "ScalaUtil#wrap()";
    }
  }
;
}
