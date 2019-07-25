/** 
 * Compares the two specified xml files and prints the diff onto the given writer.
 * @param seq1   The first XML reader to compare.
 * @param seq2   The first XML reader to compare.
 * @param out    Where the output goes.
 * @param config The DiffX configuration to use.
 * @throws DiffXException Should a Diff-X exception occur.
 * @throws IOException    Should an I/O exception occur.
 */
private static void diff(EventSequence seq1,EventSequence seq2,Writer out,DiffXConfig config) throws DiffXException, IOException {
  SafeXMLFormatter formatter=new SafeXMLFormatter(out);
  formatter.declarePrefixMapping(seq1.getPrefixMapping());
  formatter.declarePrefixMapping(seq2.getPrefixMapping());
  if (config != null) {
    formatter.setConfig(config);
  }
  SequenceSlicer slicer=new SequenceSlicer(seq1,seq2);
  slicer.slice();
  slicer.formatStart(formatter);
  DiffXAlgorithm df=new GuanoAlgorithm(seq1,seq2);
  df.process(formatter);
  slicer.formatEnd(formatter);
}
