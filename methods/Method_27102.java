public static boolean isPinned(long pullRequestId){
  return get(pullRequestId) != null;
}
