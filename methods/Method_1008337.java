/** 
 * Indexes bindings by type.
 */
void index(){
  for (  Binding<?> binding : state.getExplicitBindingsThisLevel().values()) {
    index(binding);
  }
}
