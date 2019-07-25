/** 
 * Returns a function that filters a document map based on the given include and exclude rules.
 * @see #filter(Map,String[],String[]) for details
 */
public static Function<Map<String,?>,Map<String,Object>> filter(String[] includes,String[] excludes){
  CharacterRunAutomaton matchAllAutomaton=new CharacterRunAutomaton(Automata.makeAnyString());
  CharacterRunAutomaton include;
  if (includes == null || includes.length == 0) {
    include=matchAllAutomaton;
  }
 else {
    Automaton includeA=Regex.simpleMatchToAutomaton(includes);
    includeA=makeMatchDotsInFieldNames(includeA);
    include=new CharacterRunAutomaton(includeA);
  }
  Automaton excludeA;
  if (excludes == null || excludes.length == 0) {
    excludeA=Automata.makeEmpty();
  }
 else {
    excludeA=Regex.simpleMatchToAutomaton(excludes);
    excludeA=makeMatchDotsInFieldNames(excludeA);
  }
  CharacterRunAutomaton exclude=new CharacterRunAutomaton(excludeA);
  return (map) -> filter(map,include,0,exclude,0,matchAllAutomaton);
}
