public String addUser(){
  User user=new User(1L,"mrbird","123456");
  HttpStatus status=this.restTemplate.postForEntity("http://Server-Provider/user",user,null).getStatusCode();
  if (status.is2xxSuccessful()) {
    return "??????";
  }
 else {
    return "??????";
  }
}
