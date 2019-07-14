/** 
 * Call when the android pay sheet should be shown.
 */
private void showAndroidPaySheet(final @NonNull AndroidPayPayload payload){
  if (this.walletFragment == null) {
    return;
  }
  this.isInAndroidPayFlow=true;
  final MaskedWalletRequest request=AndroidPayUtils.createMaskedWalletRequest(payload);
  this.walletFragment.initialize(WalletFragmentInitParams.newBuilder().setMaskedWalletRequest(request).setMaskedWalletRequestCode(ActivityRequestCodes.CHECKOUT_ACTIVITY_WALLET_REQUEST).build());
  AndroidPayUtils.triggerAndroidPaySheet(this.walletFragment);
}
