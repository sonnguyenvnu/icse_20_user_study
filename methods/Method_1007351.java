/** 
 * Removes this <code>CtClass</code> object from the <code>ClassPool</code>. After this method is called, any method cannot be called on the removed <code>CtClass</code> object. <p>If <code>get()</code> in <code>ClassPool</code> is called with the name of the removed method, the <code>ClassPool</code> will read the class file again and constructs another <code>CtClass</code> object representing the same class.
 */
public void detach(){
  ClassPool cp=getClassPool();
  CtClass obj=cp.removeCached(getName());
  if (obj != this)   cp.cacheCtClass(getName(),obj,false);
}
