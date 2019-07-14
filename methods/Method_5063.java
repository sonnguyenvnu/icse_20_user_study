/** 
 * Returns  {@link RequirementFlags} that are not met, or 0.
 * @param context Any context.
 * @return RequirementFlags that are not met, or 0.
 */
@RequirementFlags public int getNotMetRequirements(Context context){
  return (!checkNetworkRequirements(context) ? getRequiredNetworkType() : 0) | (!checkChargingRequirement(context) ? DEVICE_CHARGING : 0) | (!checkIdleRequirement(context) ? DEVICE_IDLE : 0);
}
