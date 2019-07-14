@Override public void init(){
  if (logDbProperties.isEnabled()) {
    String sql="CREATE TABLE IF NOT EXISTS `t_logger`  (\n" + "  `id` bigint(20) NOT NULL AUTO_INCREMENT,\n" + "  `group_id` varchar(64)  NOT NULL ,\n" + "  `unit_id` varchar(32)  NOT NULL ,\n" + "  `tag` varchar(50)  NOT NULL ,\n" + "  `content` varchar(1024)  NOT NULL ,\n" + "  `create_time` varchar(30) NOT NULL,\n" + "  `app_name` varchar(128) NOT NULL,\n" + "  PRIMARY KEY (`id`) USING BTREE\n" + ") ";
    dbHelper.update(sql);
  }
}
