private void logParsingError(Element element,Class<? extends Annotation> annotation,Exception e){
  StringWriter stackTrace=new StringWriter();
  e.printStackTrace(new PrintWriter(stackTrace));
  error(element,"Unable to parse @%s binding.\n\n%s",annotation.getSimpleName(),stackTrace);
}
