/** 
 * Rename a file (RNFR / RNTO).
 * @param fromFileName the old file name
 * @param toFileName the new file name
 */
void rename(String fromFileName,String toFileName) throws IOException {
  send("RNFR " + fromFileName);
  readCode(350);
  send("RNTO " + toFileName);
  readCode(250);
}
