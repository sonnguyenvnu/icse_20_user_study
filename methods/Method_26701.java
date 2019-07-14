public static <T,U extends Unifiable<? super T>>Function<Unifier,Choice<Unifier>> unifications(@Nullable final U unifiable,@Nullable final T target){
  return (  Unifier unifier) -> unifyNullable(unifier,unifiable,target);
}
