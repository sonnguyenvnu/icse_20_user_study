/** 
 * Compares the two specified <code>NodeList</code>s and prints the diff onto the given writer. <p>Only the first node in the node list is sequenced.
 * @param xml1   The first XML node list to compare.
 * @param xml2   The second XML node list to compare.
 * @param out    Where the output goes.
 * @param config The DiffX configuration to use.
 * @throws DiffXException Should a Diff-X exception occur.
 * @throws IOException    Should an I/O exception occur.
 */
public static void diff(NodeList xml1,NodeList xml2,Writer out,DiffXConfig config) throws DiffXException, IOException {
  DOMRecorder loader=new DOMRecorder();
  if (config != null) {
    loader.setConfig(config);
  }
  EventSequence seq1=loader.process(xml1);
  EventSequence seq2=loader.process(xml2);
  diff(seq1,seq2,out,config);
}
