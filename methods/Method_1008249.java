private void reroute(RoutingAllocation allocation){
  assert hasDeadNodes(allocation) == false : "dead nodes should be explicitly cleaned up. See deassociateDeadNodes";
  if (allocation.routingNodes().unassigned().size() > 0) {
    removeDelayMarkers(allocation);
    gatewayAllocator.allocateUnassigned(allocation);
  }
  shardsAllocator.allocate(allocation);
  assert RoutingNodes.assertShardStats(allocation.routingNodes());
}
