/** 
 * ????????.
 * @param node ??????
 * @param value ???????
 */
public void fillEphemeralJobNode(final String node,final Object value){
  regCenter.persistEphemeral(jobNodePath.getFullPath(node),value.toString());
}
