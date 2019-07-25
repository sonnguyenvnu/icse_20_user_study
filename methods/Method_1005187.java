public StringBuffer end(){
  StringBuffer sb=new StringBuffer();
  if (StringUtils.isBlank(divClass)) {
    divClass="form";
  }
  if (StringUtils.isBlank(labelClass)) {
    labelClass="Validform_label";
  }
  if (dictTable != null) {
    List<Map<String,Object>> list=queryDic();
    if ("radio".equals(type)) {
      for (      Map<String,Object> map : list) {
        radio(map.get("text").toString(),map.get("field").toString(),sb);
      }
    }
 else     if ("checkbox".equals(type)) {
      for (      Map<String,Object> map : list) {
        checkbox(map.get("text").toString(),map.get("field").toString(),sb);
      }
    }
 else     if ("text".equals(type)) {
      for (      Map<String,Object> map : list) {
        text(map.get("text").toString(),map.get("field").toString(),sb);
      }
    }
 else {
      sb.append("<select name=\"" + field + "\"");
      if (!StringUtils.isBlank(this.extendJson)) {
        sb.append(this.getExtendJsonCommon(extendJson));
      }
      this.readonly(sb);
      if (!StringUtils.isBlank(this.id)) {
        sb.append(" id=\"" + id + "\"");
      }
      this.datatype(sb);
      sb.append(">");
      select("common.please.select","",sb);
      for (      Map<String,Object> map : list) {
        select(map.get("text").toString(),map.get("field").toString(),sb);
      }
      sb.append("</select>");
    }
  }
 else {
    TSTypegroup typeGroup=ResourceUtil.getCacheTypeGroup(this.typeGroupCode.toLowerCase());
    List<TSType> types=ResourceUtil.getCacheTypes(this.typeGroupCode.toLowerCase());
    if (hasLabel) {
      sb.append("<div class=\"" + divClass + "\">");
      sb.append("<label class=\"" + labelClass + "\" >");
    }
    if (typeGroup != null) {
      if (hasLabel) {
        if (StringUtils.isBlank(this.title)) {
          this.title=MutiLangUtil.getLang(typeGroup.getTypegroupname());
        }
        sb.append(this.title + ":");
        sb.append("</label>");
      }
      if ("radio".equals(type)) {
        for (        TSType type : types) {
          radio(type.getTypename(),type.getTypecode(),sb);
        }
      }
 else       if ("checkbox".equals(type)) {
        for (        TSType type : types) {
          checkbox(type.getTypename(),type.getTypecode(),sb);
        }
      }
 else       if ("text".equals(type)) {
        for (        TSType type : types) {
          text(type.getTypename(),type.getTypecode(),sb);
        }
      }
 else {
        sb.append("<select name=\"" + field + "\"");
        if (!StringUtils.isBlank(this.extendJson)) {
          sb.append(this.getExtendJsonCommon(extendJson));
        }
        this.readonly(sb);
        if (!StringUtils.isBlank(this.id)) {
          sb.append(" id=\"" + id + "\"");
        }
        this.datatype(sb);
        sb.append(">");
        select("common.please.select","",sb);
        for (        TSType type : types) {
          select(type.getTypename(),type.getTypecode(),sb);
        }
        sb.append("</select>");
      }
      if (hasLabel) {
        sb.append("</div>");
      }
    }
  }
  return sb;
}
