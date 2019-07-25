@SuppressWarnings("deprecation") @Override public void populate(final int chunkX,final int chunkZ){
  this.chunkGeneratorTiming.startTimingIfSync();
  if (this.moddedGeneratorFallback != null) {
    this.moddedGeneratorFallback.populate(chunkX,chunkZ);
    return;
  }
  this.rand.setSeed(this.world.getSeed());
  final long i1=this.rand.nextLong() / 2L * 2L + 1L;
  final long j1=this.rand.nextLong() / 2L * 2L + 1L;
  this.rand.setSeed(chunkX * i1 + chunkZ * j1 ^ this.world.getSeed());
  BlockFalling.fallInstantly=true;
  this.cachedBiomes.reuse(new Vector3i(chunkX * 16,0,chunkZ * 16));
  this.biomeGenerator.generateBiomes(this.cachedBiomes);
  final ImmutableBiomeVolume biomeBuffer=this.cachedBiomes.getImmutableBiomeCopy();
  final BlockPos blockpos=new BlockPos(chunkX * 16,0,chunkZ * 16);
  BiomeType biome=(BiomeType)this.world.getBiome(blockpos.add(16,0,16));
  if (biome == null) {
    if (!((WorldBridge)this.world).isFake()) {
      final DimensionType type=(DimensionType)(Object)((org.spongepowered.api.world.World)this.world).getDimension().getType();
      try {
        this.moddedGeneratorFallback=type.createDimension().createChunkGenerator();
      }
 catch (      Exception e) {
        throw new CompatibilityException("Unable to create a fallback compatibility adaptor for IChunkGenerator for Dimension: " + type + " in world: " + this.world);
      }
      if (this.moddedGeneratorFallback == null) {
        biome=BiomeTypes.PLAINS;
      }
 else {
        this.moddedGeneratorFallback.populate(chunkX,chunkZ);
        return;
      }
    }
  }
  final Chunk chunk=(Chunk)this.world.getChunk(chunkX,chunkZ);
  final BiomeGenerationSettings settings=getBiomeSettings(biome);
  final List<Populator> populators=new ArrayList<>(this.pop);
  Populator snowPopulator=null;
  final Iterator<Populator> itr=populators.iterator();
  while (itr.hasNext()) {
    final Populator populator=itr.next();
    if (populator instanceof SnowPopulator) {
      itr.remove();
      snowPopulator=populator;
      break;
    }
  }
  populators.addAll(settings.getPopulators());
  if (snowPopulator != null) {
    populators.add(snowPopulator);
  }
  Sponge.getGame().getEventManager().post(SpongeEventFactory.createPopulateChunkEventPre(Sponge.getCauseStackManager().getCurrentCause(),populators,chunk));
  MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(this,this.world,this.rand,chunkX,chunkZ,false));
  MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Pre(this.world,this.rand,blockpos));
  MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Pre(this.world,this.rand,blockpos));
  final List<String> flags=Lists.newArrayList();
  final Vector3i min=PhaseTracker.getInstance().getCurrentState().getChunkPopulatorOffset(chunk,chunkX,chunkZ);
  final org.spongepowered.api.world.World spongeWorld=(org.spongepowered.api.world.World)this.world;
  final Extent volume=new SoftBufferExtentViewDownsize(chunk.getWorld(),min,min.add(15,255,15),min.sub(8,0,8),min.add(23,255,23));
  for (  final Populator populator : populators) {
    if (!(populator instanceof PlainsGrassPopulator)) {
      if (!this.checkForgeEvent(populator,this,chunkX,chunkZ,flags,chunk)) {
        continue;
      }
    }
 else {
      final PlainsGrassPopulator grassPop=(PlainsGrassPopulator)populator;
      if (!this.checkForgeEvent(grassPop.getFlowers(),this,chunkX,chunkZ,flags,chunk)) {
        grassPop.setPopulateFlowers(false);
      }
      if (!this.checkForgeEvent(grassPop.getGrass(),this,chunkX,chunkZ,flags,chunk)) {
        grassPop.setPopulateGrass(false);
      }
      if (!this.checkForgeEvent(grassPop.getPlant(),this,chunkX,chunkZ,flags,chunk)) {
        grassPop.setPopulateGrass(false);
      }
      if (!grassPop.isPopulateFlowers() && !grassPop.isPopulateGrass()) {
        continue;
      }
    }
    final PopulatorType type=populator.getType();
    if (Sponge.getGame().getEventManager().post(SpongeEventFactory.createPopulateChunkEventPopulate(Sponge.getCauseStackManager().getCurrentCause(),populator,chunk))) {
      continue;
    }
    try (final PopulatorPhaseContext context=GenerationPhase.State.POPULATOR_RUNNING.createPhaseContext().world(this.world).populator(type)){
      context.buildAndSwitch();
      Timing timing=null;
      if (Timings.isTimingsEnabled()) {
        timing=this.populatorTimings.get(populator.getType().getId());
        if (timing == null) {
          timing=SpongeTimingsFactory.ofSafe(populator.getType().getId());
          this.populatorTimings.put(populator.getType().getId(),timing);
        }
        timing.startTimingIfSync();
      }
      if (populator instanceof FlaggedPopulatorBridge) {
        ((FlaggedPopulatorBridge)populator).populate(spongeWorld,volume,this.rand,biomeBuffer,flags);
      }
 else {
        populator.populate(spongeWorld,volume,this.rand,biomeBuffer);
      }
      if (timing != null) {
        timing.stopTimingIfSync();
      }
    }
   }
  MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Post(this.world,this.rand,blockpos));
  MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(this.world,this.rand,blockpos));
  MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(this,this.world,this.rand,chunkX,chunkZ,false));
  if (this.baseGenerator instanceof SpongeGenerationPopulator) {
    Timing timing=null;
    final IChunkGenerator chunkGenerator=((SpongeGenerationPopulator)this.baseGenerator).getHandle(this.world);
    if (Timings.isTimingsEnabled()) {
      final TimingBridge spongePopulator=(TimingBridge)this.baseGenerator;
      timing=spongePopulator.bridge$getTimingsHandler();
      timing.startTimingIfSync();
    }
    chunkGenerator.populate(chunkX,chunkZ);
    if (Timings.isTimingsEnabled()) {
      timing.stopTimingIfSync();
    }
  }
  final org.spongepowered.api.event.world.chunk.PopulateChunkEvent.Post event=SpongeEventFactory.createPopulateChunkEventPost(Sponge.getCauseStackManager().getCurrentCause(),ImmutableList.copyOf(populators),chunk);
  SpongeImpl.postEvent(event);
  BlockFalling.fallInstantly=false;
  this.chunkGeneratorTiming.stopTimingIfSync();
  ((ServerWorldBridge)spongeWorld).bridge$getTimingsHandler().chunkPopulate.stopTimingIfSync();
}
