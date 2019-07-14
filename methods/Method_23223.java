/** 
 * For arrays, use printArray() instead. This function causes a warning because the new print(Object...) and println(Object...) functions can't be reliably bound by the compiler.
 */
static public void println(Object what){
  if (what == null) {
    System.out.println("null");
  }
 else   if (what.getClass().isArray()) {
    printArray(what);
  }
 else {
    System.out.println(what.toString());
    System.out.flush();
  }
}
