private static void initialize(){
  VALUE_TO_ECI=new Hashtable(29);
  NAME_TO_ECI=new Hashtable(29);
  addCharacterSet(0,"Cp437");
  addCharacterSet(1,new String[]{"ISO8859_1","ISO-8859-1"});
  addCharacterSet(2,"Cp437");
  addCharacterSet(3,new String[]{"ISO8859_1","ISO-8859-1"});
  addCharacterSet(4,"ISO8859_2");
  addCharacterSet(5,"ISO8859_3");
  addCharacterSet(6,"ISO8859_4");
  addCharacterSet(7,"ISO8859_5");
  addCharacterSet(8,"ISO8859_6");
  addCharacterSet(9,"ISO8859_7");
  addCharacterSet(10,"ISO8859_8");
  addCharacterSet(11,"ISO8859_9");
  addCharacterSet(12,"ISO8859_10");
  addCharacterSet(13,"ISO8859_11");
  addCharacterSet(15,"ISO8859_13");
  addCharacterSet(16,"ISO8859_14");
  addCharacterSet(17,"ISO8859_15");
  addCharacterSet(18,"ISO8859_16");
  addCharacterSet(20,new String[]{"SJIS","Shift_JIS"});
}
