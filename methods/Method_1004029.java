@SuppressWarnings("unused") protected void process(HttpServletRequest request,HttpServletResponse response) throws IOException {
  String lastDateAddedToDashboardParameter=request.getParameter("lastDateAddedToDashboard");
  Date lastDateAdded=null;
  if (!Strings.isNullOrEmpty(lastDateAddedToDashboardParameter)) {
    long lastDateAddedLong=Long.parseLong(lastDateAddedToDashboardParameter);
    lastDateAdded=new Date(lastDateAddedLong);
  }
  response.addHeader("Access-Control-Allow-Origin","*");
  response.addHeader("Access-Control-Allow-Headers","Content-Type,Authorization,Access-Control-Allow-Origin,*");
  List<TestInformation> executedTestsInformation=Dashboard.loadTestInformationFromFile();
  executedTestsInformation.sort(Comparator.comparing(TestInformation::getAddedToDashboardTime));
  if (lastDateAdded != null) {
    Date finalLastDateAdded=lastDateAdded;
    executedTestsInformation=executedTestsInformation.stream().filter(testInformation -> new Date(testInformation.getAddedToDashboardTime()).compareTo(finalLastDateAdded) > 0).collect(Collectors.toList());
  }
  Gson gson=new GsonBuilder().setPrettyPrinting().create();
  byte[] testInformation=gson.toJson(executedTestsInformation).getBytes(UTF_8);
  response.setStatus(HTTP_OK);
  response.setContentType(JSON_UTF_8.toString());
  response.setContentLength(testInformation.length);
  try (ServletOutputStream out=response.getOutputStream()){
    out.write(testInformation);
  }
 }
