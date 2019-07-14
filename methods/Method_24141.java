public void pushProjection(){
  if (projectionStackDepth == MATRIX_STACK_DEPTH) {
    throw new RuntimeException(ERROR_PUSHMATRIX_OVERFLOW);
  }
  projection.get(projectionStack[projectionStackDepth]);
  projectionStackDepth++;
}
