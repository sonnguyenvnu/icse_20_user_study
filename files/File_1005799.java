package cn.crap.controller.admin;

import cn.crap.adapter.MenuAdapter;
import cn.crap.dto.MenuDto;
import cn.crap.enu.MyError;
import cn.crap.framework.JsonResult;
import cn.crap.framework.MyException;
import cn.crap.framework.base.BaseController;
import cn.crap.framework.interceptor.AuthPassport;
import cn.crap.model.Menu;
import cn.crap.query.MenuQuery;
import cn.crap.service.MenuService;
import cn.crap.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
public class MenuController extends BaseController {
    @Autowired
    private MenuService menuService;

    /**
     * æ ¹æ?®çˆ¶è?œå?•ã€?è?œå?•å??ã€?è?œå?•ç±»åž‹å?Šé¡µç ?èŽ·å?–è?œå?•åˆ—è¡¨
     *
     * @return
     */
    @RequestMapping("/menu/list.do")
    @ResponseBody
    @AuthPassport(authority = C_AUTH_MENU)
    public JsonResult list(@ModelAttribute MenuQuery query) throws MyException{
        Page page = new Page(query);

        page.setAllRow(menuService.count(query));
        return new JsonResult(1, MenuAdapter.getDto(menuService.query(query)), page);
    }

    /**
     * è?œå?•è¯¦æƒ…
     * @param id
     * @param parentId
     * @param type è?œå?•ç±»åž‹ï¼Œå½“idä¸ºnullï¼ŒparentIdä¸ºnullæ—¶ï¼Œè¡¨ç¤ºæ–°å¢žçˆ¶è?œå?•ï¼Œéœ€è¦?æ ¹æ?®ä¼ å…¥çš„typeé»˜è®¤é€‰ä¸­è?œå?•ç±»åž‹
     * @return
     */
    @RequestMapping("/menu/detail.do")
    @ResponseBody
    @AuthPassport(authority = C_AUTH_MENU)
    public JsonResult detail(String id, String parentId, String type) {
        Menu menu = new Menu();
        menu.setParentId(parentId);
        Menu parentMenu = menuService.getById(parentId);
        if (id != null) {
            menu = menuService.getById(id);
        }else{
            menu.setType(parentMenu == null ? type : parentMenu.getType());
        }
        MenuDto menuDto = MenuAdapter.getDto(menu);

        return new JsonResult().data(menuDto);
    }

    /**
     * @return
     */
    @RequestMapping("/menu/addOrUpdate.do")
    @ResponseBody
    @AuthPassport(authority = C_AUTH_MENU)
    public JsonResult addOrUpdate(@ModelAttribute MenuDto menuDto) throws MyException{
        // å­?è?œå?•ç±»åž‹å’Œçˆ¶è?œå?•ç±»åž‹ä¸€è‡´
        Menu parentMenu = menuService.getById(menuDto.getParentId());
        if (parentMenu != null && parentMenu.getId() != null) {
            menuDto.setType(parentMenu.getType());
        }

        if (menuDto.getId() != null) {
            menuService.update(MenuAdapter.getModel(menuDto));
        } else {
            menuService.insert(MenuAdapter.getModel(menuDto));
        }
        // æ¸…é™¤ç¼“å­˜
        objectCache.del(C_CACHE_MENU);
        return new JsonResult().data(menuDto);
    }

    @RequestMapping("/menu/delete.do")
    @ResponseBody
    @AuthPassport(authority = C_AUTH_MENU)
    public JsonResult delete(@RequestParam String id) throws MyException {
        if (menuService.count(new MenuQuery().setParentId(id)) > 0) {
            throw new MyException(MyError.E000025);
        }
        menuService.delete(id);
        // æ¸…é™¤ç¼“å­˜
        objectCache.del(C_CACHE_MENU);
        return SUCCESS;
    }
}
