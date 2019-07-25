public String report(long cutOffTimeMillis,String... separate){
  if (top == 0) {
    myStack[0].task.merge(new HashSet<>(Arrays.asList(separate)));
    StringBuilder sb=new StringBuilder();
    sb.append('[');
    sb.append(traceName);
    sb.append("]\n");
    myStack[0].task.toString(cutOffTimeMillis,sb,0);
    for (    String s : externalText) {
      sb.append(s);
      sb.append('\n');
    }
    return sb.toString();
  }
 else {
    return null;
  }
}
