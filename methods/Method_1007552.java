/** 
 * Analyzes the list of supplied classes and inserts Kilim-related bytecode if necessary. If a supplied class is dependent upon another class X, it is the caller's responsibility to ensure that X is either in the classpath, or loaded by the context classloader, or has been seen in an earlier invocation of weave().   Since weave() remembers method signatures from earlier invocations, the woven classes do not have to be classloaded to help future invocations of weave.  If two classes A and B are not in the classpath, and are mutually recursive, they can be woven only if supplied in the same input list. This method is thread safe.
 * @param classes A list of (className, byte[]) pairs. The first part is a fully qualified classname, and the second part is the bytecode for the class.
 * @return A list of (className, byte[]) pairs. Some of the classes may or may not have beenmodified, and new ones may be added.
 * @throws KilimException
 */
public List<ClassInfo> weave(List<ClassInfo> classes) throws KilimException, IOException {
  ArrayList<ClassInfo> ret=new ArrayList<ClassInfo>(classes.size());
  try {
    for (    ClassInfo cl : classes) {
      context.detector.mirrors.mirror(cl.bytes);
    }
    for (    ClassInfo cl : classes) {
      InputStream is=new ByteArrayInputStream(cl.bytes);
      ClassWeaver cw=new ClassWeaver(context,is);
      cw.weave();
      ret.addAll(cw.getClassInfos());
    }
    return ret;
  }
  finally {
  }
}
