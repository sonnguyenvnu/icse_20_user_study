@GET @Path("/download/{reportID}/{processState}") @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.WILDCARD) public byte[] download(@PathParam("reportID") String reportID,@PathParam("processState") String processState) throws RemoteInvocationException {
  System.err.println("reportId " + reportID);
  System.err.println("processState " + processState);
  try {
    return delegate.download(reportID,processState);
  }
 catch (  XDocReportException e) {
    throw new RemoteInvocationException(e.getMessage(),e.getStackTrace());
  }
}
