public void convert(Map<String,Object> contextMap,Options options,OutputStream out) throws XDocReportException, XDocConverterException, IOException {
  convert(createContext(contextMap),options,out);
}
