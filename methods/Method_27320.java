@NonNull public static IssueService getIssueService(boolean enterprise){
  return provideRetrofit(enterprise).create(IssueService.class);
}
