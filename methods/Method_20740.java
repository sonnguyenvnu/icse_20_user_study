/** 
 * Constructs an authorized payload that can be sent back to our server from an Android Pay full wallet.
 */
public static @NonNull AndroidPayAuthorizedPayload authorizedPayloadFromFullWallet(final @NonNull FullWallet fullWallet,final @NonNull Gson gson){
  final InstrumentInfo instrumentInfo=fullWallet.getInstrumentInfos()[0];
  return AndroidPayAuthorizedPayload.builder().androidPayWallet(AndroidPayAuthorizedPayload.AndroidPayWallet.builder().googleTransactionId(fullWallet.getGoogleTransactionId()).instrumentDetails(instrumentInfo.getInstrumentDetails()).instrumentType(instrumentInfo.getInstrumentType()).build()).stripeToken(AndroidPayAuthorizedPayload.create(fullWallet.getPaymentMethodToken().getToken(),gson)).build();
}
