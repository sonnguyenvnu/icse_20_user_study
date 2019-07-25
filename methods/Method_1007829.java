/** 
 * Create a new progress object with progress split equally between the given progress objects.
 * @param progress a collection of progress objects
 * @return a new progress value
 */
public static Progress split(Collection<Progress> progress){
  int count=0;
  double total=0;
  for (  Progress p : progress) {
    if (p.isIndeterminate()) {
      return indeterminate();
    }
    total+=p.getProgress();
  }
  return of(total / count);
}
