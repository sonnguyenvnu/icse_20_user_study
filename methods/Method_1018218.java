/** 
 * stream/reader reset.
 * @param input
 * @throws IOException
 */
public void reset(Reader input) throws IOException {
  if (input != null)   reader=new IPushbackReader(new BufferedReader(input));
  idx=-1;
}
