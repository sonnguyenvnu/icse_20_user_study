/** 
 * Prepares a child source. <p> {@link #onChildSourceInfoRefreshed(Object,MediaSource,Timeline,Object)} will be calledwhen the child source updates its timeline and/or manifest with the same  {@code id} passed tothis method. <p>Any child sources that aren't explicitly released with  {@link #releaseChildSource(Object)}will be released in  {@link #releaseSourceInternal()}.
 * @param id A unique id to identify the child source preparation. Null is allowed as an id.
 * @param mediaSource The child {@link MediaSource}.
 */
protected final void prepareChildSource(final T id,MediaSource mediaSource){
  Assertions.checkArgument(!childSources.containsKey(id));
  SourceInfoRefreshListener sourceListener=(source,timeline,manifest) -> onChildSourceInfoRefreshed(id,source,timeline,manifest);
  MediaSourceEventListener eventListener=new ForwardingEventListener(id);
  childSources.put(id,new MediaSourceAndListener(mediaSource,sourceListener,eventListener));
  mediaSource.addEventListener(Assertions.checkNotNull(eventHandler),eventListener);
  mediaSource.prepareSource(sourceListener,mediaTransferListener);
}
