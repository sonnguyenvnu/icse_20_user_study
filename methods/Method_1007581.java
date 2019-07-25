public static org.bukkit.entity.EntityType adapt(EntityType entityType){
  if (!entityType.getId().startsWith("minecraft:")) {
    throw new IllegalArgumentException("Bukkit only supports vanilla entities");
  }
  return org.bukkit.entity.EntityType.fromName(entityType.getId().substring(10));
}
