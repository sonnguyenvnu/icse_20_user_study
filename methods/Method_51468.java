@Override public void renderFileViolations(Iterator<RuleViolation> violations) throws IOException {
  Writer writer=getWriter();
  Gson gson=new GsonBuilder().disableHtmlEscaping().create();
  while (violations.hasNext()) {
    RuleViolation rv=violations.next();
    rule=rv.getRule();
    String json=gson.toJson(asIssue(rv));
    json=json.replace(BODY_PLACEHOLDER,getBody());
    writer.write(json + NULL_CHARACTER + PMD.EOL);
  }
}
