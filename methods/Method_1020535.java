/** 
 * ????
 */
public static ActivityStack create(){
  if (instance == null) {
    instance=new ActivityStack();
  }
  return instance;
}
