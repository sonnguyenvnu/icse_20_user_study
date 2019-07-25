/** 
 * Ends this test session for the hub, releasing the resources in the hub / registry. It does not release anything on the remote. The resources are released in a separate thread, so the call returns immediately. It allows release with long duration not to block the test while the hub is releasing the resource.
 * @param session The session to terminate
 * @param reason  the reason for termination
 */
public void terminate(final TestSession session,final SessionTerminationReason reason){
  String remoteName="";
  if (session.getSlot().getProxy() instanceof DockerSeleniumRemoteProxy) {
    remoteName=((DockerSeleniumRemoteProxy)session.getSlot().getProxy()).getRegistration().getContainerId();
  }
  String internalKey=Optional.ofNullable(session.getInternalKey()).orElse("No internal key");
  ExternalSessionKey externalKey=Optional.ofNullable(session.getExternalKey()).orElse(new ExternalSessionKey("No external key was assigned"));
  new Thread(() -> _release(session.getSlot(),reason),"Terminate Test Session int id: [" + internalKey + "] ext id: [" + externalKey + "] container: [" + remoteName + "]").start();
}
