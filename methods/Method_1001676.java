public Pin create(int row,Object userData){
  if (userData == null) {
    return new Pin(row,userData);
  }
  Pin result=pins.get(userData);
  if (result == null) {
    result=new Pin(row,userData);
    pins.put(userData,result);
  }
  return result;
}
