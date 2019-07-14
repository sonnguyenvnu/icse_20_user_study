public static boolean impliedBy(Direction sub,Direction sup){
  return sup == sub || sup == Direction.BOTH;
}
