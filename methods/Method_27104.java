public static boolean isPinned(@NonNull String repoFullName){
  return get(repoFullName) != null;
}
