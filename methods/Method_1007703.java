@Override public Operation resume(RunContext run) throws WorldEditException {
  for (  BlockVector2 column : flatRegion.asFlatRegion()) {
    if (!mask.test(column)) {
      continue;
    }
    if (function.isGround(column.toBlockVector3(maxY + 1))) {
      continue;
    }
    boolean found=false;
    int groundY=0;
    for (int y=maxY; y >= minY; --y) {
      BlockVector3 test=column.toBlockVector3(y);
      if (!found) {
        if (function.isGround(test)) {
          found=true;
          groundY=y;
        }
      }
      if (found) {
        if (!function.apply(test,groundY - y)) {
          break;
        }
      }
    }
  }
  return null;
}
