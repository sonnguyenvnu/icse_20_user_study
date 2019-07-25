/** 
 * Constructs a monoid from the given sum function and zero value, which must follow the monoidal laws. Java 8+ users: use  {@link #monoidDef(Semigroup.Definition,Object)} instead.
 * @param sum  The sum function for the monoid.
 * @param zero The zero for the monoid.
 * @return A monoid instance that uses the given sun function and zero value.
 */
public static <A>Monoid<A> monoid(final F<A,F<A,A>> sum,final A zero){
  return new Monoid<>(new AltDefinition<A>(){
    @Override public F<A,A> prepend(    A a){
      return sum.f(a);
    }
    @Override public A empty(){
      return zero;
    }
  }
);
}
