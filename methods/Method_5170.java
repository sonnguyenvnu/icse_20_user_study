/** 
 * Removes a  {@link MediaSource} from the playlist.<p>Note: If you want to move the instance, it's preferable to use  {@link #moveMediaSource(int,int)} instead.<p>Note: If you want to remove a set of contiguous sources, it's preferable to use  {@link #removeMediaSourceRange(int,int)} instead.
 * @param index The index at which the media source will be removed. This index must be in therange of 0 &lt;= index &lt;  {@link #getSize()}.
 */
public final synchronized void removeMediaSource(int index){
  removePublicMediaSources(index,index + 1,null,null);
}
