package com.spring.beans;

import java.math.BigDecimal;

import com.spring.beans.Account.AccountType;

public class InternalFundTransfer {
	 private int owner_id;
	    private AccountType fromAccType;
	    private AccountType toAccType;
	    private BigDecimal amount;
		public InternalFundTransfer() {
			super();
			// TODO Auto-generated constructor stub
		}
		public InternalFundTransfer(int ownerId, AccountType fromAccType, AccountType toAccType, BigDecimal amount) {
			super();
			this.owner_id = ownerId;
			this.fromAccType = fromAccType;
			this.toAccType = toAccType;
			this.amount = amount;
		}
		public int getOwner_id() {
			return owner_id;
		}
		public void setOwner_id(int ownerId) {
			this.owner_id = ownerId;
		}
		public AccountType getFromAccType() {
			return fromAccType;
		}
		public void setFromAccType(AccountType fromAccType) {
			this.fromAccType = fromAccType;
		}
		public AccountType getToAccType() {
			return toAccType;
		}
		public void setToAccType(AccountType toAccType) {
			this.toAccType = toAccType;
		}
		public BigDecimal getAmount() {
			return amount;
		}
		public void setAmount(BigDecimal amount) {
			this.amount = amount;
		}
		
}
