public static <A,B,App extends Applicative<?,App>,AppMap extends Applicative<Map<A,B>,App>>AppMap sequence(Map<A,? extends Applicative<B,App>> mapApp,Fn1<Map<A,B>,? extends AppMap> pure){
  return Sequence.<A,B,App,AppMap>sequence(mapApp).apply(pure);
}
