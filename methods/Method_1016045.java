public static <R,V extends Supplier<R> & TypeVisitor>R accept(Class<?> type,V visitor){
  accept(type,(TypeVisitor)visitor);
  return visitor.get();
}
