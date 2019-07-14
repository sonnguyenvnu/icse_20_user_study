/** 
 * ???
 * @param out ??DataOutputStream
 * @throws Exception ???IO???
 */
public void save(DataOutputStream out) throws Exception {
  out.writeInt(size);
  for (int i=0; i < size; i++) {
    out.writeInt(base[i]);
    out.writeInt(check[i]);
    out.writeInt(fail[i]);
    int output[]=this.output[i];
    if (output == null) {
      out.writeInt(0);
    }
 else {
      out.writeInt(output.length);
      for (      int o : output) {
        out.writeInt(o);
      }
    }
  }
  out.writeInt(l.length);
  for (  int length : l) {
    out.writeInt(length);
  }
}
