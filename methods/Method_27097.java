public static void pinUpin(@NonNull Issue issue){
  PinnedIssues pinnedIssues=get(issue.getId());
  if (pinnedIssues == null) {
    PinnedIssues pinned=new PinnedIssues();
    pinned.setLogin(Login.getUser().getLogin());
    pinned.setIssue(issue);
    pinned.setIssueId(issue.getId());
    try {
      App.getInstance().getDataStore().toBlocking().insert(pinned);
    }
 catch (    Exception ignored) {
    }
  }
 else {
    delete(issue.getId());
  }
}
