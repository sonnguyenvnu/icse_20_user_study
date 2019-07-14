private void attemptAndroidPayConfirmation(final @NonNull MaskedWallet maskedWallet,final @NonNull AndroidPayPayload payload){
  final FullWalletRequest fullWalletRequest=AndroidPayUtils.createFullWalletRequest(maskedWallet.getGoogleTransactionId(),payload);
  Wallet.Payments.loadFullWallet(this.googleApiClient,fullWalletRequest,ActivityRequestCodes.CHECKOUT_ACTIVITY_WALLET_OBTAINED_FULL);
}
