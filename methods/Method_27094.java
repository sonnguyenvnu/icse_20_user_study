public static void pinUpin(@NonNull Gist gist){
  PinnedGists pinnedIssues=get(gist.getGistId().hashCode());
  if (pinnedIssues == null) {
    PinnedGists pinned=new PinnedGists();
    pinned.setLogin(Login.getUser().getLogin());
    pinned.setGist(gist);
    pinned.setGistId(gist.getGistId().hashCode());
    try {
      App.getInstance().getDataStore().toBlocking().insert(pinned);
    }
 catch (    Exception ignored) {
    }
  }
 else {
    delete(gist.getGistId().hashCode());
  }
}
