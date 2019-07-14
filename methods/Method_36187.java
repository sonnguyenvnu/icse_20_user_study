public List<DiffLine<?>> getLines(Map<String,RequestMatcherExtension> customMatcherExtensions){
  ImmutableList.Builder<DiffLine<?>> builder=ImmutableList.builder();
  DiffLine<RequestMethod> methodSection=new DiffLine<>("HTTP method",requestPattern.getMethod(),request.getMethod(),requestPattern.getMethod().getName());
  builder.add(methodSection);
  UrlPattern urlPattern=firstNonNull(requestPattern.getUrlMatcher(),anyUrl());
  DiffLine<String> urlSection=new DiffLine<>("URL",urlPattern,request.getUrl(),urlPattern.getExpected());
  builder.add(urlSection);
  builder.add(SPACER);
  addHeaderSection(requestPattern.combineBasicAuthAndOtherHeaders(),request.getHeaders(),builder);
  boolean anyQueryParams=false;
  if (requestPattern.getQueryParameters() != null) {
    Map<String,QueryParameter> requestQueryParams=Urls.splitQuery(URI.create(request.getUrl()));
    for (    Map.Entry<String,MultiValuePattern> entry : requestPattern.getQueryParameters().entrySet()) {
      String key=entry.getKey();
      MultiValuePattern pattern=entry.getValue();
      QueryParameter queryParameter=firstNonNull(requestQueryParams.get(key),QueryParameter.absent(key));
      String operator=generateOperatorString(pattern.getValuePattern()," = ");
      DiffLine<MultiValue> section=new DiffLine<>("Query",pattern,queryParameter,"Query: " + key + operator + pattern.getValuePattern().getValue());
      builder.add(section);
      anyQueryParams=true;
    }
  }
  if (anyQueryParams) {
    builder.add(SPACER);
  }
  boolean anyCookieSections=false;
  if (requestPattern.getCookies() != null) {
    Map<String,Cookie> cookies=firstNonNull(request.getCookies(),Collections.<String,Cookie>emptyMap());
    for (    Map.Entry<String,StringValuePattern> entry : requestPattern.getCookies().entrySet()) {
      String key=entry.getKey();
      StringValuePattern pattern=entry.getValue();
      Cookie cookie=firstNonNull(cookies.get(key),Cookie.absent());
      String operator=generateOperatorString(pattern,"=");
      DiffLine<String> section=new DiffLine<>("Cookie",pattern,cookie.isPresent() ? cookie.getValue() : "","Cookie: " + key + operator + pattern.getValue());
      builder.add(section);
      anyCookieSections=true;
    }
  }
  if (anyCookieSections) {
    builder.add(SPACER);
  }
  List<ContentPattern<?>> bodyPatterns=requestPattern.getBodyPatterns();
  addBodySection(bodyPatterns,new Body(request.getBody()),builder);
  List<MultipartValuePattern> multipartPatterns=requestPattern.getMultipartPatterns();
  if (multipartPatterns != null && !multipartPatterns.isEmpty()) {
    for (    MultipartValuePattern pattern : multipartPatterns) {
      if (!request.isMultipart()) {
        builder.add(new SectionDelimiter("[Multipart request body]",""));
      }
 else       if (!pattern.match(request).isExactMatch()) {
        for (        Request.Part part : request.getParts()) {
          builder.add(SPACER);
          String patternPartName=pattern.getName() == null ? "" : ": " + pattern.getName();
          String partName=part.getName() == null ? "" : part.getName();
          builder.add(new SectionDelimiter("[Multipart" + patternPartName + "]","[" + partName + "]"));
          builder.add(SPACER);
          if (!pattern.match(part).isExactMatch()) {
            addHeaderSection(pattern.getHeaders(),part.getHeaders(),builder);
            addBodySection(pattern.getBodyPatterns(),part.getBody(),builder);
            builder.add(SPACER);
          }
          builder.add(new SectionDelimiter("[/Multipart]","[/" + partName + "]"));
          builder.add(SPACER);
        }
      }
    }
  }
  if (requestPattern.hasInlineCustomMatcher()) {
    InlineCustomMatcherLine customMatcherLine=new InlineCustomMatcherLine(requestPattern.getMatcher(),request);
    builder.add(customMatcherLine);
  }
  if (requestPattern.hasNamedCustomMatcher()) {
    RequestMatcherExtension customMatcher=customMatcherExtensions.get(requestPattern.getCustomMatcher().getName());
    if (customMatcher != null) {
      NamedCustomMatcherLine namedCustomMatcherLine=new NamedCustomMatcherLine(customMatcher,requestPattern.getCustomMatcher().getParameters(),request);
      builder.add(namedCustomMatcherLine);
    }
 else {
      builder.add(new SectionDelimiter("[custom matcher: " + requestPattern.getCustomMatcher().getName() + "]"));
    }
  }
  return builder.build();
}
