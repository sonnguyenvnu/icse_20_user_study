/** 
 * ???
 * @param out ??ObjectOutputStream
 * @throws IOException ???IO??
 */
public void save(ObjectOutputStream out) throws IOException {
  out.writeObject(base);
  out.writeObject(check);
  out.writeObject(fail);
  out.writeObject(output);
  out.writeObject(l);
}
