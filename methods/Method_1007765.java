/** 
 * Registers an InputParser to this factory
 * @param inputParser The input parser
 */
public void register(InputParser<E> inputParser){
  checkNotNull(inputParser);
  parsers.add(parsers.size() - 1,inputParser);
}
