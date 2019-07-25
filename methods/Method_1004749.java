public String prettify(String error){
  if (internalVersion.onOrAfter(EsMajorVersion.V_2_X)) {
    return error;
  }
  String invalidFragment=ErrorUtils.extractInvalidXContent(error);
  String header=(invalidFragment != null ? "Invalid JSON fragment received[" + invalidFragment + "]" : "");
  return header + "[" + error + "]";
}
