/** 
 * Calculate the best query plan to use.
 * @param parse If we do not need to really get the best plan because it isa view parsing stage.
 */
void optimize(boolean parse){
  if (parse) {
    calculateFakePlan();
  }
 else {
    calculateBestPlan();
    bestPlan.removeUnusableIndexConditions();
  }
  TableFilter[] f2=bestPlan.getFilters();
  topFilter=f2[0];
  for (int i=0; i < f2.length - 1; i++) {
    f2[i].addJoin(f2[i + 1],false,null);
  }
  if (parse) {
    return;
  }
  for (  TableFilter f : f2) {
    PlanItem item=bestPlan.getItem(f);
    f.setPlanItem(item);
  }
}
