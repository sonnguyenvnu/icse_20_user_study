protected void endType(){
  if (arrayStack % 2 == 0) {
    arrayStack/=2;
  }
 else {
    while (arrayStack % 2 != 0) {
      arrayStack/=2;
      declaration.append("[]");
    }
  }
}
