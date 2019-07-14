@Override public boolean onCallApi(int page){
  if (page == 1) {
    lastPage=Integer.MAX_VALUE;
    sendToView(view -> view.getLoadMore().reset());
  }
  if (page > lastPage || lastPage == 0) {
    sendToView(FeedsMvp.View::hideProgress);
    return false;
  }
  setCurrentPage(page);
  Login login=Login.getUser();
  if (login == null)   return false;
  Observable<Pageable<Event>> observable;
  Logger.e(isOrg);
  if (user != null) {
    if (isOrg) {
      observable=RestProvider.getOrgService(isEnterprise()).getReceivedEvents(login.getLogin(),user,page);
    }
 else {
      observable=RestProvider.getUserService(login.getLogin().equalsIgnoreCase(user) ? PrefGetter.isEnterprise() : isEnterprise()).getUserEvents(user,page);
    }
  }
 else {
    observable=RestProvider.getUserService(PrefGetter.isEnterprise()).getReceivedEvents(login.getLogin(),page);
  }
  makeRestCall(observable,response -> {
    lastPage=response.getLast();
    if (getCurrentPage() == 1) {
      manageDisposable(Event.save(response.getItems(),user));
    }
    sendToView(view -> view.onNotifyAdapter(response.getItems(),page));
  }
);
  return true;
}
