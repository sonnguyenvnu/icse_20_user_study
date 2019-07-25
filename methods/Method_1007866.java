public static net.minecraft.util.Direction adapt(Direction face){
switch (face) {
case NORTH:
    return net.minecraft.util.Direction.NORTH;
case SOUTH:
  return net.minecraft.util.Direction.SOUTH;
case WEST:
return net.minecraft.util.Direction.WEST;
case EAST:
return net.minecraft.util.Direction.EAST;
case DOWN:
return net.minecraft.util.Direction.DOWN;
case UP:
default :
return net.minecraft.util.Direction.UP;
}
}
