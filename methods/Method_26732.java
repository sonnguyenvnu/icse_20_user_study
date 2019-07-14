static <T,U extends Unifiable<? super T>>boolean anyMatch(U toUnify,T target,Unifier unifier){
  return toUnify.unify(target,unifier).first().isPresent();
}
