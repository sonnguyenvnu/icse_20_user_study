public static boolean matches(@NonNull String path1,@NonNull String path2){
  if (path1.equals(path2))   return true;
  List<Segment> segments1=pathToList(path1);
  List<Segment> segments2=pathToList(path2);
  if (segments1.size() != segments2.size())   return false;
  boolean matches=true;
  for (int i=0; i < segments1.size(); i++) {
    Segment segment=segments1.get(i);
    if (!segment.equals(segments2.get(i)) && !segment.isBlurred()) {
      matches=false;
      break;
    }
  }
  return matches;
}
