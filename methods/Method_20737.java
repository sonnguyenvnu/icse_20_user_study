/** 
 * Returns true if the activity result contains data for a full wallet.
 */
public static boolean isFullWalletRequest(final @NonNull ActivityResult result){
  final Intent intent=result.intent();
  return intent != null && intent.getIntExtra(WalletConstants.EXTRA_ERROR_CODE,-1) == -1 && intent.hasExtra(WalletConstants.EXTRA_FULL_WALLET) && result.resultCode() == Activity.RESULT_OK && result.requestCode() == ActivityRequestCodes.CHECKOUT_ACTIVITY_WALLET_OBTAINED_FULL;
}
