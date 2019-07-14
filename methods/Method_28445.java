@Override public void checkOrgMembership(@NonNull String org){
  makeRestCall(RestProvider.getOrgService(isEnterprise()).isMember(org,Login.getUser().getLogin()),booleanResponse -> sendToView(view -> {
    isMember=booleanResponse.code() == 204 ? 1 : 0;
    view.onInitOrg(isMember == 1);
  }
));
}
