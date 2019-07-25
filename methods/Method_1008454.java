/** 
 * publishes a cluster change event to other nodes. if at least minMasterNodes acknowledge the change it is committed and will be processed by the master and the other nodes. <p> The method is guaranteed to throw a  {@link org.elasticsearch.discovery.Discovery.FailedToCommitClusterStateException}if the change is not committed and should be rejected. Any other exception signals the something wrong happened but the change is committed.
 */
public void publish(final ClusterChangedEvent clusterChangedEvent,final int minMasterNodes,final Discovery.AckListener ackListener) throws Discovery.FailedToCommitClusterStateException {
  final DiscoveryNodes nodes;
  final SendingController sendingController;
  final Set<DiscoveryNode> nodesToPublishTo;
  final Map<Version,BytesReference> serializedStates;
  final Map<Version,BytesReference> serializedDiffs;
  final boolean sendFullVersion;
  try {
    nodes=clusterChangedEvent.state().nodes();
    nodesToPublishTo=new HashSet<>(nodes.getSize());
    DiscoveryNode localNode=nodes.getLocalNode();
    final int totalMasterNodes=nodes.getMasterNodes().size();
    for (    final DiscoveryNode node : nodes) {
      if (node.equals(localNode) == false) {
        nodesToPublishTo.add(node);
      }
    }
    sendFullVersion=!discoverySettings.getPublishDiff() || clusterChangedEvent.previousState() == null;
    serializedStates=new HashMap<>();
    serializedDiffs=new HashMap<>();
    buildDiffAndSerializeStates(clusterChangedEvent.state(),clusterChangedEvent.previousState(),nodesToPublishTo,sendFullVersion,serializedStates,serializedDiffs);
    final BlockingClusterStatePublishResponseHandler publishResponseHandler=new AckClusterStatePublishResponseHandler(nodesToPublishTo,ackListener);
    sendingController=new SendingController(clusterChangedEvent.state(),minMasterNodes,totalMasterNodes,publishResponseHandler);
  }
 catch (  Exception e) {
    throw new Discovery.FailedToCommitClusterStateException("unexpected error while preparing to publish",e);
  }
  try {
    innerPublish(clusterChangedEvent,nodesToPublishTo,sendingController,sendFullVersion,serializedStates,serializedDiffs);
  }
 catch (  Discovery.FailedToCommitClusterStateException t) {
    throw t;
  }
catch (  Exception e) {
    if (sendingController.markAsFailed("unexpected error",e)) {
      throw new Discovery.FailedToCommitClusterStateException("unexpected error",e);
    }
 else {
      throw e;
    }
  }
}
