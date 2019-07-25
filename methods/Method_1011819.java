public static void bar(){
  Box<String> stringBox=new BoxImpl<String>("1");
  stringBox.assignValue("2");
  BLOperations.plusAssign(stringBox.asReference(),"1");
  System.out.println(stringBox.getValue());
}
