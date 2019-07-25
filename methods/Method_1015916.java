/** 
 * Scans classpath to discover bean classes.
 * @param scanPath the paths to scan, using ',' as the separator. There are two types of the scanPath:<ul> <li>package: org.b3log.process</li> <li>ant-style classpath: org/b3log/** /*process.class</li> </ul>
 * @return discovered classes
 * @throws Exception exception
 */
public static Collection<Class<?>> discover(final String scanPath) throws Exception {
  if (StringUtils.isBlank(scanPath)) {
    throw new IllegalStateException("Please specify the [scanPath]");
  }
  LOGGER.debug("scanPath[" + scanPath + "]");
  final Collection<Class<?>> ret=new HashSet<>();
  final String[] splitPaths=scanPath.split(",");
  final String[] paths=ArrayUtils.concatenate(splitPaths,BUILT_IN_COMPONENT_PKGS);
  final Set<URL> urls=new LinkedHashSet<>();
  for (  String path : paths) {
    if (!AntPathMatcher.isPattern(path)) {
      path=path.replaceAll("\\.","/") + "/**/*.class";
    }
    urls.addAll(ClassPathResolver.getResources(path));
  }
  for (  final URL url : urls) {
    final DataInputStream classInputStream=new DataInputStream(url.openStream());
    final ClassFile classFile=new ClassFile(classInputStream);
    final String className=classFile.getName();
    final AnnotationsAttribute annotationsAttribute=(AnnotationsAttribute)classFile.getAttribute(AnnotationsAttribute.visibleTag);
    if (null == annotationsAttribute) {
      LOGGER.log(Level.TRACE,"The class [name={0}] is not a bean",className);
      continue;
    }
    final ConstPool constPool=classFile.getConstPool();
    final Annotation[] annotations=annotationsAttribute.getAnnotations();
    boolean maybeBeanClass=false;
    for (    final Annotation annotation : annotations) {
      final String typeName=annotation.getTypeName();
      if (typeName.equals(Singleton.class.getName())) {
        maybeBeanClass=true;
        break;
      }
      if (typeName.equals(RequestProcessor.class.getName()) || typeName.equals(Service.class.getName()) || typeName.equals(Repository.class.getName())) {
        final Annotation singletonAnnotation=new Annotation(Singleton.class.getName(),constPool);
        annotationsAttribute.addAnnotation(singletonAnnotation);
        classFile.addAttribute(annotationsAttribute);
        classFile.setVersionToJava5();
        maybeBeanClass=true;
        break;
      }
    }
    if (maybeBeanClass) {
      Class<?> clz=null;
      try {
        clz=Thread.currentThread().getContextClassLoader().loadClass(className);
      }
 catch (      final ClassNotFoundException e) {
        LOGGER.log(Level.ERROR,"Loads class [" + className + "] failed",e);
      }
      ret.add(clz);
    }
  }
  return ret;
}
