package org.jeecgframework.tag.core.easyui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;

import org.jeecgframework.core.online.util.FreemarkerHelper;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.SysThemesUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.core.JeecgTag;

/**
 * 用plupload实现uploadify的上传效果<br>
 * 若�?无�?替�?�uploadify标签 则需修改如下：<br>
 * 1�?修改easyui.tld upload标签的tag-class为此类<br>
 * 2�?修改base标签内uploadify的引入为注释部分，并将现有的引入注释掉<br>
 * @author taoYan
 * @since 2018年8月16日
 */
public class UploadPlTag extends JeecgTag {
	private static final long serialVersionUID = -9022815118906773203L;
	/**通用文件�?�缀*/
	private static final String ALL_COMMON_FILE = "doc,docx,txt,ppt,xls,xlsx,html,htm";
	/**图片文件�?�缀*/
	private static final String ALL_IMG_FILE = "jpg,jpeg,png,gif,bmp,ico,tif";
	protected String id;// ID
	protected String uploader;//url
	protected String name;//控件�??称
	protected String formData;//�?�数�??称
	protected String extend="";//上传文件的扩展�??	
	protected String buttonText="�?览";//按钮文本
	protected boolean multi=true;//是�?�多文件
	protected String queueID="filediv";//文件容器ID
	protected boolean dialog=true;//是�?�是弹出窗�?�模�?
	protected String callback;
	protected boolean auto=false;//是�?�自动上传
	protected String onUploadSuccess;//上传�?功处�?�函数
	protected boolean view=false;//生�?查看删除链接
	protected String formId;//�?�数�??称
	private boolean outhtml = true;
	private String onUploadStart;//上传开始处�?�函数
	private String height="18";//自定义上传按钮高度
	private String width="80";//自定义上传按钮宽度
	private String fileSizeLimit = "15MB";//上传文件大�?设置
	private String onFileAdded;//文件添加时触�?�方法
	private String onFilesRemoved;//文件移除时触�?�方法
	
	@SuppressWarnings("unchecked")
	public int doStartTag() throws JspTagException {
		List<String> idList  = (List<String>) pageContext.getRequest().getAttribute("nameList");
		if(idList == null || idList.isEmpty()){
			idList = new ArrayList<String>();
		}
		idList.add(id);
		pageContext.getRequest().setAttribute("nameList", idList);
		return EVAL_PAGE;
	}
	
	public int doEndTag() throws JspTagException {
		JspWriter out = null;
		try {
			out = this.pageContext.getOut();
			out.print(end().toString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				out.clear();
				out.close();
			} catch (Exception e2) {
			}
		}
		return EVAL_PAGE;
	}
	
	public StringBuffer end() {
		StringBuffer sb = this.getTagCache();
		if(sb != null){
			return sb;
		}
		sb = new StringBuffer();
		FreemarkerHelper free = new FreemarkerHelper();
		Map<String, Object> mainConfig = new HashMap<String, Object>();
		mainConfig.put("obj",this);
		mainConfig.put("style","uploadify");
		List<String> idList  = (List<String>) pageContext.getRequest().getAttribute("nameList");
		mainConfig.put("idList",idList);
		String superQuery = free.parseTemplate("/org/jeecgframework/tag/ftl/plupload.ftl", mainConfig);
		String format = "\r\n"; //调试  格�?化
		sb.append(superQuery).append(format);
		return sb;
	}
	
	public String getAllowedFilesExt(){
		if(oConvertUtils.isEmpty(extend)){
			return ALL_COMMON_FILE+","+ALL_IMG_FILE;
		}else{
			if("pic".equals(extend)){
				return ALL_IMG_FILE;
			}else if(extend.equals("office")){
				return ALL_COMMON_FILE;
			}else{
				if(extend.indexOf("*.")>=0){
					extend = extend.replace("*.", "");
				}
				if(extend.indexOf(";")>=0){
					extend = extend.replace(";", ",");
				}
				return extend;
			}
		}
	}
	
	/**
	 * 获�?�上传路径,修改jsessionid传�?到�?��?�的bug jueyue --- 20130916
	 * @return
	 */
	public String getUploader() {
		return uploader+"&sessionId="+pageContext.getSession().getId()+"',";
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the formData
	 */
	public String getFormData() {
		return formData;
	}
	/**
	 * @param formData the formData to set
	 */
	public void setFormData(String formData) {
		this.formData = formData;
	}
	/**
	 * @return the extend
	 */
	public String getExtend() {
		return extend;
	}
	/**
	 * @param extend the extend to set
	 */
	public void setExtend(String extend) {
		this.extend = extend;
	}
	/**
	 * @return the buttonText
	 */
	public String getButtonText() {
		return buttonText;
	}
	/**
	 * @param buttonText the buttonText to set
	 */
	public void setButtonText(String buttonText) {
		this.buttonText = buttonText;
	}
	/**
	 * @return the multi
	 */
	public boolean isMulti() {
		return multi;
	}
	/**
	 * @param multi the multi to set
	 */
	public void setMulti(boolean multi) {
		this.multi = multi;
	}
	/**
	 * @return the queueID
	 */
	public String getQueueID() {
		return queueID;
	}
	/**
	 * @param queueID the queueID to set
	 */
	public void setQueueID(String queueID) {
		this.queueID = queueID;
	}
	/**
	 * @return the dialog
	 */
	public boolean isDialog() {
		return dialog;
	}
	/**
	 * @param dialog the dialog to set
	 */
	public void setDialog(boolean dialog) {
		this.dialog = dialog;
	}
	/**
	 * @return the callback
	 */
	public String getCallback() {
		return callback;
	}
	/**
	 * @param callback the callback to set
	 */
	public void setCallback(String callback) {
		this.callback = callback;
	}
	/**
	 * @return the auto
	 */
	public boolean isAuto() {
		return auto;
	}
	/**
	 * @param auto the auto to set
	 */
	public void setAuto(boolean auto) {
		this.auto = auto;
	}
	/**
	 * @return the onUploadSuccess
	 */
	public String getOnUploadSuccess() {
		return onUploadSuccess;
	}
	/**
	 * @param onUploadSuccess the onUploadSuccess to set
	 */
	public void setOnUploadSuccess(String onUploadSuccess) {
		this.onUploadSuccess = onUploadSuccess;
	}
	/**
	 * @return the view
	 */
	public boolean isView() {
		return view;
	}
	/**
	 * @param view the view to set
	 */
	public void setView(boolean view) {
		this.view = view;
	}
	/**
	 * @return the formId
	 */
	public String getFormId() {
		return formId;
	}
	/**
	 * @param formId the formId to set
	 */
	public void setFormId(String formId) {
		this.formId = formId;
	}
	/**
	 * @return the outhtml
	 */
	public boolean isOuthtml() {
		return outhtml;
	}
	/**
	 * @param outhtml the outhtml to set
	 */
	public void setOuthtml(boolean outhtml) {
		this.outhtml = outhtml;
	}
	/**
	 * @return the onUploadStart
	 */
	public String getOnUploadStart() {
		return onUploadStart;
	}
	/**
	 * @param onUploadStart the onUploadStart to set
	 */
	public void setOnUploadStart(String onUploadStart) {
		this.onUploadStart = onUploadStart;
	}
	/**
	 * @return the height
	 */
	public String getHeight() {
		return height;
	}
	/**
	 * @param height the height to set
	 */
	public void setHeight(String height) {
		this.height = height;
	}
	/**
	 * @return the width
	 */
	public String getWidth() {
		return width;
	}
	/**
	 * @param width the width to set
	 */
	public void setWidth(String width) {
		this.width = width;
	}
	/**
	 * @return the fileSizeLimit
	 */
	public String getFileSizeLimit() {
		return fileSizeLimit;
	}
	/**
	 * @param fileSizeLimit the fileSizeLimit to set
	 */
	public void setFileSizeLimit(String fileSizeLimit) {
		this.fileSizeLimit = fileSizeLimit;
	}
	/**
	 * @param uploader the uploader to set
	 */
	public void setUploader(String uploader) {
		this.uploader = uploader;
	}

	/**
	 * @return the onFileAdded
	 */
	public String getOnFileAdded() {
		return onFileAdded;
	}

	/**
	 * @param onFileAdded the onFileAdded to set
	 */
	public void setOnFileAdded(String onFileAdded) {
		this.onFileAdded = onFileAdded;
	}

	/**
	 * @return the onFilesRemoved
	 */
	public String getOnFilesRemoved() {
		return onFilesRemoved;
	}

	/**
	 * @param onFilesRemoved the onFilesRemoved to set
	 */
	public void setOnFilesRemoved(String onFilesRemoved) {
		this.onFilesRemoved = onFilesRemoved;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UploadTag [id=").append(id).append(", uploader=")
				.append(uploader).append(", name=").append(name)
				.append(", formData=").append(formData).append(", extend=")
				.append(extend).append(", buttonText=").append(buttonText)
				.append(", multi=").append(multi).append(", queueID=")
				.append(queueID).append(", dialog=").append(dialog)
				.append(", callback=").append(callback).append(", auto=")
				.append(auto).append(", onUploadSuccess=")
				.append(onUploadSuccess).append(", view=").append(view)
				.append(", formId=").append(formId).append(", outhtml=")
				.append(outhtml).append(", fileSizeLimit=").append(fileSizeLimit)
				.append(",sysTheme=").append(SysThemesUtil.getSysTheme(ContextHolderUtils.getRequest()).getStyle())
				.append(",brower_type=").append(ContextHolderUtils.getSession().getAttribute("brower_type"))
				.append(",height=").append(height)
				.append(",width=").append(width)
				.append("]");
		return builder.toString();
	}
	
}
