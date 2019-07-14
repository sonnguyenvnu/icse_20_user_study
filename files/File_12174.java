package com.geekq.common.utils.numcal;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 计算器Util
 *
 * @author 邱润泽
 *
 */
public class CalculatetUtil {

	public static final BigDecimal ONE_HUNDRED = new BigDecimal("100.0000");
	public static final BigDecimal NUMBER_MONTHS_OF_YEAR = new BigDecimal(
			"12.0000");

	/**
	 * 获�?�月利率
	 */
	public static BigDecimal getMonthlyRate(BigDecimal yearRate) {
		if (yearRate == null)
			return BigDecimal.ZERO;
		return yearRate.divide(ONE_HUNDRED).divide(NUMBER_MONTHS_OF_YEAR,
				BidConst.CAL_SCALE, RoundingMode.HALF_UP);
	}

	/**
	 * 计算借款总利�?�
	 *
	 * @param returnType
	 *            还款类型
	 * @param bidRequestAmount
	 *            借款金�?
	 * @param yearRate
	 *            年利率
	 * @param monthes2Return
	 *            还款期�?
	 * @return
	 */
	public static BigDecimal calTotalInterest(int returnType,
			BigDecimal bidRequestAmount, BigDecimal yearRate, int monthes2Return) {
		BigDecimal totalInterest = BigDecimal.ZERO;
		BigDecimal monthlyRate = getMonthlyRate(yearRate);
		if (returnType == BidConst.RETURN_TYPE_MONTH_INTEREST_PRINCIPAL) {// 按月分期
			// �?�借款一个月
			if (monthes2Return == 1) {
				totalInterest = bidRequestAmount.multiply(monthlyRate)
						.setScale(BidConst.CAL_SCALE, RoundingMode.HALF_UP);
			} else {
				BigDecimal temp1 = bidRequestAmount.multiply(monthlyRate);
				BigDecimal temp2 = (BigDecimal.ONE.add(monthlyRate))
						.pow(monthes2Return);
				BigDecimal temp3 = (BigDecimal.ONE.add(monthlyRate)).pow(
						monthes2Return).subtract(BigDecimal.ONE);
				// 算出�?月还款
				BigDecimal monthToReturnMoney = temp1.multiply(temp2).divide(
						temp3, BidConst.CAL_SCALE, RoundingMode.HALF_UP);
				// 算出总还款
				BigDecimal totalReturnMoney = monthToReturnMoney
						.multiply(new BigDecimal(monthes2Return));
				// 算出总利�?�
				totalInterest = totalReturnMoney.subtract(bidRequestAmount);
			}
		} else if (returnType == BidConst.RETURN_TYPE_MONTH_INTEREST) {// 按月到期
			BigDecimal monthlyInterest = DecimalFormatUtil
					.amountFormat(bidRequestAmount.multiply(monthlyRate));
			totalInterest = monthlyInterest.multiply(new BigDecimal(
					monthes2Return));
		}
		return DecimalFormatUtil.formatBigDecimal(totalInterest,
				BidConst.STORE_SCALE);
	}

	/**
	 * 计算�?期利�?�
	 *
	 * @param returnType
	 *            还款类型
	 * @param bidRequestAmount
	 *            借款金�?
	 * @param yearRate
	 *            年利率
	 * @param monthIndex
	 *            第几期
	 * @param monthes2Return
	 *            还款期�?
	 * @return
	 */
	public static BigDecimal calMonthlyInterest(int returnType,
			BigDecimal bidRequestAmount, BigDecimal yearRate, int monthIndex,
			int monthes2Return) {
		BigDecimal monthlyInterest = BigDecimal.ZERO;
		BigDecimal monthlyRate = getMonthlyRate(yearRate);
		if (returnType == BidConst.RETURN_TYPE_MONTH_INTEREST_PRINCIPAL) {// 按月分期
			// �?�借款一个月
			if (monthes2Return == 1) {
				monthlyInterest = bidRequestAmount.multiply(monthlyRate)
						.setScale(BidConst.CAL_SCALE, RoundingMode.HALF_UP);
			} else {
				BigDecimal temp1 = bidRequestAmount.multiply(monthlyRate);
				BigDecimal temp2 = (BigDecimal.ONE.add(monthlyRate))
						.pow(monthes2Return);
				BigDecimal temp3 = (BigDecimal.ONE.add(monthlyRate)).pow(
						monthes2Return).subtract(BigDecimal.ONE);
				BigDecimal temp4 = (BigDecimal.ONE.add(monthlyRate))
						.pow(monthIndex - 1);
				// 算出�?月还款
				BigDecimal monthToReturnMoney = temp1.multiply(temp2).divide(
						temp3, BidConst.CAL_SCALE, RoundingMode.HALF_UP);
				// 算出总还款
				BigDecimal totalReturnMoney = monthToReturnMoney
						.multiply(new BigDecimal(monthes2Return));
				// 算出总利�?�
				BigDecimal totalInterest = totalReturnMoney
						.subtract(bidRequestAmount);

				if (monthIndex < monthes2Return) {
					monthlyInterest = (temp1.subtract(monthToReturnMoney))
							.multiply(temp4).add(monthToReturnMoney)
							.setScale(BidConst.CAL_SCALE, RoundingMode.HALF_UP);
				} else if (monthIndex == monthes2Return) {
					BigDecimal temp6 = BigDecimal.ZERO;
					// 汇总最�?�一期之�?所有利�?�之和
					for (int i = 1; i < monthes2Return; i++) {
						BigDecimal temp5 = (BigDecimal.ONE.add(monthlyRate))
								.pow(i - 1);
						monthlyInterest = (temp1.subtract(monthToReturnMoney))
								.multiply(temp5)
								.add(monthToReturnMoney)
								.setScale(BidConst.CAL_SCALE,
										RoundingMode.HALF_UP);
						temp6 = temp6.add(monthlyInterest);
					}
					monthlyInterest = totalInterest.subtract(temp6);
				}

			}
		} else if (returnType == BidConst.RETURN_TYPE_MONTH_INTEREST) {// 按月到期
			monthlyInterest = DecimalFormatUtil.amountFormat(bidRequestAmount
					.multiply(monthlyRate));
		}
		return monthlyInterest;
	}

	/**
	 * 计算�?期还款
	 *
	 * @param returnType
	 *            还款类型
	 * @param bidRequestAmount
	 *            借款金�?
	 * @param yearRate
	 *            年利率
	 * @param monthIndex
	 *            第几期
	 * @param monthes2Return
	 *            还款期�?
	 * @return
	 */
	public static BigDecimal calMonthToReturnMoney(int returnType,
			BigDecimal bidRequestAmount, BigDecimal yearRate, int monthIndex,
			int monthes2Return) {
		BigDecimal monthToReturnMoney = BigDecimal.ZERO;
		BigDecimal monthlyRate = getMonthlyRate(yearRate);
		if (returnType == BidConst.RETURN_TYPE_MONTH_INTEREST_PRINCIPAL) {// 按月分期
			if (monthes2Return == 1) {
				monthToReturnMoney = bidRequestAmount.add(
						bidRequestAmount.multiply(monthlyRate)).setScale(
						BidConst.CAL_SCALE, RoundingMode.HALF_UP);
			} else {
				BigDecimal temp1 = bidRequestAmount.multiply(monthlyRate);
				BigDecimal temp2 = (BigDecimal.ONE.add(monthlyRate))
						.pow(monthes2Return);
				BigDecimal temp3 = (BigDecimal.ONE.add(monthlyRate)).pow(
						monthes2Return).subtract(BigDecimal.ONE);
				// 算出�?月还款
				monthToReturnMoney = temp1.multiply(temp2).divide(temp3,
						BidConst.CAL_SCALE, RoundingMode.HALF_UP);
			}
		} else if (returnType == BidConst.RETURN_TYPE_MONTH_INTEREST) {// 按月到期
			BigDecimal monthlyInterest = bidRequestAmount.multiply(monthlyRate)
					.setScale(BidConst.CAL_SCALE, RoundingMode.HALF_UP);
			if (monthIndex == monthes2Return) {
				monthToReturnMoney = bidRequestAmount.add(monthlyInterest)
						.setScale(BidConst.CAL_SCALE, RoundingMode.HALF_UP);
			} else if (monthIndex < monthes2Return) {
				monthToReturnMoney = monthlyInterest;
			}
		}
		return DecimalFormatUtil.formatBigDecimal(monthToReturnMoney,
				BidConst.STORE_SCALE);
	}

	/**
	 * 计算一次投标实际获得的利�?�=投标金�?/借款金�? *总利�?�
	 *
	 * @param bidRequestAmount
	 *            借款金�?
	 * @param monthes2Return
	 *            还款期数
	 * @param yearRate
	 *            年利率
	 * @param returnType
	 *            还款类型
	 * @param acturalBidAmount
	 *            投标金�?
	 * @return
	 */
	public static BigDecimal calBidInterest(BigDecimal bidRequestAmount,
			int monthes2Return, BigDecimal yearRate, int returnType,
			BigDecimal acturalBidAmount) {
		// 借款产生的总利�?�
		BigDecimal totalInterest = calTotalInterest(returnType,
				bidRequestAmount, yearRate, monthes2Return);
		// 所�?�比例
		BigDecimal proportion = acturalBidAmount.divide(bidRequestAmount,
				BidConst.CAL_SCALE, RoundingMode.HALF_UP);
		BigDecimal bidInterest = totalInterest.multiply(proportion);
		return DecimalFormatUtil.formatBigDecimal(bidInterest,
				BidConst.STORE_SCALE);
	}

	/**
	 * 计算利�?�管�?�费
	 * 
	 * @param interest
	 *            利�?�
	 * @param interestManagerChargeRate
	 *            利�?�管�?�费比例
	 * @return
	 */
	/**
	public static BigDecimal calInterestManagerCharge(BigDecimal interest) {
		return DecimalFormatUtil.formatBigDecimal(
				interest.multiply(BidConst.INTEREST_MANAGER_CHARGE_RATE), BidConst.SCALE);
	}
	*/

	/**
	 * 计算借款管�?�费
	 *
	 * @param bidRequestAmount
	 *            借款金�?
	 * @param returnType
	 *            还款类型
	 * @param monthes2Return
	 *            还款期�?
	 * @return
	 */
	public static BigDecimal calAccountManagementCharge(
			BigDecimal bidRequestAmount) {
		BigDecimal accountManagementCharge = DecimalFormatUtil
				.formatBigDecimal(bidRequestAmount
						.multiply(BidConst.ACCOUNT_MANAGER_CHARGE_RATE),
						BidConst.CAL_SCALE);
		return accountManagementCharge;
	}

}
