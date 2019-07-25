@POST @Path("/upload") @Consumes(MediaType.APPLICATION_JSON) public void upload(ReportRepresentation report) throws RemoteInvocationException {
  fr.opensagres.xdocreport.template.formatter.FieldsMetadata fieldsMetadata2=new fr.opensagres.xdocreport.template.formatter.FieldsMetadata();
  for (  String field : report.getFieldsMetaData()) {
    fieldsMetadata2.addFieldAsList(field);
  }
  try {
    delegate.registerReport(report.getReportID(),report.getDocument(),fieldsMetadata2,"Velocity");
  }
 catch (  XDocReportException e) {
    throw new RemoteInvocationException(e.getMessage(),e.getStackTrace());
  }
}
