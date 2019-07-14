/** 
 * Adds a new method to a class with a given name and implementation. <h5>Discussion</h5> <p>class_addMethod will add an override of a superclass's implementation, but will not replace an existing implementation in this class. To change an existing implementation, use  {@link #method_setImplementation}.</p> <p>An Objective-C method is simply a C function that takes at least two arguments &ndash;  {@code self} and {@code _cmd}. For example, given the following function:</p> <pre><code> void myMethodIMP(id self, SEL _cmd) { // implementation .... }</code></pre> <p>you can dynamically add it to a class as a method (called  {@code resolveThisMethodDynamically}) like this:</p> <pre><code> class_addMethod([self class], @selector(resolveThisMethodDynamically), (IMP) myMethodIMP, "v@:");</code></pre>
 * @param cls   the class to which to add a method
 * @param name  a selector that specifies the name of the method being added
 * @param imp   a function which is the implementation of the new method. The function must take at least two arguments &ndash; {@code self} and {@code _cmd}.
 * @param types an array of characters that describe the types of the arguments to the method. For possible values, see <em>Objective-C Runtime ProgrammingGuide</em> &gt; Type Encodings in Objective-C Runtime Programming Guide. Since the function must take at least two arguments &ndash;  {@code self}and  {@code _cmd}, the second and third characters must be “@:” (the first character is the return type).
 * @return {@link #YES} if the method was added successfully, otherwise {@link #NO} (for example, the class already contains a method implementation with that name)
 */
@NativeType("BOOL") public static boolean class_addMethod(@NativeType("Class") long cls,@NativeType("SEL") long name,@NativeType("IMP") long imp,@NativeType("char const *") ByteBuffer types){
  if (CHECKS) {
    checkNT1(types);
  }
  return nclass_addMethod(cls,name,imp,memAddress(types));
}
