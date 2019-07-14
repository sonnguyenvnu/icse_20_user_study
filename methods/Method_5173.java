/** 
 * Removes a range of  {@link MediaSource}s from the playlist, by specifying an initial index (included) and a final index (excluded), and executes a custom action on completion. <p>Note: when specified range is empty, no actual media source is removed and no exception is thrown.
 * @param fromIndex The initial range index, pointing to the first media source that will beremoved. This index must be in the range of 0 &lt;= index &lt;=  {@link #getSize()}.
 * @param toIndex The final range index, pointing to the first media source that will be leftuntouched. This index must be in the range of 0 &lt;= index &lt;=  {@link #getSize()}.
 * @param handler The {@link Handler} to run {@code actionOnCompletion}.
 * @param actionOnCompletion A {@link Runnable} which is executed immediately after the mediasource range has been removed from the playlist.
 * @throws IllegalArgumentException When the range is malformed, i.e. {@code fromIndex} &lt; 0,{@code toIndex} &gt; {@link #getSize()},  {@code fromIndex} &gt; {@code toIndex}
 */
public final synchronized void removeMediaSourceRange(int fromIndex,int toIndex,Handler handler,Runnable actionOnCompletion){
  removePublicMediaSources(fromIndex,toIndex,handler,actionOnCompletion);
}
