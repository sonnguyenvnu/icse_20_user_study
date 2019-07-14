public static boolean hasAddedRelations(byte lifecycle){
  return lifecycle == AddedRelations || lifecycle == Modified;
}
