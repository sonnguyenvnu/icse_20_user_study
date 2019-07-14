/** 
 * Returns true if this package isn't part of a library (it's a system import or something like that). Don't bother complaining about java.* or javax. because it's probably in boot.class.path. But we're not checking against that path since it's enormous. Unfortunately we do still have to check for libraries that begin with a prefix like javax, since that includes the OpenGL library, even though we're just returning true here, hrm...
 */
protected boolean ignorableImport(String pkg){
  if (pkg.startsWith("java."))   return true;
  if (pkg.startsWith("javax."))   return true;
  if (pkg.startsWith("javafx."))   return true;
  if (pkg.startsWith("processing.core."))   return true;
  if (pkg.startsWith("processing.data."))   return true;
  if (pkg.startsWith("processing.event."))   return true;
  if (pkg.startsWith("processing.opengl."))   return true;
  return false;
}
