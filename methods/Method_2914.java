/** 
 * ???
 */
void setup_system(){
  system=new TransitionSystem();
  system.set_root_relation(deprels_alphabet.idOf(root));
  system.set_number_of_relations(deprels_alphabet.size() - 2);
}
