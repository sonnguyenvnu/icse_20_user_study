/** 
 * Checks if two classes belong to the same package
 */
static boolean belongToTheSamePackage(TypeElement class1,TypeElement class2,Elements elements){
  Name package1=elements.getPackageOf(class1).getQualifiedName();
  Name package2=elements.getPackageOf(class2).getQualifiedName();
  return package1.equals(package2);
}
