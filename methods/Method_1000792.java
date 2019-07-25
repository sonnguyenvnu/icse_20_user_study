/** 
 * ??????
 */
public void write(String fileName) throws IOException {
  Files.copy(file,new File(fileName));
}
