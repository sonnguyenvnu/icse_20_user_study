/** 
 * Processes a path element.
 * @return the OR of all return valuesfrom  {@code Consumer.processFileBytes()}.
 */
public boolean process(){
  File file=new File(pathname);
  return processOne(file,true);
}
