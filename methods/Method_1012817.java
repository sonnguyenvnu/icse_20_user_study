/** 
 * See the general contract of  {@link InputStream#mark(int)}.
 * @param readlimit - ignored.
 * @see ResettableInputStream#reset()
 */
@Override public synchronized void mark(int readlimit){
  overriddenMarkpos=pos;
}
