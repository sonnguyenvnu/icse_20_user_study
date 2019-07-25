/** 
 * Create a  {@link IndentingWriterFactory} with a single indenting strategy.
 * @param defaultIndentingStrategy the default indenting strategy to use
 * @return an {@link IndentingWriterFactory}
 */
public static IndentingWriterFactory create(Function<Integer,String> defaultIndentingStrategy){
  return new IndentingWriterFactory(new Builder(defaultIndentingStrategy));
}
