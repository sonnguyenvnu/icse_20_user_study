package org.nutz.mvc.filter;

import javax.servlet.http.HttpSession;

import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.View;
import org.nutz.mvc.view.ServerRedirectView;

/**
 * 检查当�? Session，如果存在�?一属性，并且�?为 null，则通过 <br>
 * �?�则，返回一个 ServerRecirectView 到对应 path
 * <p>
 * 构造函数需�?两个�?�数
 * <ul>
 * <li>第一个是， 需�?检查的属性�??称。如果 session 里存在这个属性，则表示通过检查
 * <li>第二个是，如果未通过检查，将当�?请求转�?�何处。 一个类似 /yourpath/xxx.xx 的路径
 * </ul>
 * 
 * @author zozoh(zozohtnt@gmail.com)
 */
public class CheckSession implements ActionFilter {

    private String name;
    private String path;

    public CheckSession(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public View match(ActionContext context) {
    	HttpSession session = Mvcs.getHttpSession(false);
    	if (session == null || null == session.getAttribute(name))
            return new ServerRedirectView(path);
        return null;
    }

}
