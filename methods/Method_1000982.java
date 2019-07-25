/** 
 * @return New stack frame, if addition is ok; null if not
 */
public ClassStack child(Class<?> cls){
  return new ClassStack(this,cls);
}
