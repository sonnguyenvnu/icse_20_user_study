private byte[] process(IXDocReport report,List<DataContext> dataContext,Options options) throws XDocReportException {
  try {
    IContext context=createContext(report,dataContext);
    ByteArrayOutputStream out=new ByteArrayOutputStream();
    if (options == null) {
      report.process(context,out);
    }
 else {
      report.convert(context,options,out);
    }
    return out.toByteArray();
  }
 catch (  Throwable e) {
    throw new XDocReportException(e);
  }
}
