private void out(final PrintStream output,final PSystemError err){
  output.println("protocolVersion=1");
  if (empty(err)) {
    output.println("status=NO_DATA");
  }
 else {
    output.println("status=ERROR");
    output.println("lineNumber=" + err.getLineLocation().getPosition());
    for (    ErrorUml er : err.getErrorsUml()) {
      output.println("label=" + er.getError());
    }
  }
  output.flush();
}
