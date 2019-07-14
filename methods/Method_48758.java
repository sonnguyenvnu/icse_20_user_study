public static boolean hasRemovedRelations(byte lifecycle){
  return lifecycle == RemovedRelations || lifecycle == Modified;
}
