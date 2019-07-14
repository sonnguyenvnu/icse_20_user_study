/** 
 * Adds the child in front of any other childs.
 */
public void addFirst(AbstractReportNode child){
  childNodes.add(0,child);
  child.parentNode=this;
}
