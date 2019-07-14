/** 
 * <p> Get the names of all of the <code> {@link org.quartz.Job}</code> groups. </p>
 */
public List<String> getJobGroupNames(){
  List<String> outList;
synchronized (lock) {
    outList=new LinkedList<String>(jobsByGroup.keySet());
  }
  return outList;
}
