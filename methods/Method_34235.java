@org.codehaus.jackson.annotate.JsonProperty("authorities") @org.codehaus.jackson.map.annotate.JsonDeserialize(using=JacksonArrayOrStringDeserializer.class) @com.fasterxml.jackson.annotation.JsonProperty("authorities") @com.fasterxml.jackson.databind.annotation.JsonDeserialize(using=Jackson2ArrayOrStringDeserializer.class) private void setAuthoritiesAsStrings(Set<String> values){
  setAuthorities(AuthorityUtils.createAuthorityList(values.toArray(new String[values.size()])));
}
