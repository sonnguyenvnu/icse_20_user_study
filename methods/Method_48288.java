public static boolean matches(SliceQuery query,StaticBuffer column){
  return query.getSliceStart().compareTo(column) <= 0 && query.getSliceEnd().compareTo(column) > 0;
}
