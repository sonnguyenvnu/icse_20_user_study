public static Observable<Boolean> onMultipleLogin(@NonNull Login userModel,boolean isEnterprise,boolean isNew){
  return Observable.fromPublisher(s -> {
    Login currentUser=Login.getUser();
    if (currentUser != null) {
      currentUser.setIsLoggedIn(false);
      App.getInstance().getDataStore().toBlocking().update(currentUser);
    }
    if (!isEnterprise) {
      PrefGetter.resetEnterprise();
    }
    userModel.setIsLoggedIn(true);
    if (isNew) {
      userModel.setIsEnterprise(isEnterprise);
      userModel.setToken(isEnterprise ? PrefGetter.getEnterpriseToken() : PrefGetter.getToken());
      userModel.setOtpCode(isEnterprise ? PrefGetter.getEnterpriseOtpCode() : PrefGetter.getOtpCode());
      userModel.setEnterpriseUrl(isEnterprise ? PrefGetter.getEnterpriseUrl() : null);
      App.getInstance().getDataStore().toBlocking().delete(Login.class).where(Login.ID.eq(userModel.getId())).get().value();
      App.getInstance().getDataStore().toBlocking().insert(userModel);
    }
 else {
      if (isEnterprise) {
        PrefGetter.setTokenEnterprise(userModel.token);
        PrefGetter.setEnterpriseOtpCode(userModel.otpCode);
        PrefGetter.setEnterpriseUrl(userModel.enterpriseUrl);
      }
 else {
        PrefGetter.resetEnterprise();
        PrefGetter.setToken(userModel.token);
        PrefGetter.setOtpCode(userModel.otpCode);
      }
      App.getInstance().getDataStore().toBlocking().update(userModel);
    }
    s.onNext(true);
    s.onComplete();
  }
);
}
