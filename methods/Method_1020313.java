/** 
 */
public void render(Node node,Transform transform){
  if (!(node instanceof Mesh || node instanceof Sprite3D || node instanceof Group) && node != null) {
    throw new IllegalArgumentException();
  }
  integrityCheck();
  final Node finalNode=node;
  final Transform finalTransform=transform;
  Platform.executeInUIThread(new M3gRunnable(){
    @Override public void doRun(){
      _renderNode(handle,finalNode.handle,finalTransform != null ? finalTransform.matrix : null);
    }
  }
);
}
