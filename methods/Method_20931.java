public static Project saved(){
  return project().toBuilder().name("savedProject").isStarred(true).build();
}
