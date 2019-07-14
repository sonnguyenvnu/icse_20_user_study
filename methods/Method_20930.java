public static Project featured(){
  return project().toBuilder().name("featuredProject").featuredAt(new DateTime()).build();
}
