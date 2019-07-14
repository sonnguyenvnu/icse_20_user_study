public static Disposable saveMyRepos(@NonNull List<Repo> models,@NonNull String reposOwner){
  return RxHelper.getSingle(Single.fromPublisher(s -> {
    try {
      Login login=Login.getUser();
      if (login != null) {
        BlockingEntityStore<Persistable> dataSource=App.getInstance().getDataStore().toBlocking();
        if (login.getLogin().equalsIgnoreCase(reposOwner)) {
          dataSource.delete(Repo.class).where(REPOS_OWNER.eq(reposOwner)).get().value();
          if (!models.isEmpty()) {
            for (            Repo repo : models) {
              dataSource.delete(Repo.class).where(Repo.ID.eq(repo.getId())).get().value();
              repo.setReposOwner(reposOwner);
              dataSource.insert(repo);
            }
          }
        }
 else {
          dataSource.delete(Repo.class).where(REPOS_OWNER.notEqual(login.getLogin()).or(REPOS_OWNER.isNull())).get().value();
        }
      }
      s.onNext("");
    }
 catch (    Exception e) {
      s.onError(e);
    }
    s.onComplete();
  }
)).subscribe(o -> {
  }
,Throwable::printStackTrace);
}
