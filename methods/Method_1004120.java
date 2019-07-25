@Override protected void content(final HTMLElement body) throws IOException {
  if (sessionInfos.isEmpty()) {
    body.p().text(MSG_NO_SESSIONS);
  }
 else {
    body.p().text(MSG_SESSIONS);
    sessionTable(body);
  }
  if (executionData.isEmpty()) {
    body.p().text(MSG_NO_EXECDATA);
  }
 else {
    body.p().text(MSG_EXECDATA);
    executionDataTable(body);
  }
}
