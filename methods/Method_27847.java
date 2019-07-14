@Override public void onFragmentCreated(@Nullable Bundle bundle){
  if (bundle == null || bundle.getString(BundleConstant.EXTRA) == null) {
    throw new NullPointerException("Either bundle or User is null");
  }
  login=bundle.getString(BundleConstant.EXTRA);
  if (login != null) {
    makeRestCall(RestProvider.getOrgService(isEnterprise()).getOrganization(login),this::onSendUserToView);
  }
}
