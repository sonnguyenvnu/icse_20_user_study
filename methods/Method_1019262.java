/** 
 * A unique (within a single Caliper run) name for this worker. Used for display to the user and in naming the file that worker output is written to.
 */
public String name(){
  return "worker-" + id;
}
