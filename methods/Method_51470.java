private <T>String getBody(){
  String result="## " + rule.getName() + "\\n\\n" + "Since: PMD " + rule.getSince() + "\\n\\n" + "Priority: " + rule.getPriority() + "\\n\\n" + "[Categories](https://github.com/codeclimate/spec/blob/master/SPEC.md#categories): " + Arrays.toString(getCategories()).replaceAll("[\\[\\]]","") + "\\n\\n" + "[Remediation Points](https://github.com/codeclimate/spec/blob/master/SPEC.md#remediation-points): " + getRemediationPoints() + "\\n\\n" + cleaned(rule.getDescription());
  if (!rule.getExamples().isEmpty()) {
    result+="\\n\\n### Example:\\n\\n";
    for (    String snippet : rule.getExamples()) {
      String exampleSnippet=snippet.replaceAll("\\n","\\\\n");
      exampleSnippet=exampleSnippet.replaceAll("\\t","\\\\t");
      result+="```java\\n" + exampleSnippet + "\\n```  ";
    }
  }
  if (!rule.getPropertyDescriptors().isEmpty()) {
    result+="\\n\\n### [PMD properties](" + PMD_PROPERTIES_URL + ")\\n\\n";
    result+="Name | Value | Description\\n";
    result+="--- | --- | ---\\n";
    for (    PropertyDescriptor<?> property : rule.getPropertyDescriptors()) {
      String propertyName=property.name().replaceAll("\\_","\\\\_");
      if (INTERNAL_DEV_PROPERTIES.contains(propertyName)) {
        continue;
      }
      @SuppressWarnings("unchecked") PropertyDescriptor<T> typed=(PropertyDescriptor<T>)property;
      T value=rule.getProperty(typed);
      String propertyValue=typed.asDelimitedString(value);
      if (propertyValue == null) {
        propertyValue="";
      }
      propertyValue=propertyValue.replaceAll("(\n|\r\n|\r)","\\\\n");
      result+=propertyName + " | " + propertyValue + " | " + property.description() + "\\n";
    }
  }
  return cleaned(result);
}
