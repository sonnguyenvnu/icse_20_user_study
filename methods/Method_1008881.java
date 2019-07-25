/** 
 * Skip a given number of bytes.
 * @param add The number of bytes to advance
 * @throws IOException In case of an I/O problem
 */
public void skip(long add) throws IOException {
  seekAdd(add);
}
