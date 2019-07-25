public StaticStickerNormalFilter hit(Vector3 target){
  parentToLocalCoordinates(target);
  return target.x >= 0 && target.x < swidth && target.y >= 0 && target.y < sheight ? this : null;
}
