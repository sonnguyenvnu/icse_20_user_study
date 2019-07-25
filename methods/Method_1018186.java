@Override public Alert.ID resolve(Serializable id){
  if (id instanceof JpaAlert.AlertId) {
    return (JpaAlert.AlertId)id;
  }
 else {
    return new JpaAlert.AlertId(id);
  }
}
