/** 
 * Encode/Decode functions, helpful when interacting with cassandra through cqlsh.
 */
@GET @Path("status") public Response status(){
  final List<ClusterNodeStatus> nodes=convert(cluster.getNodes());
  final ClusterStatus status=new ClusterStatus(nodes,cluster.getStatistics());
  return Response.status(Response.Status.OK).entity(status).build();
}
