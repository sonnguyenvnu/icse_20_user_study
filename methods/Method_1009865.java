@Override protected boolean supports(TileEntity dataHolder){
  for (  EnumFacing enumFacing : EnumFacing.values()) {
    if (dataHolder.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY,enumFacing)) {
      return true;
    }
  }
  return false;
}
