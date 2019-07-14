/** 
 * Returns if the entry is in the Main space's probation queue. 
 */
public boolean inMainProbation(){
  return getQueueType() == PROBATION;
}
