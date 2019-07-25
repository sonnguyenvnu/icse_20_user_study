@SuppressWarnings("unchecked") public static <A extends B,B>Downcast<A,B> downcast(){
  return (Downcast<A,B>)INSTANCE;
}
