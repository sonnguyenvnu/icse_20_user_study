package com.geekq.admin.entity;

import com.geekq.common.utils.MD5.MD5Utils;
import com.geekq.common.utils.numcal.BidConst;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;

/**
 * 用户的�?户信�?�账户 一个LoginInfo 对应一个UserInfo对应一个Account
 * 
 * @author 邱润泽
 */
@Getter
@Setter
@Alias("Account")
public class Account extends BaseDomain {
	private static final long serialVersionUID = 6760287512112252557L;
	private int version;
	private String tradePassword; // 交易密�?
	private BigDecimal usableAmount = BidConst.ZERO; // �?�用余�?
	private BigDecimal freezedAmount = BidConst.ZERO; // 冻结金�?
	private BigDecimal unReceiveInterest = BidConst.ZERO; // 账户待收利�?�
	private BigDecimal unReceivePrincipal = BidConst.ZERO; // 账户待收本金
	private BigDecimal unReturnAmount = BidConst.ZERO; // 账户待还金�?
	private BigDecimal remainBorrowLimit = BidConst.ZERO; // 账户剩余授信�?度
	private BigDecimal borrowLimitAmount; // 授信�?度（当�?还�?�以信用借款�?度）

	private String abstractInfo;//摘�?信�?�用于防篡改检查;

	public String getAbstractInfo() {//�?�用余�? + 冻结金�? + 账户神域的授�?��?度
		return MD5Utils.MD5(usableAmount.add(freezedAmount)
				.add(remainBorrowLimit).toString());
	}

	public boolean checkAbstractInfo() {//�?�用余�? + 冻结金�? + 账户神域的授�?��?度
		return MD5Utils.MD5(
				usableAmount.add(freezedAmount).add(remainBorrowLimit)
						.toString()).equals(abstractInfo);
	}

	public BigDecimal getTotalAmount() {
		return usableAmount.add(freezedAmount).add(unReceivePrincipal);
	}

	public void addUseableAmount(BigDecimal amount) {
		this.usableAmount = this.usableAmount.add(amount);
	}

	public void addFreezedAmount(BigDecimal amount) {
		this.freezedAmount = this.freezedAmount.add(amount);
	}

	public static Account empty(Long id) {
		Account account = new Account();
		account.setId(id);
		account.setBorrowLimitAmount(BidConst.DEFALUT_BORROWLIMITAMOUNT);
		account.setRemainBorrowLimit(BidConst.DEFALUT_BORROWLIMITAMOUNT);
		return account;
	}
}
