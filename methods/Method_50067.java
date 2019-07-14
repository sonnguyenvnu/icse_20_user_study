/** 
 * Writes color table
 */
private void writePalette() throws IOException {
  out.write(colorTab,0,colorTab.length);
  int n=(3 * 256) - colorTab.length;
  for (int i=0; i < n; i++) {
    out.write(0);
  }
}
