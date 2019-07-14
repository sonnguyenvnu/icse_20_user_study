@Override public void addChild(PShape who,int idx){
  if (who instanceof PShapeOpenGL) {
    if (family == GROUP) {
      PShapeOpenGL c3d=(PShapeOpenGL)who;
      super.addChild(c3d,idx);
      c3d.updateRoot(root);
      markForTessellation();
      if (c3d.family == GROUP) {
        if (c3d.textures != null) {
          for (          PImage tex : c3d.textures) {
            addTexture(tex);
          }
        }
 else {
          untexChild(true);
        }
        if (c3d.strokedTexture) {
          strokedTexture(true);
        }
      }
 else {
        if (c3d.image != null) {
          addTexture(c3d.image);
          if (c3d.stroke) {
            strokedTexture(true);
          }
        }
 else {
          untexChild(true);
        }
      }
    }
 else {
      PGraphics.showWarning("Cannot add child shape to non-group shape.");
    }
  }
 else {
    PGraphics.showWarning("Shape must be OpenGL to be added to the group.");
  }
}
