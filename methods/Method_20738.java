public static boolean isMaskedWalletRequest(final @NonNull ActivityResult result){
  final Intent intent=result.intent();
  return intent != null && intent.getIntExtra(WalletConstants.EXTRA_ERROR_CODE,-1) == -1 && result.requestCode() == ActivityRequestCodes.CHECKOUT_ACTIVITY_WALLET_REQUEST || result.requestCode() == ActivityRequestCodes.CHECKOUT_ACTIVITY_WALLET_CHANGE_REQUEST;
}
