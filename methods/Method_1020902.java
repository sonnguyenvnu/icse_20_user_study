public Association associate(String obj){
  return (Association)ReflectHelper.method(this,obj);
}
