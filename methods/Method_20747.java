/** 
 * Handles a config API error by logging the user out in the case of a 401. We will interpret 401's on the config request as meaning the user's current access token is no longer valid, as that endpoint should never 401 othewise.
 */
private void handleConfigApiError(final @NonNull ErrorEnvelope error){
  if (error.httpCode() == 401) {
    this.logout.execute();
    ApplicationUtils.startNewDiscoveryActivity(this.application);
  }
}
