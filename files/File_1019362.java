package cn.iocoder.mall.user.application.controller.users;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.user.api.UserService;
import cn.iocoder.mall.user.api.bo.UserBO;
import cn.iocoder.mall.user.api.dto.UserUpdateDTO;
import cn.iocoder.mall.user.application.convert.UserConvert;
import cn.iocoder.mall.user.application.vo.users.UsersUserVO;
import cn.iocoder.mall.user.sdk.annotation.RequiresLogin;
import cn.iocoder.mall.user.sdk.context.UserSecurityContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.common.framework.vo.CommonResult.success;

@RestController
@RequestMapping("/users/user")
@Api("用户模�?�")
public class UserController {

    @Reference(validation = "true", version = "${dubbo.provider.UserService.version}")
    private UserService userService;

    @GetMapping("/info")
    @RequiresLogin
    @ApiOperation(value = "用户信�?�")
    public CommonResult<UsersUserVO> info() {
        UserBO userResult = userService.getUser(UserSecurityContextHolder.getContext().getUserId());
        return success(UserConvert.INSTANCE.convert2(userResult));
    }

    @PostMapping("/update_avatar")
    @RequiresLogin
    @ApiOperation(value = "更新头�?")
    public CommonResult<Boolean> updateAvatar(@RequestParam("avatar") String avatar) {
        // 创建
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO().setId(UserSecurityContextHolder.getContext().getUserId())
                .setAvatar(avatar);
        // 更新头�?
        return success(userService.updateUser(userUpdateDTO));
    }

    @PostMapping("/update_nickname")
    @RequiresLogin
    @ApiOperation(value = "更新昵称")
    public CommonResult<Boolean> updateNickname(@RequestParam("nickname") String nickname) {
        // 创建
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO().setId(UserSecurityContextHolder.getContext().getUserId())
                .setNickname(nickname);
        // 更新头�?
        return success(userService.updateUser(userUpdateDTO));
    }

}
