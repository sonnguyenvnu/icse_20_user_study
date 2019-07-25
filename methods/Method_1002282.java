/** 
 * @param ctl required here to make the benchmark generate code correctly(JMH 0.2 issue, fixed on main)
 */
@Benchmark @Group("ring") @GroupThreads(1) public void loop(Control ctl,Link l){
  l.link();
}
