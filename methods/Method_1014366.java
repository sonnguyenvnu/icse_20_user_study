/** 
 * Restore Nodes and Properties from Thing channels after handler initalization.
 * @param channels
 */
@SuppressWarnings({"null","unused"}) public void initialize(String baseTopic,String deviceID,List<Channel> channels){
  this.topic=baseTopic + "/" + deviceID;
  this.deviceID=deviceID;
  nodes.clear();
  for (  Channel channel : channels) {
    final String nodeID=channel.getUID().getGroupId();
    final String propertyID=channel.getUID().getIdWithoutGroup();
    if (nodeID == null) {
      continue;
    }
    Node node=nodes.get(nodeID);
    if (node == null) {
      node=createNode(nodeID);
      node.nodeRestoredFromConfig();
      nodes.put(nodeID,node);
    }
    Property property=node.createProperty(propertyID,channel.getConfiguration().as(PropertyAttributes.class));
    property.createChannelFromAttribute();
    node.properties.put(propertyID,property);
  }
}
