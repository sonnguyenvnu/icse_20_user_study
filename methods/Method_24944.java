public void sendNewValue(){
  int index=varIndex;
  try {
    if ("int".equals(type)) {
      tweakClient.sendInt(index,newValue.intValue());
    }
 else     if ("hex".equals(type)) {
      tweakClient.sendInt(index,newValue.intValue());
    }
 else     if ("webcolor".equals(type)) {
      tweakClient.sendInt(index,newValue.intValue());
    }
 else     if ("float".equals(type)) {
      tweakClient.sendFloat(index,newValue.floatValue());
    }
  }
 catch (  Exception e) {
    System.out.println("error sending new value!");
  }
}
