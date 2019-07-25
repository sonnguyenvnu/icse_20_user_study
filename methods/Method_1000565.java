public IocObject clone(){
  return Json.fromJson(IocObject.class,Json.toJson(this));
}
