@Override public void onFragmentCreated(@Nullable Bundle bundle){
  if (bundle == null || bundle.getString(BundleConstant.EXTRA) == null) {
    throw new NullPointerException("Either bundle or User is null");
  }
  login=bundle.getString(BundleConstant.EXTRA);
  if (login != null) {
    makeRestCall(RestProvider.getUserService(isEnterprise()).getUser(login).doOnComplete(() -> {
      loadPinnedRepos(login);
      loadOrgs();
    }
),userModel -> {
      onSendUserToView(userModel);
      if (userModel != null) {
        userModel.save(userModel);
        if (userModel.getType() != null && userModel.getType().equalsIgnoreCase("user")) {
          onCheckFollowStatus(login);
        }
      }
    }
);
  }
}
