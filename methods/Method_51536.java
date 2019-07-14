private void checkRequiredAttributesArePresent(Element ruleElement){
  for (  String att : REQUIRED_ATTRIBUTES) {
    if (!ruleElement.hasAttribute(att)) {
      throw new IllegalArgumentException("Missing '" + att + "' attribute");
    }
  }
}
