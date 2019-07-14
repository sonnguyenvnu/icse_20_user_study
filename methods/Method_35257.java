@Override protected void resetFromView(@NonNull View from){
  from.setAlpha(1);
switch (flipDirection) {
case LEFT:
case RIGHT:
    from.setRotationY(0);
  break;
case UP:
case DOWN:
from.setRotationX(0);
break;
}
}
