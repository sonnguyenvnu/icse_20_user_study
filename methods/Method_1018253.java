protected void init(String owner,MethodNode m) throws AnalyzerException {
  final Frame[] frames=getFrames();
  AbstractInsnNode insnNode=m.instructions.getFirst();
  FrameAnalyzer.TypeInterpreter interpreter=FrameAnalyzer.TypeInterpreter.instance;
  Frame lastFrame=frames[0];
  for (int insnIndex=0; insnNode != null; insnNode=insnNode.getNext(), insnIndex++) {
    if (insnNode instanceof FrameNode) {
      FrameNode frameNode=(FrameNode)insnNode;
      final int frameType=frameNode.type;
      if (frameType == F_NEW || frameType == F_FULL) {
        ExtendedFrame frame=(ExtendedFrame)newFrame(lastFrame);
        frame.force=true;
        frames[insnIndex]=frame;
        int iLocal_w=0;
        if (frameNode.local != null && frameNode.local.size() > 0) {
          for (int j=0; j < frameNode.local.size(); j++) {
            BasicValue value=convertFrameNodeType(frameNode.local.get(j));
            frame.setLocal(iLocal_w,value);
            iLocal_w+=value.getSize();
          }
        }
        BasicValue nullValue=interpreter.newValue(null);
        while (iLocal_w < m.maxLocals) {
          frame.setLocal(iLocal_w++,nullValue);
        }
        frame.clearStack();
        if (frameNode.stack != null && frameNode.stack.size() > 0) {
          for (int j=0; j < frameNode.stack.size(); j++) {
            frame.push(convertFrameNodeType(frameNode.stack.get(j)));
          }
        }
        lastFrame=frame;
      }
    }
  }
}
