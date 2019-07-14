public static Observable<Issue> getIssue(long id){
  return App.getInstance().getDataStore().select(Issue.class).where(ID.equal(id)).get().observable();
}
