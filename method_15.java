/** 
 * Logic on handling channel exceptions.
 * @param storageServerChannel server channel
 * @param cause                exception on establishing channel.
 */
@Override public void _XXXXX_(StorageServerChannel storageServerChannel,Throwable cause){
  if (null != cause) {
    resultFuture.completeExceptionally(cause);
    return;
  }
  sendRpcToServerChannel(storageServerChannel);
}