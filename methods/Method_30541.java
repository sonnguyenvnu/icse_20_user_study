public static ArrayList<me.zhanghai.android.douya.network.api.info.frodo.TextEntity> toFrodo(ArrayList<TextEntity> entities){
  ArrayList<me.zhanghai.android.douya.network.api.info.frodo.TextEntity> frodoEntities=new ArrayList<>();
  for (  TextEntity entity : entities) {
    frodoEntities.add(entity.toFrodo());
  }
  return frodoEntities;
}
