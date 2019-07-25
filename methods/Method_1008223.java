/** 
 * Rethrows <code>t</code> (identical object).
 */
private void rethrow(Throwable t){
  new Rethrower<Error>().rethrow(t);
}
