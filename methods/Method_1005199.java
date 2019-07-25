public StringBuffer end(){
  StringBuffer sb=new StringBuffer();
  if (StringUtils.isBlank(url)) {
    url="url";
  }
  if (StringUtils.isBlank(windowWidth)) {
    windowWidth="200px";
  }
  if (StringUtils.isBlank(windowHeight)) {
    windowHeight="30px";
  }
  sb.append("<link rel=\"stylesheet\" href=\"plug-in/ztree/css/metroStyle.css\" type=\"text/css\"></link>");
  sb.append("<script type=\"text/javascript\" src=\"plug-in/ztree/js/jquery.ztree.core-3.5.min.js\"></script>");
  sb.append("<script type=\"text/javascript\" src=\"plug-in/ztree/js/jquery.ztree.excheck-3.5.min.js\"></script>");
  sb.append("<script type=\"text/javascript\">" + "function beforeClick(treeId, treeNode) {" + "   var zTree = $.fn.zTree.getZTreeObj('treeDemo');" + "   zTree.checkNode(treeNode, !treeNode.checked, null, true);" + "   return false;" + "}" + "function onCheck(e, treeId, treeNode) {" + "		var zTree = $.fn.zTree.getZTreeObj('treeDemo')," + "		nodes = zTree.getCheckedNodes(true)," + "		v = '';" + "		for (var i=0, l=nodes.length; i<l; i++) {" + "			v += nodes[i].name + ',';" + "		}" + "		if (v.length > 0 ) v = v.substring(0, v.length-1);" + "		var cityObj = $('#" + id + "');" + "		cityObj.attr('value', v);" + "} " + " function showMenu() {" + "		var cityObj = $('#" + id + "');" + "		var cityOffset = $('#" + id + "').offset();" + " $('#menuContent').css({left:cityOffset.left + 'px', top:cityOffset.top + cityObj.outerHeight() + 'px'}).slideDown('fast');" + "    $('body').bind('mousedown', onBodyDown);" + "} " + " function hideMenu() {" + "		$('#menuContent').fadeOut('fast');" + "		$('body').unbind('blur', onBodyDown);" + "} " + " function onBodyDown(event) {" + "		if (!(event.target.id == 'menuBtn' || event.target.id == 'citySel' || event.target.id == 'menuContent' || $(event.target).parents('#menuContent').length>0)) {" + "		hideMenu();" + "	 }" + "} " + " var setting = {" + "		async: {" + "		enable: true," + "}, " + "		check: {" + "			enable: true,");
  sb.append("chkboxType: {'Y':'");
  if (selectCascadeParent) {
    sb.append("p");
  }
  if (selectCascadeChildren) {
    sb.append("s");
  }
  sb.append("','N':'");
  if (cancelCascadeParent) {
    sb.append("p");
  }
  if (cancelCascadeChildren) {
    sb.append("s");
  }
  sb.append("'}");
  sb.append("}," + "		view: {" + "			dblClickExpand: false" + "		}," + "		data: {" + "			simpleData: {" + "				enable: true" + "			}" + "		}," + "		callback: {" + "			beforeClick: beforeClick," + "			onCheck: onCheck" + "			}" + "		};" + " $(function(){" + "		$.post(" + "			'" + url + "'," + "		function(data){" + "			var d = $.parseJSON(data);" + "			if (d.success) {" + "				var datas = eval(d.obj);" + "				$.fn.zTree.init($('#treeDemo'), setting, datas);" + "				}" + "			}" + "		);" + "});" + "</script>");
  sb.append("		   <input id=\"" + id + "\" name=\"" + id + "\"  type=\"text\" readonly value=\"\" style=\"width:" + windowWidth + ";height:" + windowHeight + "\" class=\"form-control\" onclick=\"showMenu();\" />");
  sb.append("<div id=\"menuContent\" class=\"menuContent\" style=\"display:none; position: absolute;\" >");
  sb.append("		<ul id=\"treeDemo\" class=\"ztree\" style=\"margin-top:0; width:100%;background-color:#f9f9f9\"></ul>");
  sb.append("</div>");
  return sb;
}
