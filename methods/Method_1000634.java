/** 
 * ??????, ???
 * @return ????
 * @throws IOException
 */
public int poll() throws IOException {
  peekindex=0;
  int v=-1;
  if (cache.size() <= 0) {
    v=is.read();
  }
 else {
    v=cache.poll();
  }
  if (v == -1) {
    end=true;
  }
  if (v == '\n') {
    col=0;
    row++;
  }
 else {
    col++;
  }
  return v;
}
