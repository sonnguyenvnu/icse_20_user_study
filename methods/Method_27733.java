@Override public void onLoadOrgs(){
  makeRestCall(RestProvider.getOrgService(PrefGetter.isEnterprise()).getMyOrganizations().flatMap(userPageable -> {
    if (userPageable != null && userPageable.getItems() != null) {
      return Observable.fromIterable(userPageable.getItems());
    }
    return Observable.empty();
  }
).map(user -> {
    if (user != null)     user.setType("Organization");
    return user;
  }
).toList().toObservable(),list -> {
    List<User> myOrgs=new ArrayList<>();
    if (list != null && !list.isEmpty()) {
      myOrgs.addAll(list);
    }
    sendToView(view -> view.onNotifyAdapter(myOrgs));
  }
);
}
