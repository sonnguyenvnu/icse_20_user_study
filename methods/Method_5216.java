private void resolveUtcTimingElement(UtcTimingElement timingElement){
  String scheme=timingElement.schemeIdUri;
  if (Util.areEqual(scheme,"urn:mpeg:dash:utc:direct:2014") || Util.areEqual(scheme,"urn:mpeg:dash:utc:direct:2012")) {
    resolveUtcTimingElementDirect(timingElement);
  }
 else   if (Util.areEqual(scheme,"urn:mpeg:dash:utc:http-iso:2014") || Util.areEqual(scheme,"urn:mpeg:dash:utc:http-iso:2012")) {
    resolveUtcTimingElementHttp(timingElement,new Iso8601Parser());
  }
 else   if (Util.areEqual(scheme,"urn:mpeg:dash:utc:http-xsdate:2014") || Util.areEqual(scheme,"urn:mpeg:dash:utc:http-xsdate:2012")) {
    resolveUtcTimingElementHttp(timingElement,new XsDateTimeParser());
  }
 else {
    onUtcTimestampResolutionError(new IOException("Unsupported UTC timing scheme"));
  }
}
