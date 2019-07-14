/** 
 * Parses the header of the subtitle.
 * @param data A {@link ParsableByteArray} from which the header should be read.
 */
private void parseHeader(ParsableByteArray data){
  String currentLine;
  while ((currentLine=data.readLine()) != null) {
    if (currentLine.startsWith("[Events]")) {
      return;
    }
  }
}
