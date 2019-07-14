@Override public void resetMatrix(){
  if (shapeCreated && matrix != null && matrixInv != null) {
    if (family == GROUP) {
      updateTessellation();
    }
    if (tessellated) {
      applyMatrixImpl(matrixInv);
    }
    matrix.reset();
    matrixInv.reset();
  }
}
