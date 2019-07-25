public static net.minecraft.util.math.Direction adapt(Direction face){
switch (face) {
case NORTH:
    return net.minecraft.util.math.Direction.NORTH;
case SOUTH:
  return net.minecraft.util.math.Direction.SOUTH;
case WEST:
return net.minecraft.util.math.Direction.WEST;
case EAST:
return net.minecraft.util.math.Direction.EAST;
case DOWN:
return net.minecraft.util.math.Direction.DOWN;
case UP:
default :
return net.minecraft.util.math.Direction.UP;
}
}
