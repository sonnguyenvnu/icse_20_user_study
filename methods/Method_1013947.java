/** 
 * Creates a parameter builder
 * @param name configuration parameter name
 * @param type configuration parameter type
 * @return parameter builder
 */
public static ConfigDescriptionParameterBuilder create(String name,Type type){
  return new ConfigDescriptionParameterBuilder(name,type);
}
