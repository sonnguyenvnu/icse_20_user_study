/** 
 * Changes the item's animation frame depending on what the user is doing a) idle = use default model = mbe12_item_nbt_animate_0 b) when the user has been holding down the button, advance through the animation frames _1 to _5.  The texture changes, as well as the ItemCameraTransform (the "scale", in the json files) from the json: "overrides": [ { "predicate": { "chargefraction": 0.000 }, "model": "minecraftbyexample:item/mbe12_item_nbt_animate_0" }, { "predicate": { "chargefraction": 0.001 }, "model": "minecraftbyexample:item/mbe12_item_nbt_animate_1" }, { "predicate": { "chargefraction": 0.200 }, "model": "minecraftbyexample:item/mbe12_item_nbt_animate_2" }, { "predicate": { "chargefraction": 0.400 }, "model": "minecraftbyexample:item/mbe12_item_nbt_animate_3" }, { "predicate": { "chargefraction": 0.600 }, "model": "minecraftbyexample:item/mbe12_item_nbt_animate_4" }, { "predicate": { "chargefraction": 0.800 }, "model": "minecraftbyexample:item/mbe12_item_nbt_animate_5" } ]
 * @param stack
 * @param worldIn null when rendering in a GUI such as inventory or the hotbar, or in an ItemFrame (eg picture frame)
 * @param entityIn null when in the world as an EntityItem (GROUND)
 * @return the appropriate animation frame index expected by the "overrides" section in the item json file
 */
@Override public float apply(ItemStack stack,@Nullable World worldIn,@Nullable EntityLivingBase entityIn){
  final float IDLE_FRAME_INDEX=0.0F;
  final float FULLY_CHARGED_INDEX=1.0F;
  if (worldIn == null && entityIn != null) {
    worldIn=entityIn.world;
  }
  if (entityIn == null || worldIn == null)   return IDLE_FRAME_INDEX;
  if (!entityIn.isHandActive()) {
    animationHasStarted=false;
    return IDLE_FRAME_INDEX;
  }
  long worldTicks=worldIn.getTotalWorldTime();
  if (!animationHasStarted) {
    startingTick=worldTicks;
    animationHasStarted=true;
  }
  final long ticksInUse=worldTicks - startingTick;
  if (ticksInUse <= ItemNBTAnimate.CHARGE_UP_INITIAL_PAUSE_TICKS) {
    return IDLE_FRAME_INDEX;
  }
  final long chargeTicksSoFar=ticksInUse - ItemNBTAnimate.CHARGE_UP_INITIAL_PAUSE_TICKS;
  final double fractionCharged=chargeTicksSoFar / (double)ItemNBTAnimate.CHARGE_UP_DURATION_TICKS;
  if (fractionCharged < 0.0)   return IDLE_FRAME_INDEX;
  if (fractionCharged > FULLY_CHARGED_INDEX)   return FULLY_CHARGED_INDEX;
  return (float)fractionCharged * FULLY_CHARGED_INDEX;
}
