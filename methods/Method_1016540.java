/** 
 * Returns a source excerpt suitable for declaring this type element. <p>e.g.  {@code MyType<N extends Number, C extends Consumer<N>>}
 */
public Excerpt declaration(){
  return Excerpts.add("%s%s",getSimpleName(),declarationParameters());
}
