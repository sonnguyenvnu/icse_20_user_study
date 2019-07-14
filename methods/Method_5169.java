/** 
 * Adds a  {@link MediaSource} to the playlist and executes a custom action on completion.
 * @param index The index at which the new {@link MediaSource} will be inserted. This index mustbe in the range of 0 &lt;= index &lt;=  {@link #getSize()}.
 * @param mediaSource The {@link MediaSource} to be added to the list.
 * @param handler The {@link Handler} to run {@code actionOnCompletion}.
 * @param actionOnCompletion A {@link Runnable} which is executed immediately after the mediasource has been added to the playlist.
 */
public final synchronized void addMediaSource(int index,MediaSource mediaSource,Handler handler,Runnable actionOnCompletion){
  addPublicMediaSources(index,Collections.singletonList(mediaSource),handler,actionOnCompletion);
}
