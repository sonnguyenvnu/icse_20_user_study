/** 
 * builds a predicate that will accept a cluster state only if it was generated after the current has (re-)joined the master
 */
public static Predicate<ClusterState> build(ClusterState currentState){
  final long currentVersion=currentState.version();
  final DiscoveryNode masterNode=currentState.nodes().getMasterNode();
  final String currentMasterId=masterNode == null ? null : masterNode.getEphemeralId();
  return newState -> {
    final DiscoveryNode newMaster=newState.nodes().getMasterNode();
    final boolean accept;
    if (newMaster == null) {
      accept=false;
    }
 else     if (newMaster.getEphemeralId().equals(currentMasterId) == false) {
      accept=true;
    }
 else {
      accept=newState.version() > currentVersion;
    }
    return accept;
  }
;
}
