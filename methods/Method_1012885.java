/** 
 * Updates with  {@link UpdateOptions}.
 */
public void update(UpdateOptions updateOptions){
  updateOptions.giverOption.ifPresent(s -> giver=s);
  updateOptions.giverSectionOption.ifPresent(s -> giverSection=s);
  updateOptions.recipientOption.ifPresent(s -> recipient=s);
  updateOptions.recipientSectionOption.ifPresent(s -> recipientSection=s);
  updateOptions.responseDetailsUpdateOption.ifPresent(this::setResponseDetails);
}
