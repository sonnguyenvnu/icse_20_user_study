/** 
 * Parses a given specification using the algorithm depicted in <a href="http://www.faqs.org/rfcs/rfc1808.html">RFC1808</a>: Section 2.4: Parsing a URL An accepted method for parsing URLs is useful to clarify the generic-RL syntax of Section 2.2 and to describe the algorithm for resolving relative URLs presented in Section 4. This section describes the parsing rules for breaking down a URL (relative or absolute) into the component parts described in Section 2.1.  The rules assume that the URL has already been separated from any surrounding text and copied to a "parse string". The rules are listed in the order in which they would be applied by the parser.
 * @param spec The specification to parse.
 * @return the parsed specification.
 */
private static Url parseUrl(final String spec){
  final Url url=new Url();
  int startIndex=0;
  int endIndex=spec.length();
  final int crosshatchIndex=indexOf(spec,'#',startIndex,endIndex);
  if (crosshatchIndex >= 0) {
    url.fragment=spec.substring(crosshatchIndex + 1,endIndex);
    endIndex=crosshatchIndex;
  }
  final int colonIndex=indexOf(spec,':',startIndex,endIndex);
  if (colonIndex > 0) {
    final String scheme=spec.substring(startIndex,colonIndex);
    if (isValidScheme(scheme)) {
      url.scheme=scheme;
      startIndex=colonIndex + 1;
    }
  }
  final int locationStartIndex;
  int locationEndIndex;
  if (spec.startsWith("//",startIndex)) {
    locationStartIndex=startIndex + 2;
    locationEndIndex=indexOf(spec,'/',locationStartIndex,endIndex);
    if (locationEndIndex >= 0) {
      startIndex=locationEndIndex;
    }
  }
 else {
    locationStartIndex=-1;
    locationEndIndex=-1;
  }
  final int questionMarkIndex=indexOf(spec,'?',startIndex,endIndex);
  if (questionMarkIndex >= 0) {
    if ((locationStartIndex >= 0) && (locationEndIndex < 0)) {
      locationEndIndex=questionMarkIndex;
      startIndex=questionMarkIndex;
    }
    url.query=spec.substring(questionMarkIndex + 1,endIndex);
    endIndex=questionMarkIndex;
  }
  final int semicolonIndex=indexOf(spec,';',startIndex,endIndex);
  if (semicolonIndex >= 0) {
    if ((locationStartIndex >= 0) && (locationEndIndex < 0)) {
      locationEndIndex=semicolonIndex;
      startIndex=semicolonIndex;
    }
    url.parameters=spec.substring(semicolonIndex + 1,endIndex);
    endIndex=semicolonIndex;
  }
  if ((locationStartIndex >= 0) && (locationEndIndex < 0)) {
    locationEndIndex=endIndex;
  }
 else   if (startIndex < endIndex) {
    url.path=spec.substring(startIndex,endIndex);
  }
  if ((locationStartIndex >= 0) && (locationEndIndex >= 0)) {
    url.location=spec.substring(locationStartIndex,locationEndIndex);
  }
  return url;
}
