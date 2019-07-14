/** 
 * Consumes payload data.
 * @param data The payload data to consume.
 * @param timeUs The timestamp associated with the payload.
 * @throws ParserException If an error occurs parsing the data.
 */
public final void consume(ParsableByteArray data,long timeUs) throws ParserException {
  if (parseHeader(data)) {
    parsePayload(data,timeUs);
  }
}
