@Override protected void init(){
  for (  SupportedThingTypes supportedThingType : SupportedThingTypes.values()) {
    if (supportedThingType.handler.equals(DeviceHandler.class.getSimpleName())) {
      DeviceHandler.SUPPORTED_THING_TYPES.add(new ThingTypeUID(DigitalSTROMBindingConstants.BINDING_ID,supportedThingType.toString()));
    }
    if (supportedThingType.handler.equals(CircuitHandler.class.getSimpleName())) {
      CircuitHandler.SUPPORTED_THING_TYPES.add(new ThingTypeUID(DigitalSTROMBindingConstants.BINDING_ID,supportedThingType.toString()));
    }
  }
}
