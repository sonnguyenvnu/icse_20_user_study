public void popProjection(){
  flush();
  if (projectionStackDepth == 0) {
    throw new RuntimeException(ERROR_PUSHMATRIX_UNDERFLOW);
  }
  projectionStackDepth--;
  projection.set(projectionStack[projectionStackDepth]);
  updateProjmodelview();
}
