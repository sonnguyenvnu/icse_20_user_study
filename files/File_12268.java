package com.geekq.miaosha.service.rpchander;

import com.geekq.miaosha.common.SnowflakeIdWorker;
import com.geekq.miaosha.common.resultbean.ResultGeekQ;
import com.geekq.miaosha.service.rpchander.enums.PlanStepStatus;
import com.geekq.miaosha.service.rpchander.enums.PlanStepType;
import com.geekq.miaosha.service.rpchander.vo.HandlerParam;
import com.geekq.miaosha.service.rpchander.vo.PlanOrder;
import com.geekq.miaosha.service.rpchander.vo.PlanStep;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RpcCompensateService {


    public ResultGeekQ<String> recharge(){
        ResultGeekQ<String> result =  ResultGeekQ.build();
        /**
         * �?��?校验check
         */


        /**
         * 需�?�?�加redis分布�?�?
         */

        /**
         * 拦截
         * 校验状�? -- init 或 ROLLING_BACK则 返回
         *
         * �?功则返回已处�?�状�?
         */

        /**
         * 生�?订�?�和处�?�步骤
         */

        /**
         * 获�?�订�?�
         */
        long orderId = SnowflakeIdWorker.getOrderId(1,1);

        /**
         * 创建订�?�步骤 �?�定义一个VO
         * 一个planorder 对应多个planstep
         * 创建 PlanOrder 创建 planStep
         *   createOrderStep(vo);
         */


//        PlanOrder planOrder = new PlanOrder();
//        planOrder.setCreateTime(new Date());
//        planOrder.setVersion(0);
//        planOrder.setUserId(inputVo.getUserId());
//        planOrder.setOrderNo(inputVo.getOrderNo());
//        planOrder.setType(PlanOrderType.X_RECHARGE.name());
//        planOrder.setParams(params);
//        planOrder.setStatus(PlanOrderStatus.INIT.name());
//        planOrderDao.insertSelective(planOrder);
//
//        List<PlanStep> steps = new ArrayList<>();
//        //第一步请求民生
//        steps.add(planStepLogic.buildStep(planOrder.getId(), PlanStepType.X_RECHARGE_CMBC, PlanStepStatus.INIT));
//        if (inputVo.getCouponId() != null) {
//            //第二步使用优惠券
//            steps.add(planStepLogic.buildStep(planOrder.getId(), PlanStepType.X_RECHARGE_USE_COUPON, PlanStepStatus.INIT));
//        }
//        //第三步�?扣主账户
//        steps.add(planStepLogic.buildStep(planOrder.getId(), PlanStepType.X_RECHARGE_POINT, PlanStepStatus.INIT));
//        //第四部�?扣�?账户
//        steps.add(planStepLogic.buildStep(planOrder.getId(), PlanStepType.X_RECHARGE_SUB_POINT, PlanStepStatus.INIT));
//        //第五步�?��?通知
//        steps.add(planStepLogic.buildStep(planOrder.getId(), PlanStepType.X_RECHARGE_NOTIFY, PlanStepStatus.INIT));
//
//        planStepDao.batchInsert(steps);

        /**
         *
         * 调用Rpc接�?� 第几步错误则回滚�?几步
         * 并更新step状�?
         *
         * 然�?�定时任务去处�?� 状�?为INIT与ROLLBACK的 状�?订�?�
         *
         *
         */
//        HandlerParam handlerParam = new HandlerParam();
//        handlerParam.setPlanOrder(planOrder);
//        AutoInvestPlanRechargeOrderInputVo inputVo = JsonUtil.jsonToBean(planOrder.getParams(), AutoInvestPlanRechargeOrderInputVo.class);
//        handlerParam.setInputVo(inputVo);
//        for (int i = 0; i < planStepList.size(); i++) {
//            PlanStep planStep = planStepList.get(i);
//            PlanStepType stepType = PlanStepType.valueOf(planStep.getType());
//            xxx handler = (xxx) xxxx.getApplicationContext().getBean(stepType.getHandler());
//            boolean handlerResult = handler.handle(handlerParam);
//            if (!handlerResult) {
//                break;
//            }
//        }
        return result;
    }

}
