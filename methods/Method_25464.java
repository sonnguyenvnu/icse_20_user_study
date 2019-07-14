/** 
 * Compute the total cost of this assignment including the costs of unassigned source and target terms.
 */
private static double computeCost(int[] assignments,double[][] costMatrix,double[] sourceTermDeletionCosts,double[] targetTermDeletionCosts){
  double totalCost=DoubleStream.of(targetTermDeletionCosts).sum();
  for (int sourceTermIndex=0; sourceTermIndex < assignments.length; sourceTermIndex++) {
    int targetTermIndex=assignments[sourceTermIndex];
    if (targetTermIndex == -1) {
      totalCost+=sourceTermDeletionCosts[sourceTermIndex];
    }
 else {
      totalCost+=costMatrix[sourceTermIndex][targetTermIndex];
      totalCost-=targetTermDeletionCosts[targetTermIndex];
    }
  }
  return totalCost;
}
