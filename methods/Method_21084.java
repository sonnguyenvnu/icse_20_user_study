/** 
 * This method is called from  {@link com.kickstarter.services.KSWebViewClient} when an Android Paypayload has been obtained from the webview.
 */
public void takeAndroidPayPayloadString(final @Nullable String payloadString){
  this.viewModel.inputs.takePayloadString(payloadString);
}
