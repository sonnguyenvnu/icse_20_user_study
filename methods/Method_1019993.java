@SuppressWarnings("unchecked") public static <A,App extends Applicative<?,App>,Trav extends Traversable<?,Trav>,TravA extends Traversable<A,Trav>,AppTrav extends Applicative<TravA,App>>Sequence<A,App,Trav,TravA,AppTrav> sequence(){
  return (Sequence<A,App,Trav,TravA,AppTrav>)INSTANCE;
}
