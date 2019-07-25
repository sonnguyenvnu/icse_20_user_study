protected static void match(String expected_name,String name,boolean is_element) throws Exception {
  if (!expected_name.equals(name))   throw new Exception((is_element ? "Element " : "Attribute ") + "\"" + name + "\" didn't match \"" + expected_name + "\"");
}
