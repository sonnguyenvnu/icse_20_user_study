package org.nutz.mvc.view;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nutz.lang.Files;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.mvc.Mvcs;

/**
 * 内部�?定�?�视图
 * <p/>
 * 根�?�传入的视图�??，决定视图的路径：
 * <ul>
 * <li>如果视图�??以 '/' 开头， 则被认为是一个 全路径
 * <li>�?�则，将视图�??中的 '.' 转�?��? '/'，并加入�?缀 "/WEB-INF/"
 * </ul>
 * 通过注解映射的例�?：
 * <ul>
 * <li>'@Ok("forward:abc.cbc")' => /WEB-INF/abc/cbc
 * <li>'@Ok("forward:/abc/cbc")' => /abc/cbc
 * <li>'@Ok("forward:/abc/cbc.jsp")' => /abc/cbc.jsp
 * </ul>
 * 
 * @author mawm(ming300@gmail.com)
 * @author zozoh(zozohtnt@gmail.com)
 * @author wendal(wendal1985@gmail.com)
 */
public class ForwardView extends AbstractPathView {

    public ForwardView(String dest) {
        super(dest == null ? null : dest.replace('\\', '/'));
    }

    public void render(HttpServletRequest req, HttpServletResponse resp, Object obj)
            throws Exception {
        String path = evalPath(req, obj);
        String args = "";
        if (path == null)
            path = "";
        else if (path.contains("?")) { //将�?�数部分分解出�?�
            args = path.substring(path.indexOf('?'));
            path = path.substring(0, path.indexOf('?'));
        }

        String ext = getExt();        
        // 空路径，采用默认规则
        if (Strings.isBlank(path)) {
            path = Mvcs.getRequestPath(req);
            path = "/WEB-INF"
                    + (path.startsWith("/") ? "" : "/")
                    + Files.renameSuffix(path, ext);
        }
        // �?对路径 : 以 '/' 开头的路径�?增加 '/WEB-INF'
        else if (path.charAt(0) == '/') {
            if (!path.toLowerCase().endsWith(ext))
                path += ext;
        }
        // 包�??形�?的路径
        else {
            path = "/WEB-INF/" + path.replace('.', '/') + ext;
        }

        // 执行 Forward
        path = path + args;
        RequestDispatcher rd = req.getRequestDispatcher(path);
        if (rd == null)
            throw Lang.makeThrow("Fail to find Forward '%s'", path);
        // Do rendering
        rd.forward(req, resp);
    }

    /**
     * �?类�?�以覆盖这个方法，给出自己特殊的�?�缀,必须�?写哦
     * 
     * @return �?�缀
     */
    protected String getExt() {
        return "";
    }

}
