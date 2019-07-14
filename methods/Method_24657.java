/** 
 * Run the build inside a temporary build folder. Used for run/present.
 * @return null if compilation failed, main class name if not
 * @throws RunnerException
 */
public String build(boolean sizeWarning) throws SketchException {
  return build(sketch.makeTempFolder(),sketch.makeTempFolder(),sizeWarning);
}
