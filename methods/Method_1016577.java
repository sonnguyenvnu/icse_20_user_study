/** 
 * Highly efficient find-replace char sequence. 
 */
public void replace(String name,CharSequence original,CharSequence after){
  addStep(ReplaceStep.create(name,original,after));
}
