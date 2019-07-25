@SuppressWarnings("unchecked") public StringBuffer end(){
  StringBuffer sb=this.getTagCache();
  if (sb != null) {
    return sb;
  }
  sb=new StringBuffer();
  if ("pic".equals(extend)) {
    extend="*.jpg;*.jpeg;*.png;*.gif;*.bmp;*.ico;*.tif";
  }
  if (extend.equals("office")) {
    extend="*.doc;*.docx;*.txt;*.ppt;*.xls;*.xlsx;*.html;*.htm";
  }
  if (outhtml) {
    sb.append("<link rel=\"stylesheet\" href=\"plug-in/uploadify/css/uploadify.css\" type=\"text/css\"></link>");
    sb.append("<script type=\"text/javascript\" src=\"plug-in/uploadify/jquery.uploadify-3.1.js\"></script>");
    sb.append("<script type=\"text/javascript\" src=\"plug-in/tools/Map.js\"></script>");
  }
  sb.append("<script type=\"text/javascript\">" + "var flag = false;" + "var fileitem=\"\";" + "var fileKey=\"\";" + "var serverMsg=\"\";" + "var m = new Map();" + "$(function(){" + "$(\'#" + id + "\').uploadify({" + "buttonText:\'" + MutiLangUtil.getLang(buttonText) + "\'," + "auto:" + auto + "," + "progressData:\'speed\'," + "multi:" + multi + "," + "height:" + height + "," + "width:" + width + "," + "overrideEvents:[\'onDialogClose\']," + "fileTypeDesc:\'????:\'," + "queueID:\'" + queueID + "\'," + "fileTypeExts:\'" + extend + "\'," + "fileSizeLimit:\'" + fileSizeLimit + "\'," + "swf:\'plug-in/uploadify/uploadify.swf\',	" + "uploader:\'" + getUploader() + "onUploadStart : function(file) { ");
  if (onUploadStart == null || "".equals(onUploadStart)) {
    if (formData != null) {
      String[] paramnames=formData.split(",");
      for (int i=0; i < paramnames.length; i++) {
        String paramname=paramnames[i];
        String fieldName=paramname;
        if (paramname.indexOf("_") > -1) {
          fieldName=paramname.substring(0,paramname.indexOf("_"));
        }
        sb.append("var " + fieldName + "=$(\'#" + paramname + "\').val();");
      }
      sb.append("$(\'#" + id + "\').uploadify(\"settings\", \"formData\", {");
      for (int i=0; i < paramnames.length; i++) {
        String paramname=paramnames[i];
        if (paramname.indexOf("_") > -1) {
          paramname=paramname.substring(0,paramname.indexOf("_"));
        }
        if (i == paramnames.length - 1) {
          sb.append("'" + paramname + "':" + paramname + "");
        }
 else {
          sb.append("'" + paramname + "':" + paramname + ",");
        }
      }
      sb.append("});");
    }
 else     if (formId != null) {
      sb.append(" var o = {};");
      sb.append("    var _array = $('#" + formId + "').serializeArray();");
      sb.append("    $.each(_array, function() {");
      sb.append("        if (o[this.name]) {");
      sb.append("            if (!o[this.name].push) {");
      sb.append("                o[this.name] = [ o[this.name] ];");
      sb.append("            }");
      sb.append("            o[this.name].push(this.value || '');");
      sb.append("        } else {");
      sb.append("            o[this.name] = this.value || '';");
      sb.append("        }");
      sb.append("    });");
      sb.append("$(\'#" + id + "\').uploadify(\"settings\", \"formData\", o);");
    }
  }
 else {
    sb.append(this.onUploadStart + "(file);");
  }
  sb.append("} ," + "onQueueComplete : function(queueData) { ");
  if (dialog) {
    sb.append("var win = frameElement.api.opener;" + "win.reloadTable();" + "win.tip(serverMsg);" + "if(subDlgIndex && $('#infoTable-loading')){" + "$('#infoTable-loading').hide();" + "if(!subDlgIndex.closed)subDlgIndex.close();" + "}" + "frameElement.api.close();");
  }
 else {
    if (callback != null)     sb.append("" + callback + "();");
  }
  if (view) {
    sb.append("$(\"#viewmsg\").html(m.toString());");
    sb.append("$(\"#fileKey\").val(fileKey);");
  }
  sb.append("},");
  sb.append("onUploadSuccess : function(file, data, response) {");
  sb.append("var d=$.parseJSON(data);");
  if (view) {
    sb.append("var fileitem=\"<span id=\'\"+d.attributes.id+\"\'><a href=\'#\' onclick=openwindow(\'????\',\'\"+d.attributes.viewhref+\"\',\'70%\',\'80%\') title=\'??\'>\"+d.attributes.name+\"</a><img border=\'0\' onclick=confuploadify(\'\"+d.attributes.delurl+\"\',\'\"+d.attributes.id+\"\') title=\'??\' src=\'plug-in/uploadify/img/uploadify-cancel.png\' widht=\'15\' height=\'15\'>&nbsp;&nbsp;</span>\";");
    sb.append(" m=new Map(); ");
    sb.append("m.put(d.attributes.id,fileitem);");
    sb.append("fileKey=d.attributes.fileKey;");
  }
  if (onUploadSuccess != null) {
    sb.append(onUploadSuccess + "(d,file,response);");
  }
  sb.append("if(d.success){");
  sb.append("var win = frameElement.api.opener;");
  sb.append("serverMsg = d.msg;");
  sb.append("}");
  sb.append("},");
  sb.append("onFallback : function(){tip(\"????FLASH?????????????FLASH?????\")},");
  sb.append("onSelectError : function(file, errorCode, errorMsg){");
  sb.append("switch(errorCode) {");
  sb.append("case -100:");
  sb.append("tip(\"????????????????\"+$(\'#" + id + "\').uploadify(\'settings\',\'queueSizeLimit\')+\"????\");");
  sb.append("break;");
  sb.append("case -110:" + "tip(\"?? [\"+file.name+\"] ?????????\"+$(\'#" + id + "\').uploadify(\'settings\',\'fileSizeLimit\')+\"???\");" + "break;" + "case -120:" + "tip(\"?? [\"+file.name+\"] ?????\");" + "break;" + "case -130:" + "tip(\"?? [\"+file.name+\"] ??????\");" + "break;" + "}");
  sb.append("}," + "onUploadProgress : function(file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal) { " + "}" + "});" + "});");
  if (outhtml) {
    List<String> idList=(List<String>)pageContext.getRequest().getAttribute("nameList");
    sb.append("function upload() {");
    for (int i=0; i < idList.size(); i++) {
      String tempId=idList.get(i);
      sb.append("	$(\'#" + tempId + "\').uploadify('upload', '*');");
    }
    sb.append("return flag;");
    sb.append("}");
    sb.append("function cancel() {");
    for (int i=0; i < idList.size(); i++) {
      String tempId=idList.get(i);
      sb.append("$(\'#" + tempId + "\').uploadify('cancel', '*');");
    }
    sb.append("}");
  }
  sb.append("</script>");
  sb.append("<span id=\"" + id + "span\"><input type=\"file\" name=\"" + name + "\" id=\"" + id + "\" /></span>");
  if (view) {
    sb.append("<span id=\"viewmsg\"></span>");
    sb.append("<input type=\"hidden\" name=\"fileKey\" id=\"fileKey\" />");
  }
  this.putTagCache(sb);
  return sb;
}
