private ResourceServerTokenServices resourceTokenServices(){
  if (resourceTokenServices == null) {
    if (tokenServices instanceof ResourceServerTokenServices) {
      return (ResourceServerTokenServices)tokenServices;
    }
    resourceTokenServices=createDefaultTokenServices();
  }
  return resourceTokenServices;
}
