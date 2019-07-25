private String transform(String label,String labelMappedOption){
  String ret=label;
  if (getFormatPattern(label) != null) {
    Matcher matcher=EXTRACT_TRANSFORMFUNCTION_PATTERN.matcher(label);
    if (matcher.find()) {
      String type=matcher.group(1);
      String pattern=matcher.group(2);
      String value=matcher.group(3);
      TransformationService transformation=TransformationHelper.getTransformationService(UIActivator.getContext(),type);
      if (transformation != null) {
        try {
          String transformationResult=transformation.transform(pattern,value);
          if (transformationResult != null) {
            ret=label.substring(0,label.indexOf("[") + 1) + transformationResult + "]";
          }
 else {
            logger.warn("transformation of type {} did not return a valid result",type);
            ret=label.substring(0,label.indexOf("[") + 1) + UnDefType.NULL + "]";
          }
        }
 catch (        TransformationException e) {
          logger.error("transformation throws exception [transformation={}, value={}]",transformation,value,e);
          ret=label.substring(0,label.indexOf("[") + 1) + value + "]";
        }
      }
 else {
        logger.warn("couldn't transform value in label because transformationService of type '{}' is unavailable",type);
        ret=label.substring(0,label.indexOf("[") + 1) + value + "]";
      }
    }
 else     if (labelMappedOption != null) {
      ret=labelMappedOption;
    }
  }
  return ret;
}
