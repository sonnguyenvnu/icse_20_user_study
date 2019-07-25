/** 
 * create a new Thing
 * @param thingBean
 * @return Response holding the newly created Thing or error information
 */
@POST @RolesAllowed({Role.ADMIN}) @Consumes(MediaType.APPLICATION_JSON) @ApiOperation(value="Creates a new thing and adds it to the registry.") @ApiResponses(value={@ApiResponse(code=201,message="Created",response=String.class),@ApiResponse(code=400,message="A uid must be provided, if no binding can create a thing of this type."),@ApiResponse(code=409,message="A thing with the same uid already exists.")}) public Response create(@HeaderParam(HttpHeaders.ACCEPT_LANGUAGE) @ApiParam(value="language") String language,@ApiParam(value="thing data",required=true) ThingDTO thingBean){
  final Locale locale=localeService.getLocale(language);
  ThingUID thingUID=thingBean.UID == null ? null : new ThingUID(thingBean.UID);
  ThingTypeUID thingTypeUID=new ThingTypeUID(thingBean.thingTypeUID);
  if (thingUID != null) {
    Thing thing=thingRegistry.get(thingUID);
    if (thing != null) {
      return getThingResponse(Status.CONFLICT,thing,locale,"Thing " + thingUID.toString() + " already exists!");
    }
  }
  ThingUID bridgeUID=null;
  if (thingBean.bridgeUID != null) {
    bridgeUID=new ThingUID(thingBean.bridgeUID);
  }
  Configuration configuration=new Configuration(normalizeConfiguration(thingBean.configuration,thingTypeUID,thingUID));
  normalizeChannels(thingBean,thingUID);
  Thing thing=thingRegistry.createThingOfType(thingTypeUID,thingUID,bridgeUID,thingBean.label,configuration);
  if (thing != null) {
    if (thingBean.properties != null) {
      for (      Entry<String,String> entry : thingBean.properties.entrySet()) {
        thing.setProperty(entry.getKey(),entry.getValue());
      }
    }
    if (thingBean.channels != null) {
      List<Channel> channels=new ArrayList<>();
      for (      ChannelDTO channelDTO : thingBean.channels) {
        channels.add(ChannelDTOMapper.map(channelDTO));
      }
      ThingHelper.addChannelsToThing(thing,channels);
    }
    if (thingBean.location != null) {
      thing.setLocation(thingBean.location);
    }
  }
 else   if (thingUID != null) {
    thing=ThingDTOMapper.map(thingBean,thingTypeRegistry.getThingType(thingTypeUID) instanceof BridgeType);
  }
 else {
    return getThingResponse(Status.BAD_REQUEST,thing,locale,"A UID must be provided, since no binding can create the thing!");
  }
  thingRegistry.add(thing);
  return getThingResponse(Status.CREATED,thing,locale,null);
}
