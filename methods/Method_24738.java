/** 
 * Start a sketch in tweak mode 
 */
public Runner handleTweak(Sketch sketch,RunnerListener listener) throws SketchException {
  final JavaEditor editor=(JavaEditor)listener;
  JavaBuild build=new JavaBuild(sketch);
  String appletClassName=build.build(true);
  if (appletClassName == null) {
    return null;
  }
  editor.initBaseCode();
  boolean requiresTweak=SketchParser.containsTweakComment(editor.baseCode);
  final SketchParser parser=new SketchParser(editor.baseCode,requiresTweak);
  final boolean launchInteractive=editor.automateSketch(sketch,parser);
  build=new JavaBuild(sketch);
  appletClassName=build.build(false);
  if (appletClassName != null) {
    final Runner runtime=new Runner(build,listener);
    new Thread(new Runnable(){
      public void run(){
        runtime.launch(null);
        if (launchInteractive) {
          SwingUtilities.invokeLater(new Runnable(){
            public void run(){
              editor.initEditorCode(parser.allHandles,false);
              editor.stopTweakMode(parser.allHandles);
            }
          }
);
        }
      }
    }
).start();
    if (launchInteractive) {
      SwingUtilities.invokeLater(new Runnable(){
        public void run(){
          editor.initEditorCode(parser.allHandles,false);
          editor.updateInterface(parser.allHandles,parser.colorBoxes);
          editor.startTweakMode();
        }
      }
);
    }
    return runtime;
  }
  return null;
}
