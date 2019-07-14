/** 
 * Copies default properties from annotation to the check.
 */
protected void copyDefaultCheckProperties(final Check destCheck,final Annotation annotation){
  Integer severity=(Integer)ClassUtil.readAnnotationValue(annotation,ANN_SEVERITY);
  destCheck.setSeverity(severity.intValue());
  String[] profiles=(String[])ClassUtil.readAnnotationValue(annotation,ANN_PROFILES);
  destCheck.setProfiles(profiles);
  String message=(String)ClassUtil.readAnnotationValue(annotation,ANN_MESSAGE);
  destCheck.setMessage(message);
}
