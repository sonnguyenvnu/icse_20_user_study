@ModelAttribute(value="user",binding=false) public Map<String,Object> getUser(@Nullable Principal principal){
  if (principal != null) {
    return singletonMap("name",principal.getName());
  }
  return emptyMap();
}
