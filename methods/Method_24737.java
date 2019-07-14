/** 
 * Handles the standard Java "Run" or "Present" 
 */
public Runner handleLaunch(Sketch sketch,RunnerListener listener,final boolean present) throws SketchException {
  JavaBuild build=new JavaBuild(sketch);
  String appletClassName=build.build(true);
  if (appletClassName != null) {
    final Runner runtime=new Runner(build,listener);
    new Thread(new Runnable(){
      public void run(){
        if (present) {
          runtime.present(null);
        }
 else {
          runtime.launch(null);
        }
      }
    }
).start();
    return runtime;
  }
  return null;
}
