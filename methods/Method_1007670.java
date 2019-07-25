@Override public Clipboard read() throws IOException {
  NamedTag rootTag=inputStream.readNamedTag();
  if (!rootTag.getName().equals("Schematic")) {
    throw new IOException("Tag 'Schematic' does not exist or is not first");
  }
  CompoundTag schematicTag=(CompoundTag)rootTag.getTag();
  Map<String,Tag> schematic=schematicTag.getValue();
  if (!schematic.containsKey("Blocks")) {
    throw new IOException("Schematic file is missing a 'Blocks' tag");
  }
  String materials=requireTag(schematic,"Materials",StringTag.class).getValue();
  if (!materials.equals("Alpha")) {
    throw new IOException("Schematic file is not an Alpha schematic");
  }
  BlockVector3 origin;
  Region region;
  short width=requireTag(schematic,"Width",ShortTag.class).getValue();
  short height=requireTag(schematic,"Height",ShortTag.class).getValue();
  short length=requireTag(schematic,"Length",ShortTag.class).getValue();
  try {
    int originX=requireTag(schematic,"WEOriginX",IntTag.class).getValue();
    int originY=requireTag(schematic,"WEOriginY",IntTag.class).getValue();
    int originZ=requireTag(schematic,"WEOriginZ",IntTag.class).getValue();
    BlockVector3 min=BlockVector3.at(originX,originY,originZ);
    int offsetX=requireTag(schematic,"WEOffsetX",IntTag.class).getValue();
    int offsetY=requireTag(schematic,"WEOffsetY",IntTag.class).getValue();
    int offsetZ=requireTag(schematic,"WEOffsetZ",IntTag.class).getValue();
    BlockVector3 offset=BlockVector3.at(offsetX,offsetY,offsetZ);
    origin=min.subtract(offset);
    region=new CuboidRegion(min,min.add(width,height,length).subtract(BlockVector3.ONE));
  }
 catch (  IOException ignored) {
    origin=BlockVector3.ZERO;
    region=new CuboidRegion(origin,origin.add(width,height,length).subtract(BlockVector3.ONE));
  }
  byte[] blockId=requireTag(schematic,"Blocks",ByteArrayTag.class).getValue();
  byte[] blockData=requireTag(schematic,"Data",ByteArrayTag.class).getValue();
  byte[] addId=new byte[0];
  short[] blocks=new short[blockId.length];
  if (schematic.containsKey("AddBlocks")) {
    addId=requireTag(schematic,"AddBlocks",ByteArrayTag.class).getValue();
  }
  for (int index=0; index < blockId.length; index++) {
    if ((index >> 1) >= addId.length) {
      blocks[index]=(short)(blockId[index] & 0xFF);
    }
 else {
      if ((index & 1) == 0) {
        blocks[index]=(short)(((addId[index >> 1] & 0x0F) << 8) + (blockId[index] & 0xFF));
      }
 else {
        blocks[index]=(short)(((addId[index >> 1] & 0xF0) << 4) + (blockId[index] & 0xFF));
      }
    }
  }
  List<Tag> tileEntities=requireTag(schematic,"TileEntities",ListTag.class).getValue();
  Map<BlockVector3,Map<String,Tag>> tileEntitiesMap=new HashMap<>();
  Map<BlockVector3,BlockState> blockStates=new HashMap<>();
  for (  Tag tag : tileEntities) {
    if (!(tag instanceof CompoundTag))     continue;
    CompoundTag t=(CompoundTag)tag;
    Map<String,Tag> values=new HashMap<>(t.getValue());
    String id=t.getString("id");
    values.put("id",new StringTag(convertBlockEntityId(id)));
    int x=t.getInt("x");
    int y=t.getInt("y");
    int z=t.getInt("z");
    int index=y * width * length + z * width + x;
    BlockState block=getBlockState(blocks[index],blockData[index]);
    BlockState newBlock=block;
    if (newBlock != null) {
      for (      NBTCompatibilityHandler handler : COMPATIBILITY_HANDLERS) {
        if (handler.isAffectedBlock(newBlock)) {
          newBlock=handler.updateNBT(block,values);
          if (newBlock == null || values.isEmpty()) {
            break;
          }
        }
      }
    }
    if (values.isEmpty()) {
      t=null;
    }
    if (fixer != null && t != null) {
      t=fixer.fixUp(DataFixer.FixTypes.BLOCK_ENTITY,t,-1);
    }
    BlockVector3 vec=BlockVector3.at(x,y,z);
    if (t != null) {
      tileEntitiesMap.put(vec,t.getValue());
    }
    blockStates.put(vec,newBlock);
  }
  BlockArrayClipboard clipboard=new BlockArrayClipboard(region);
  clipboard.setOrigin(origin);
  Set<Integer> unknownBlocks=new HashSet<>();
  for (int x=0; x < width; ++x) {
    for (int y=0; y < height; ++y) {
      for (int z=0; z < length; ++z) {
        int index=y * width * length + z * width + x;
        BlockVector3 pt=BlockVector3.at(x,y,z);
        BlockState state=blockStates.computeIfAbsent(pt,p -> getBlockState(blocks[index],blockData[index]));
        try {
          if (state != null) {
            if (tileEntitiesMap.containsKey(pt)) {
              clipboard.setBlock(region.getMinimumPoint().add(pt),state.toBaseBlock(new CompoundTag(tileEntitiesMap.get(pt))));
            }
 else {
              clipboard.setBlock(region.getMinimumPoint().add(pt),state);
            }
          }
 else {
            short block=blocks[index];
            byte data=blockData[index];
            int combined=block << 8 | data;
            if (unknownBlocks.add(combined)) {
              log.warn("Unknown block when pasting schematic: " + block + ":" + data + ". Please report this issue.");
            }
          }
        }
 catch (        WorldEditException ignored) {
        }
      }
    }
  }
  ListTag entityList=getTag(schematic,"Entities",ListTag.class);
  if (entityList != null) {
    List<Tag> entityTags=entityList.getValue();
    for (    Tag tag : entityTags) {
      if (tag instanceof CompoundTag) {
        CompoundTag compound=(CompoundTag)tag;
        if (fixer != null) {
          compound=fixer.fixUp(DataFixer.FixTypes.ENTITY,compound,-1);
        }
        String id=convertEntityId(compound.getString("id"));
        Location location=NBTConversions.toLocation(clipboard,compound.getListTag("Pos"),compound.getListTag("Rotation"));
        if (!id.isEmpty()) {
          EntityType entityType=EntityTypes.get(id.toLowerCase(Locale.ROOT));
          if (entityType != null) {
            for (            EntityNBTCompatibilityHandler compatibilityHandler : ENTITY_COMPATIBILITY_HANDLERS) {
              if (compatibilityHandler.isAffectedEntity(entityType,compound)) {
                compound=compatibilityHandler.updateNBT(entityType,compound);
              }
            }
            BaseEntity state=new BaseEntity(entityType,compound);
            clipboard.createEntity(location,state);
          }
 else {
            log.warn("Unknown entity when pasting schematic: " + id.toLowerCase(Locale.ROOT));
          }
        }
      }
    }
  }
  return clipboard;
}
