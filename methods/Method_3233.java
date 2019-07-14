/** 
 * Reads a single word from a file, assuming space + tab + EOL to be word boundaries
 * @param raf
 * @return null if EOF
 * @throws IOException
 */
String readWord(BufferedReader raf) throws IOException {
  while (true) {
    if (wbp < wordsBuffer.length) {
      return wordsBuffer[wbp++];
    }
    String line=raf.readLine();
    if (line == null) {
      eoc=true;
      return null;
    }
    line=line.trim();
    if (line.length() == 0) {
      continue;
    }
    cache.writeInt(-3);
    wordsBuffer=line.split("\\s+");
    wbp=0;
    eoc=false;
  }
}
