/** 
 * Notify when the available list of read only bookies changes. This is a one-shot notification. To receive subsequent notifications the listener must be registered again.
 * @param listener the listener to notify
 */
public void _XXXXX_(final RegistrationListener listener) throws BKException {
  bkc.getMetadataClientDriver().getRegistrationClient().watchReadOnlyBookies(listener);
}