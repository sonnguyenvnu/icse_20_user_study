public Layer mute(){
  final Layer result=layer.duplicate();
  result.put(entity,newLongitude);
  return result;
}
