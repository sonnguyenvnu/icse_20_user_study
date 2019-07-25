/** 
 * {@inheritDoc}
 */
public EventSequence process(File file) throws LoadingException, IOException {
  InputStream in=new BufferedInputStream(new FileInputStream(file));
  return process(new InputSource(in));
}
