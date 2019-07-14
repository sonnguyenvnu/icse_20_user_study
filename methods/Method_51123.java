/** 
 * @return formatted dump of the StackList
 */
private static String dump(String description,List<StackObject> stackList){
  StringBuilder stringDump=new StringBuilder("Stack List (");
  stringDump.append(description).append(") ListDump:\n");
  for (  StackObject stackObject : stackList) {
    stringDump.append('\n').append(stackObject.toString());
  }
  return stringDump.toString();
}
