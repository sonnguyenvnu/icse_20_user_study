public static boolean isEmpty(Condition<?> condition){
  return condition.getType() != Condition.Type.LITERAL && condition.numChildren() == 0;
}
