public static <E extends JanusGraphElement>Condition<E> literalTransformation(Condition<E> condition,final Function<Condition<E>,Condition<E>> transformation){
  return transformation(condition,new Function<Condition<E>,Condition<E>>(){
    @Nullable @Override public Condition<E> apply(    final Condition<E> cond){
      if (cond.getType() == Condition.Type.LITERAL)       return transformation.apply(cond);
 else       return null;
    }
  }
);
}
