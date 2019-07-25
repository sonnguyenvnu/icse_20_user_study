/** 
 * This method is called just before or just after an object is synchronized.
 * @param o the object, or null for the current class
 * @return the object that was passed
 */
public static Object begin(Object o){
  if (o == null) {
    o=new SecurityManager(){
    }
.clazz;
  }
  Deque<Object> stack=STACK.get();
  if (!stack.isEmpty()) {
    if (stack.contains(o)) {
      return o;
    }
    while (!stack.isEmpty()) {
      Object last=stack.peek();
      if (Thread.holdsLock(last)) {
        break;
      }
      stack.pop();
    }
  }
  if (TRACE) {
    String thread="[thread " + Thread.currentThread().getId() + "]";
    String indent=new String(new char[stack.size() * 2]).replace((char)0,' ');
    System.out.println(thread + " " + indent + "sync " + getObjectName(o));
  }
  if (!stack.isEmpty()) {
    markHigher(o,stack);
  }
  stack.push(o);
  return o;
}
