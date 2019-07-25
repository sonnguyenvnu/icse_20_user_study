public static <A,App extends Applicative<?,App>,AppIterable extends Applicative<Iterable<A>,App>>AppIterable sequence(Iterable<? extends Applicative<A,App>> iterableApp,Fn1<Iterable<A>,? extends AppIterable> pure){
  return Sequence.<A,App,AppIterable>sequence(iterableApp).apply(pure);
}
