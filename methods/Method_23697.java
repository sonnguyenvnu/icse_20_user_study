/** 
 * Save tab-delimited entries to a file (TSV format, UTF-8 encoding)
 */
public void save(File file){
  PrintWriter writer=PApplet.createWriter(file);
  write(writer);
  writer.close();
}
