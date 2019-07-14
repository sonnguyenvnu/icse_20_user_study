/** 
 * Clears the playlist and executes a custom action on completion.
 * @param handler The {@link Handler} to run {@code actionOnCompletion}.
 * @param actionOnCompletion A {@link Runnable} which is executed immediately after the playlisthas been cleared.
 */
public final synchronized void clear(Handler handler,Runnable actionOnCompletion){
  removeMediaSourceRange(0,getSize(),handler,actionOnCompletion);
}
