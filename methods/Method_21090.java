/** 
 * Call when a masked wallet has been obtained and the content in the android pay confirmation should be rendered.
 */
private void updateAndroidPayConfirmation(final @NonNull MaskedWallet maskedWallet,final @NonNull AndroidPayPayload payload){
  Picasso.with(this).load(this.project.photo().full()).into(this.contextPhotoImageView);
  this.projectNameTextView.setText(this.project.name());
  this.creatorNameTextView.setText(this.ksString.format(this.projectCreatorByCreatorString,"creator_name",this.project.creator().name()));
  this.termsAndPrivacyTextView.setText(Html.fromHtml(this.termsAndPrivacyString));
  this.backer101TextView.setText(Html.fromHtml(this.backer101String));
  if (maskedWallet != null) {
    this.androidPayEmailTextView.setText(maskedWallet.getEmail());
    final String[] paymentDescriptions=maskedWallet.getPaymentDescriptions();
    if (paymentDescriptions.length > 0) {
      this.androidPayInstrumentDescriptionTextView.setText(paymentDescriptions[0]);
    }
  }
  this.pledgeDisclaimerTextView.setText(Html.fromHtml(this.ksString.format(this.pledgeDisclaimerString,"charge_amount",this.ksCurrency.format(Float.valueOf(payload.cart().totalPrice()),this.project,RoundingMode.HALF_UP))));
  this.confirmationWalletFragment=SupportWalletFragment.newInstance(WalletFragmentOptions.newBuilder().setEnvironment(AndroidPayUtils.environment(this.build)).setTheme(WalletConstants.THEME_LIGHT).setMode(WalletFragmentMode.SELECTION_DETAILS).build());
  this.confirmationWalletFragment.initialize(WalletFragmentInitParams.newBuilder().setMaskedWallet(maskedWallet).setMaskedWalletRequestCode(ActivityRequestCodes.CHECKOUT_ACTIVITY_WALLET_CHANGE_REQUEST).build());
  getSupportFragmentManager().beginTransaction().replace(R.id.confirmation_masked_wallet_fragment,this.confirmationWalletFragment).commit();
}
