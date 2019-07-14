/** 
 * Returns the error code contained in a wallet request. If no such error is found, `null` is returned.
 */
public static Integer walletRequestError(final @NonNull ActivityResult result){
  final Intent intent=result.intent();
  final int error=intent == null ? -1 : intent.getIntExtra(WalletConstants.EXTRA_ERROR_CODE,-1);
  return error == -1 ? null : error;
}
