private void putUser(){
  showProgressDialog("????...");
  user=getUser();
  apijson.demo.server.model.User userRequest=new apijson.demo.server.model.User(user.getId());
  userRequest.setName(user.getName());
  userRequest.setSex(user.getSex());
  userRequest.setTag(user.getTag());
  userRequest.setHead(user.getHead());
  HttpRequest.put(new JSONRequest(userRequest).setTag(User.class.getSimpleName()),HTTP_PUT,this);
}
