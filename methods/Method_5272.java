/** 
 * Checks two adaptation set content types for consistency, returning the consistent type, or throwing an  {@link IllegalStateException} if the types are inconsistent.<p> Two types are consistent if they are equal, or if one is  {@link C#TRACK_TYPE_UNKNOWN}. Where one of the types is  {@link C#TRACK_TYPE_UNKNOWN}, the other is returned.
 * @param firstType The first type.
 * @param secondType The second type.
 * @return The consistent type.
 */
private static int checkContentTypeConsistency(int firstType,int secondType){
  if (firstType == C.TRACK_TYPE_UNKNOWN) {
    return secondType;
  }
 else   if (secondType == C.TRACK_TYPE_UNKNOWN) {
    return firstType;
  }
 else {
    Assertions.checkState(firstType == secondType);
    return firstType;
  }
}
