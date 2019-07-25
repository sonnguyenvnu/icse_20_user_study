/** 
 * Method that will construct a new instance that will use specified pretty printer (or, if null, will not do any pretty-printing)
 */
public ObjectWriter with(PrettyPrinter pp){
  return _new(_generatorSettings.with(pp),_prefetch);
}
