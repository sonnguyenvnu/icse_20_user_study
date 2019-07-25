/** 
 * Closes the stream. Writes to a closed stream will fail, reads will successfully read the bytes that are already in the buffer and then return -1 (EOF)
 * @throws IOException
 */
public void close() throws IOException {
  lock.lock();
  try {
    if (closed)     return;
    closed=true;
    not_empty.signalAll();
    not_full.signalAll();
  }
  finally {
    lock.unlock();
  }
}
