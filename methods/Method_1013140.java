/** 
 * Is called during termination of the toolbox.  This implementation cancels all running prover jobs.
 */
public void terminate(){
  ProverHelper.cancelProverJobs(true);
}
