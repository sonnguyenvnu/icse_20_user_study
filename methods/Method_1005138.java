/** 
 * ????
 */
public void add(){
  for (int i=0; i < getCriterionList().size(); i++) {
    add(getCriterionList().getParas(i));
  }
  getCriterionList().removeAll(getCriterionList());
}
