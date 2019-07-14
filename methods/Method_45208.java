public static MountPredicate include(final String glob){
  checkNotNullOrEmpty(glob,"Glob should not be null or empty");
  final PathMatcher matcher=FileSystems.getDefault().getPathMatcher("glob:" + glob);
  return new MountPredicate(){
    @Override public boolean apply(    final String filename){
      return matcher.matches(Paths.get(filename));
    }
  }
;
}
