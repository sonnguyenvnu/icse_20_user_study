public static ST_Agattr_s agattrrec(__ptr__ obj){
  ENTERING("13sfx74lme08ur04vkrqta25j","agattrrec");
  try {
    return (ST_Agattr_s)aggetrec(obj,AgDataRecName,false).castTo(ST_Agattr_s.class);
  }
  finally {
    LEAVING("13sfx74lme08ur04vkrqta25j","agattrrec");
  }
}
