public static TileMap deserialize(Document object,int size){
  ArrayList<Integer> terrain=(ArrayList<Integer>)object.get("tiles");
  GameRegistry reg=GameServer.INSTANCE.getRegistry();
  Tile[][] tiles=new Tile[size][size];
  for (int x=0; x < size; x++) {
    for (int y=0; y < size; y++) {
      tiles[x][y]=reg.makeTile(terrain.get(x * size + y));
    }
  }
  return new TileMap(tiles,size);
}
