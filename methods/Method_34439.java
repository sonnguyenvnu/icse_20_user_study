public boolean supports(ConfigAttribute attribute){
  if (clientHasScope.equals(attribute.getAttribute())) {
    return true;
  }
 else {
    return false;
  }
}
