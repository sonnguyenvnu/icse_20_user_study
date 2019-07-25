/** 
 * Search for an array of opcodes. For instance: <i>(Hello world opcodes)</i> <pre> Arrays.asList("GETSTATIC", "LDC", "INVOKEVIRTUAL") </pre>
 * @param opcodes List of opcode names to search for.
 * @return Opcode pattern parameter.
 */
public static Parameter opcodes(List<String> opcodes){
  return new Parameter(SearchType.OPCODE_PATTERN,opcodes);
}
