/** 
 * Returns a stream of all infixes of this stream. A stream is considered to contain itself.
 * @return a stream of the infixes of this stream.
 */
public final Stream<Stream<A>> substreams(){
  return tails().bind(Stream::inits);
}
