@Override public DataHolder copy(){
  final FluidStack fluidStack=new FluidStack(this.fluidDelegate.get(),this.amount,this.tag);
  return (DataHolder)fluidStack;
}
