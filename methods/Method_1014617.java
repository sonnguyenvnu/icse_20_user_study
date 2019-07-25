public static World deserialize(Document dbObject){
  World world=new World(dbObject.getInteger("size"));
  world.x=dbObject.getInteger("x");
  world.y=dbObject.getInteger("y");
  world.dimension=dbObject.getString("dimension");
  world.updatable=dbObject.getInteger("updatable");
  world.tileMap=TileMap.deserialize((Document)dbObject.get("terrain"),world.getWorldSize());
  ArrayList objects=(ArrayList)dbObject.get("objects");
  for (  Object obj : objects) {
    GameObject object=GameServer.INSTANCE.getRegistry().deserializeGameObject((Document)obj);
    object.setWorld(world);
    world.addObject(object);
    object.initialize();
  }
  return world;
}
