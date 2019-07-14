/** 
 * Removes a  {@link MediaSource} from the playlist and executes a custom action on completion.<p>Note: If you want to move the instance, it's preferable to use  {@link #moveMediaSource(int,int,Handler,Runnable)} instead.<p>Note: If you want to remove a set of contiguous sources, it's preferable to use  {@link #removeMediaSourceRange(int,int,Handler,Runnable)} instead.
 * @param index The index at which the media source will be removed. This index must be in therange of 0 &lt;= index &lt;  {@link #getSize()}.
 * @param handler The {@link Handler} to run {@code actionOnCompletion}.
 * @param actionOnCompletion A {@link Runnable} which is executed immediately after the mediasource has been removed from the playlist.
 */
public final synchronized void removeMediaSource(int index,Handler handler,Runnable actionOnCompletion){
  removePublicMediaSources(index,index + 1,handler,actionOnCompletion);
}
