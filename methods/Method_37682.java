/** 
 * Builds a set of java core packages.
 */
private String[] buildJrePackages(final int javaVersionNumber){
  final ArrayList<String> packages=new ArrayList<>();
switch (javaVersionNumber) {
case 9:
case 8:
case 7:
case 6:
case 5:
    packages.add("com.sun.org.apache");
case 4:
  if (javaVersionNumber == 4) {
    packages.add("org.apache.crimson");
    packages.add("org.apache.xalan");
    packages.add("org.apache.xml");
    packages.add("org.apache.xpath");
  }
packages.add("org.ietf.jgss");
packages.add("org.w3c.dom");
packages.add("org.xml.sax");
case 3:
packages.add("org.omg");
packages.add("com.sun.corba");
packages.add("com.sun.jndi");
packages.add("com.sun.media");
packages.add("com.sun.naming");
packages.add("com.sun.org.omg");
packages.add("com.sun.rmi");
packages.add("sunw.io");
packages.add("sunw.util");
case 2:
packages.add("com.sun.java");
packages.add("com.sun.image");
case 1:
default :
packages.add("sun");
packages.add("java");
packages.add("javax");
break;
}
return packages.toArray(new String[0]);
}
