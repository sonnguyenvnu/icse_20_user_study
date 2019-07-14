/** 
 * Adds a  {@link MediaSource} to the playlist.
 * @param index The index at which the new {@link MediaSource} will be inserted. This index mustbe in the range of 0 &lt;= index &lt;=  {@link #getSize()}.
 * @param mediaSource The {@link MediaSource} to be added to the list.
 */
public final synchronized void addMediaSource(int index,MediaSource mediaSource){
  addPublicMediaSources(index,Collections.singletonList(mediaSource),null,null);
}
