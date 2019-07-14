/** 
 * Replaces the implementation of a method for a given class. <h5>Discussion</h5> <p>This function behaves in two different ways:</p> <ul> <li>If the method identified by name does not yet exist, it is added as if class_addMethod were called. The type encoding specified by types is used as given.</li> <li>If the method identified by name does exist, its IMP is replaced as if method_setImplementation were called. The type encoding specified by types is ignored.</li> </ul>
 * @param cls   the class you want to modify
 * @param name  a selector that identifies the method whose implementation you want to replace
 * @param imp   the new implementation for the method identified by {@code name} for the class identified by {@code cls}
 * @param types an array of characters that describe the types of the arguments to the method. For possible values, see <em>Objective-C Runtime ProgrammingGuide</em> &gt; Type Encodings in Objective-C Runtime Programming Guide. Since the function must take at least two arguments &ndash;  {@code self}and  {@code _cmd}, the second and third characters must be “@:” (the first character is the return type).
 * @return the previous implementation of the method identified by {@code name} for the class identified by {@code cls}
 */
@NativeType("IMP") public static long class_replaceMethod(@NativeType("Class") long cls,@NativeType("SEL") long name,@NativeType("IMP") long imp,@NativeType("char const *") ByteBuffer types){
  if (CHECKS) {
    checkNT1(types);
  }
  return nclass_replaceMethod(cls,name,imp,memAddress(types));
}
