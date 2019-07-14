@SuppressWarnings("ConstantConditions") private void loadPinnedRepos(@NonNull String login){
  ApolloCall<GetPinnedReposQuery.Data> apolloCall=ApolloProdivder.INSTANCE.getApollo(isEnterprise()).query(GetPinnedReposQuery.builder().login(login).build());
  manageDisposable(RxHelper.getObservable(Rx2Apollo.from(apolloCall)).filter(dataResponse -> !dataResponse.hasErrors()).flatMap(dataResponse -> {
    if (dataResponse.data() != null && dataResponse.data().user() != null) {
      return Observable.fromIterable(dataResponse.data().user().pinnedRepositories().edges());
    }
    return Observable.empty();
  }
).map(GetPinnedReposQuery.Edge::node).toList().toObservable().subscribe(nodes1 -> {
    nodes.clear();
    nodes.addAll(nodes1);
    sendToView(view -> view.onInitPinnedRepos(nodes));
  }
,Throwable::printStackTrace));
}
