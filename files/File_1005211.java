package org.jeecgframework.tag.core.easyui;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import org.jeecgframework.core.online.util.FreemarkerHelper;
import org.jeecgframework.core.util.FileUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.oConvertUtils;

public class WebUploaderPlTag extends TagSupport {
	
	private static final long serialVersionUID = -2057061070457899124L;
	/**通用文件�?�缀*/
	private static final String ALL_COMMON_FILE = "doc,docx,txt,ppt,xls,xlsx,html,htm";
	/**图片文件�?�缀*/
	private static final String ALL_IMG_FILE = "jpg,jpeg,png,gif,bmp,ico,tif";
	private String name;//①文件路径 input默认name�?②action返回的文件路径的name
	private boolean auto=false;//是�?�自动上传上传按钮风格
	private String url = "systemController/filedeal.do";//文件上传处�?�url
	private int fileNumLimit =3;//fileNumLimit 最大文件数 TODO
	private int fileSingleSizeLimit=5242880;//fileSingleSizeLimit�?�个文件最大5M[1024*1024*5]
	private String fileVal="file";//fileVal设置文件上传域的name,默认file
	private String buttonStyle;//自定义CSS样�? �? 废弃】
	private int size;//文件总大�?�? 废弃】
	private boolean duplicate=false;//去�?， 根�?�文件�??字�?文件大�?和最�?�修改时间�?�生�?hash Key.�? 废弃】
	private String showImgDiv;//显示图片的div,如果�?给会默认在按钮下方添加div其id为'tempdiv_'+name�? 废弃】
	private String showAndDownUrl=ResourceUtil.getConfigByName("showAndDownUrl");//预览图片请求的url&文件下载url
	private String pathValues;//默认值
	private String type="file";
	private String buttonText = "选择文件";//控件按钮显示文本
	private String extensions;//�?许的文件�?�缀，�?带点，多个用逗�?�分割
	private String extendParams;//类似css写法 这是文件上传时候需�?传递的�?�数
	private String datatype;//�?��?该属性有值,�?�视之为�?为空
	private String nullMsg;//空的时候的�??示信�?�,默认会根�?�当�?控件的类型�??示,文件类则�??示“请选择文件�?;图片类则�??示“请选择图片�?.
	private String readOnly="false";//�?留字段
	private String bizType;//业务类型,根�?�该类型确定上传路径
	private boolean displayTxt=true;//是�?�显示上传列表[默认显示]true显示false�?�?
	private boolean outJs = false;//是�?�在外部引入了js和css
	private boolean swfTransform = false;//是�?�转�?��?swf文件，文件预览使用
	private String viewModel = "list";//TODO 未用到 支�?list(文件列表)/thumb(缩略图模�?)

	
	public int doStartTag() throws JspTagException {
		return EVAL_PAGE;
	}
	public int doEndTag() throws JspTagException {
		JspWriter out = null;
		StringBuffer sb = new StringBuffer();
		try {
			out = this.pageContext.getOut();
			end(sb);
			out.print(sb.toString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(out!=null){
				try {
					out.clearBuffer();
					sb.setLength(0);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return EVAL_PAGE;
	}
	
	public void end(StringBuffer sb) {
		FreemarkerHelper free = new FreemarkerHelper();
		Map<String, Object> mainConfig = new HashMap<String, Object>();
		mainConfig.put("obj",this);
		String superQuery = free.parseTemplate("/org/jeecgframework/tag/ftl/plupload.ftl", mainConfig);
		String format = "\r\n"; //调试  格�?化
		sb.append(superQuery).append(format);
	}
	
	/**
	 * 获�?�@param size�?�?机数
	 * @author taoYan
	 * @since 2018年8月10日
	 */
	private String random(int size){
		String sources = "QAZWSXEDCRFVTGBYHNUJMIKOLP0123456789qwertyuiopasdfghjklzxcvbnm";
		Random rand = new Random();
		StringBuffer flag = new StringBuffer();
		for (int j = 0; j < size; j++){
			flag.append(sources.charAt(rand.nextInt(sources.length())) + "");
		}
		return flag.toString();
	}
	
	public String randomSix(){
		return random(6);
	}
	
	/**
	 * 获�?�真实文件�??称
	 * @author taoYan
	 * @since 2018年8月10日
	 */
	public String getFilename(String path){
		if(oConvertUtils.isEmpty(path)){
			return null;
		}
		int index1 = path.lastIndexOf("/");
		int index2 = path.lastIndexOf("_");
		int index3 = path.lastIndexOf(".");
		if(index1==-1 ||index2==-1||index3==-1){
			return null;
		}
		String name = path.substring(index1+1,index2);
		String ext = path.substring(index3);
		return name+ext;
	}
	
	/**
	 * 判断是�?�支�?预览返回1支�? -1�?支�?
	 * @author taoYan
	 * @since 2018年8月10日
	 */
	public Integer supportView(String path){
		Integer res = -1;
		int index = path.lastIndexOf(".");
		if(oConvertUtils.isEmpty(path)||index==-1){
			return res;
		}
		String ext = path.substring(index+1);
		if(FileUtils.isPicture(ext)){
			res = 1;
		}else if(this.swfTransform){
			res = 1;
		}
		return res;
	}
	
	/**
	 * 获�?�所有的文件扩展�??
	 * @author taoYan
	 * @since 2018年8月10日
	 */
	public String getAllowedFilesExt(){
		if(oConvertUtils.isEmpty(extensions)){
			return ALL_COMMON_FILE;
		}
		return extensions;
	}
	
	public String getAllowedIMG(){
		if(oConvertUtils.isEmpty(extensions)){
			return ALL_IMG_FILE;
		}
		return extensions;
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
	 * @return the buttonStyle
	 */
	public String getButtonStyle() {
		return buttonStyle;
	}
	/**
	 * @param buttonStyle the buttonStyle to set
	 */
	public void setButtonStyle(String buttonStyle) {
		this.buttonStyle = buttonStyle;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the fileNumLimit
	 */
	public int getFileNumLimit() {
		return fileNumLimit;
	}
	/**
	 * @param fileNumLimit the fileNumLimit to set
	 */
	public void setFileNumLimit(int fileNumLimit) {
		this.fileNumLimit = fileNumLimit;
	}
	/**
	 * @return the fileSingleSizeLimit
	 */
	public int getFileSingleSizeLimit() {
		return fileSingleSizeLimit;
	}
	/**
	 * @param fileSingleSizeLimit the fileSingleSizeLimit to set
	 */
	public void setFileSingleSizeLimit(int fileSingleSizeLimit) {
		this.fileSingleSizeLimit = fileSingleSizeLimit;
	}
	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}
	/**
	 * @return the fileVal
	 */
	public String getFileVal() {
		return fileVal;
	}
	/**
	 * @param fileVal the fileVal to set
	 */
	public void setFileVal(String fileVal) {
		this.fileVal = fileVal;
	}
	/**
	 * @return the duplicate
	 */
	public boolean isDuplicate() {
		return duplicate;
	}
	/**
	 * @param duplicate the duplicate to set
	 */
	public void setDuplicate(boolean duplicate) {
		this.duplicate = duplicate;
	}
	/**
	 * @return the showImgDiv
	 */
	public String getShowImgDiv() {
		return showImgDiv;
	}
	/**
	 * @param showImgDiv the showImgDiv to set
	 */
	public void setShowImgDiv(String showImgDiv) {
		this.showImgDiv = showImgDiv;
	}
	/**
	 * @return the showAndDownUrl
	 */
	public String getShowAndDownUrl() {
		return showAndDownUrl;
	}
	/**
	 * @param showAndDownUrl the showAndDownUrl to set
	 */
	public void setShowAndDownUrl(String showAndDownUrl) {
		this.showAndDownUrl = showAndDownUrl;
	}
	/**
	 * @return the pathValues
	 */
	public String getPathValues() {
		return pathValues;
	}
	/**
	 * @param pathValues the pathValues to set
	 */
	public void setPathValues(String pathValues) {
		this.pathValues = pathValues;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
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
	 * @return the extensions
	 */
	public String getExtensions() {
		return extensions;
	}
	/**
	 * @param extensions the extensions to set
	 */
	public void setExtensions(String extensions) {
		this.extensions = extensions;
	}
	/**
	 * @return the extendParams
	 */
	public String getExtendParams() {
		return extendParams;
	}
	/**
	 * @param extendParams the extendParams to set
	 */
	public void setExtendParams(String extendParams) {
		this.extendParams = extendParams;
	}
	/**
	 * @return the datatype
	 */
	public String getDatatype() {
		return datatype;
	}
	/**
	 * @param datatype the datatype to set
	 */
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}
	/**
	 * @return the nullMsg
	 */
	public String getNullMsg() {
		return nullMsg;
	}
	/**
	 * @param nullMsg the nullMsg to set
	 */
	public void setNullMsg(String nullMsg) {
		this.nullMsg = nullMsg;
	}
	/**
	 * @return the readOnly
	 */
	public String getReadOnly() {
		return readOnly;
	}
	/**
	 * @param readOnly the readOnly to set
	 */
	public void setReadOnly(String readOnly) {
		this.readOnly = readOnly;
	}
	/**
	 * @return the bizType
	 */
	public String getBizType() {
		return bizType;
	}
	/**
	 * @param bizType the bizType to set
	 */
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	/**
	 * @return the displayTxt
	 */
	public boolean isDisplayTxt() {
		return displayTxt;
	}
	/**
	 * @param displayTxt the displayTxt to set
	 */
	public void setDisplayTxt(boolean displayTxt) {
		this.displayTxt = displayTxt;
	}
	/**
	 * @return the outJs
	 */
	public boolean isOutJs() {
		return outJs;
	}
	/**
	 * @param outJs the outJs to set
	 */
	public void setOutJs(boolean outJs) {
		this.outJs = outJs;
	}
	/**
	 * @return the swfTransform
	 */
	public boolean isSwfTransform() {
		return swfTransform;
	}
	/**
	 * @param swfTransform the swfTransform to set
	 */
	public void setSwfTransform(boolean swfTransform) {
		this.swfTransform = swfTransform;
	}
	/**
	 * @return the viewModel
	 */
	public String getViewModel() {
		return viewModel;
	}
	/**
	 * @param viewModel the viewModel to set
	 */
	public void setViewModel(String viewModel) {
		this.viewModel = viewModel;
	}
}
