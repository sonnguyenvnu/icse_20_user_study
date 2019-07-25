public static Dimension2D max(Dimension2D dim1,Dimension2D dim2){
  return atLeast(dim1,dim2.getWidth(),dim2.getHeight());
}
