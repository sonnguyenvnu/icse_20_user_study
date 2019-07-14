/** 
 * Map the type to a file suffix associated with a  {@link Language}
 * @return inferred suffix
 */
public String getSuffixFromType(){
  LOG.entering(CLASS_NAME,"getSuffixFromType",this);
  if (null == type || type.isEmpty()) {
    return "";
  }
 else   if (type.toUpperCase(Locale.ROOT).indexOf("JAVA") >= 0) {
    return ".java";
  }
 else   if (type.toUpperCase(Locale.ROOT).indexOf("TRIGGER") >= 0) {
    return ".trg";
  }
 else   if (type.toUpperCase(Locale.ROOT).indexOf("FUNCTION") >= 0) {
    return ".fnc";
  }
 else   if (type.toUpperCase(Locale.ROOT).indexOf("PROCEDURE") >= 0) {
    return ".prc";
  }
 else   if (type.toUpperCase(Locale.ROOT).indexOf("PACKAGE_BODY") >= 0) {
    return ".pkb";
  }
 else   if (type.toUpperCase(Locale.ROOT).indexOf("PACKAGE") >= 0) {
    return ".pks";
  }
 else   if (type.toUpperCase(Locale.ROOT).indexOf("TYPE_BODY") >= 0) {
    return ".tpb";
  }
 else   if (type.toUpperCase(Locale.ROOT).indexOf("TYPE") >= 0) {
    return ".tps";
  }
 else {
    return "";
  }
}
