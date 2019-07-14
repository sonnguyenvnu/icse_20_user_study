@Override @Nullable public Choice<Unifier> visitNewArray(NewArrayTree newArray,@Nullable Unifier unifier){
  boolean hasRepeated=getInitializers() != null && Iterables.any(getInitializers(),Predicates.instanceOf(URepeated.class));
  return unifyNullable(unifier,getType(),newArray.getType()).thenChoose(unifications(getDimensions(),newArray.getDimensions())).thenChoose(unifications(getInitializers(),newArray.getInitializers(),hasRepeated));
}
