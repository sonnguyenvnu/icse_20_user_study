protected void checkPurchases(@Nullable Action action){
  getPresenter().manageViewDisposable(RxBillingService.getInstance(this,BuildConfig.DEBUG).getPurchases(ProductType.IN_APP).doOnSubscribe(disposable -> showProgress(0)).subscribe((purchases,throwable) -> {
    hideProgress();
    if (throwable == null) {
      Logger.e(purchases);
      if (purchases != null && !purchases.isEmpty()) {
        for (        Purchase purchase : purchases) {
          String sku=purchase.sku();
          if (!InputHelper.isEmpty(sku)) {
            DonateActivity.Companion.enableProduct(sku,App.getInstance());
          }
        }
      }
    }
 else {
      throwable.printStackTrace();
    }
    if (action != null)     action.run();
  }
));
}
