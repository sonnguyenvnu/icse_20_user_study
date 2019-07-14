/** 
 * Write tab-delimited entries to a PrintWriter
 */
public void write(PrintWriter writer){
  for (int i=0; i < count; i++) {
    writer.println(keys[i] + "\t" + values[i]);
  }
  writer.flush();
}
