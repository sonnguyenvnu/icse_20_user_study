/** 
 * @author gabizou
 * @reason Use forge's fluid stack instaed of sponge's fluid stack.
 */
@SuppressWarnings("ConstantConditions") @Override @Overwrite public FluidStack build(){
  checkNotNull(this.fluidType,"Fluidtype cannot be null!");
  checkState(this.volume >= 0,"Volume must be at least zero!");
  @Nullable final NBTTagCompound compound=this.extra == null ? null : NbtTranslator.getInstance().translateData(this.extra);
  final net.minecraftforge.fluids.FluidStack fluidStack=new net.minecraftforge.fluids.FluidStack((Fluid)this.fluidType,this.volume,compound);
  return (FluidStack)fluidStack;
}
