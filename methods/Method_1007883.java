default Location adapt(org.spongepowered.api.world.Location<org.spongepowered.api.world.World> loc,Vector3d rot){
  Vector3 position=Vector3.at(loc.getX(),loc.getY(),loc.getZ());
  return new Location(getWorld(loc.getExtent()),position,(float)rot.getY(),(float)rot.getX());
}
