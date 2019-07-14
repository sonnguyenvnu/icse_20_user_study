public static boolean hasSimpleTarget(@NonNull Transition transition){
  return !isNullOrEmpty(transition.getTargetIds()) || !isNullOrEmpty(transition.getTargetNames()) || !isNullOrEmpty(transition.getTargetTypes());
}
