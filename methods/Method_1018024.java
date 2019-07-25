/** 
 * Gets the progress of this transformation
 * @return the progress from 0.0 to 1.0
 */
public Double progress(){
  final int total=tasksTotal.get();
  if (total > 0) {
    return ((double)tasksCompleted.get()) / total;
  }
 else {
    return 0.0;
  }
}
